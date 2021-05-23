/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao.generic;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xavier.verges
 */
public interface Dao<T> {

    T add(T t) throws SQLException, ClassNotFoundException;

    T getById(Integer id) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;

    void delete(long id) throws SQLException, ClassNotFoundException;

    T update(T t) throws SQLException, ClassNotFoundException;
}
