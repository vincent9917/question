package com.jkwl.question.config.assembler;

import java.util.ArrayList;
import java.util.List;

public interface Assembler<S, T> {

    T assemble(S s);

    default List<T> assemble(List<S> ss) {
        List<T> ts = new ArrayList<>();
        for (S s : ss) {
            ts.add(assemble(s));
        }
        return ts;
    }

}
