package com.jkwl.question.config.assembler;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AssemblerFactoryImpl implements AssemblerFactory {

    Map<String, Assembler> assemblers = new HashMap<>();

    public <T, S> T assemble(S s, Class<T> clazz) {
        Assert.notNull(s, "target can not be null");
        Assert.notNull(clazz, "type class can not be null");

        String signature = signature(s.getClass(), clazz);
        Assembler assembler = assemblers.get(signature);

        if (assembler == null)
            throw new RuntimeException("assembler[" + signature + "] not existed");
        return clazz.cast(assembler.assemble(s));
    }

    @Override
    public <T, S> T assemble(S s, T t) {
        Assert.notNull(s, "source object can not be null");
        Assert.notNull(t, "target object can not be null");
        BeanUtils.copyProperties(assemble(s, t.getClass()), t);
        return t;
    }

    public void register(Assembler assembler) {

        String signature = signature(assembler);
        if (assemblers.containsKey(signature))
            return;
        assemblers.put(signature, assembler);
    }

    private String signature(Assembler assembler) {

        Type[] types = assembler.getClass().getGenericInterfaces();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType().equals(Assembler.class)) {
                    Type source = parameterizedType.getActualTypeArguments()[0];
                    Type target = parameterizedType.getActualTypeArguments()[1];
                    return signature(source, target);
                }
            }
        }
        return null;
    }


    private String signature(Type source, Type target) {
        Assert.notNull(source, "source can not be null");
        Assert.notNull(target, "source can not be null");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(source.getTypeName()).append("->").append(target.getTypeName());
        return stringBuilder.toString();
    }

}
