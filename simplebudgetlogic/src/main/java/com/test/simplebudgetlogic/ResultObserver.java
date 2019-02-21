package com.test.simplebudgetlogic;

public interface ResultObserver<T> {
    void notifyUpdate(T t);
}
