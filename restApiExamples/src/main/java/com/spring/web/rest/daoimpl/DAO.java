package com.spring.web.rest.daoimpl;

import java.util.Collection;

public interface DAO<T> {

    Collection<T> findAll();
}
