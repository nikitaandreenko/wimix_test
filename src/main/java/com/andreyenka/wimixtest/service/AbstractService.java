package com.andreyenka.wimixtest.service;

import java.util.List;

public interface AbstractService <T, K>{
    T create(T user);

    T findById(K user);

    List<T> findAll ();

    T update(T user);

    void delete(K user);
}
