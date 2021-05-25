/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao;

import com.bonarea.model.Student;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xavier.verges
 */
public interface StudentDao {

    Student add(Student student) throws SQLException;

    Student getStudentById(Integer id) throws SQLException;

    List<Student> getAll() throws SQLException;

    Integer delete(Integer id) throws SQLException;
    
    Integer deleteAll()throws SQLException;

    Student update(Student student) throws SQLException;
}
