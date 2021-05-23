/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xavier.verges
 */
public interface Dao<T> {

    T add(T t) throws SQLException, ClassNotFoundException;

    T getStudentById(long id) throws SQLException;

    List<T> getAll() throws SQLException;

    void delete(long id) throws SQLException;

    T update(T t) throws SQLException;
}
