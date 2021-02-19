package com.spring.web.messengerapp.service;

import org.springframework.context.annotation.Bean;

import java.util.List;

public interface MessageService<T> {
   List<T> findAll() throws Exception;
   T findById(long id) throws Exception;
   T addNewElement(T t);
   T updateElement(T t);
   T removeElement(T t);
}
