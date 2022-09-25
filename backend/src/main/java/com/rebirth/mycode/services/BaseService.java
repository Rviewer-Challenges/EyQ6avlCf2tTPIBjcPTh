package com.rebirth.mycode.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, C, U, ID> {

    default T findById(ID id) {
        throw new UnsupportedOperationException();
    }

    default List<T> findAll() {
        throw new UnsupportedOperationException();

    }

    default T insert(C object) {
        throw new UnsupportedOperationException();
    }

    default T update(U object, ID id) {
        throw new UnsupportedOperationException();

    }

    default void delete(ID id) {
        throw new UnsupportedOperationException();
    }

}
