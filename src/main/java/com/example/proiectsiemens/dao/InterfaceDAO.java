package com.example.proiectsiemens.dao;

import java.util.List;

public interface InterfaceDAO<T> {
    List<T> findAll();
    T findById(int id);
    int insert(T t);
    int update(T t);
    int deleteById(int id);
}
