package io.github.R3charged.utility;

@FunctionalInterface
public interface Setter<T, S, O> {
    void execute(T t, S s, O o);
}
