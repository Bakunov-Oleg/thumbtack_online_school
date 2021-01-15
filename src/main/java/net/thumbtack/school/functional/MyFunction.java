package net.thumbtack.school.functional;

@FunctionalInterface
public interface MyFunction<T, K> {
    K apply(T arg); //№10
    //K apply(T arg1, T arg2);//№11
}
