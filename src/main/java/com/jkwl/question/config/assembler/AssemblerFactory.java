package com.jkwl.question.config.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface AssemblerFactory {

    <T, S> T assemble(S s, Class<T> clazz);

    default <T, S> List<T> assemble(List<S> ss, Class<T> clazz) {
        Collection<S> sc = new ArrayList<>(ss);
        return new ArrayList<>(assemble(sc, clazz));
    }

    default <T, S> Collection<T> assemble(Collection<S> ss, Class<T> clazz) {
        Assert.notNull(ss, "sources object can not be null");
        Assert.notNull(clazz, "type class can not be null");

        Class<?> cla = ss.getClass();
        try {
            Constructor constructor = cla.getDeclaredConstructor();
            Collection<T> collection = ss.getClass().cast(constructor.newInstance());
            for (S s : ss)
                collection.add(assemble(s, clazz));

            return collection;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException();
        }
    }

    <T, S> T assemble(S s, T t);

    default <T, S> Collection<T> assemble(Collection<S> ss, Collection<T> ts, Class<T> clazz) {
        return assemble(ss, ts, clazz, true);
    }

    default <T, S> Collection<T> assemble(Collection<S> ss, Collection<T> ts, Class<T> clazz, boolean removeUnMatched) {
        Assert.notNull(ss, "source objects can not be null");
        Assert.notNull(ts, "target objects can not be null");
        Assert.notNull(clazz, "type class can not be null");

        Collection<T> tSources = assemble(ss, clazz);

        Iterator<T> iterator = ts.iterator();
        while (iterator.hasNext()) {
            T t = iterator.next();
            boolean matched = false;
            for (T tSource : tSources) {
                if (tSource.equals(t)) {
                    BeanUtils.copyProperties(tSource, t);
                    matched = true;
                    break;
                }
            }

            if (!matched && removeUnMatched)
                iterator.remove();
        }
        return ts;
    }

    default <T, S> T assemble(Collection<S> ss, T root, String... paths) {
        return assemble(ss, root, true, paths);
    }

    default <T, S> T assemble(Collection<S> ss, T root, boolean removeUnMatched, String... paths) {
        Assert.notNull(ss, "source objects can not be null");
        Assert.notNull(root, "root object can not be null");
        Assert.notEmpty(paths, "path can not be empty");

        for (String path : paths) {
            String[] fieldNames = path.split("\\.");
            Field currentField;
            String fieldName = fieldNames[0];
            try {
                currentField = root.getClass().getDeclaredField(fieldName);
                currentField.setAccessible(true);

                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 1; i < fieldNames.length; i++) {
                    stringBuffer.append(fieldNames[i]);
                    if (i < fieldNames.length - 1)
                        stringBuffer.append(".");
                }
                String leftPath = stringBuffer.toString();
                Object obj = currentField.get(root);

                if (obj instanceof Collection) {
                    Collection objs = (Collection) obj;

                    if (path.contains(".")) {
                        for (Object o : objs)
                            assemble(ss, o, removeUnMatched, leftPath);
                    } else {
                        Iterator iterator = objs.iterator();
                        while (iterator.hasNext()) {
                            Object object = iterator.next();
                            boolean matched = false;
                            for (S s : ss) {
                                Object assembledS = assemble(s, object.getClass());
                                if (object.equals(assembledS)) {
                                    BeanUtils.copyProperties(assembledS, object);
                                    matched = true;
                                    break;
                                }
                            }
                            if (!matched && removeUnMatched)
                                iterator.remove();
                        }
                    }
                } else {
                    boolean matched = false;

                    if (path.contains(".")) {
                        assemble(ss, obj, removeUnMatched, leftPath);
                    } else {
                        for (S s : ss) {
                            if (obj.equals(s)) {
                                BeanUtils.copyProperties(s, obj);
                                matched = true;
                                break;
                            }
                        }

                        if (!matched && removeUnMatched)
                            currentField.set(obj, null);
                    }

                }

            } catch (ReflectiveOperationException e) {
                throw new RuntimeException();
            }
        }
        return root;
    }

    void register(Assembler assembler);
}
