/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao;

import com.bonarea.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xavier.verges
 */
public class StudentDaoImpl implements StudentDao {

    private final static String CONNECTIONSTRING
            = "jdbc:mysql://ba60efaf494dd2:e757a8f8@eu-cdbr-west-01.cleardb.com/heroku_c2a7aa794927668?reconnect=true";
    private final static String USER = "ba60efaf494dd2";
    private final static String PWD = "e757a8f8";
    private final static String INSERT_STATEMENT = "INSERT INTO student "
            + "(firstname, lastname, email) values (?,?,?)";
    private final static String GET_BY_ID_STATEMENT = "SELECT * from student "
            + " where id=?";
    private final static String GET_ALL_STATEMENT = "SELECT * from student";
    private final static String DELETE_STATEMENT = "DELETE FROM student"
            + " WHERE id=?";
    private final static String UPDATE_STATEMENT = "UPDATE student SET"
            + " firstname=?, lastname=?, email=? where id=?";

    private Student getFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("firstname"));
        student.setLastName(rs.getString("lastname"));
        student.setEmail(rs.getString("email"));
        return student;
    }

    @Override
    public Student add(Student student) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement preparedInsert = con.prepareStatement(INSERT_STATEMENT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedInsert.setString(1, student.getFirstName());
            preparedInsert.setString(2, student.getLastName());
            preparedInsert.setString(3, student.getEmail());

            preparedInsert.executeUpdate();
            ResultSet resultset = preparedInsert.getGeneratedKeys();
            while (resultset.next()) {
                int generatedKey = resultset.getInt(1);
                student.setId(generatedKey);
                System.out.println("Clave generada = " + generatedKey);
            }
        }
        return student;
    }

    @Override
    public Student getStudentById(Integer id) throws SQLException, ClassNotFoundException {
        Student student = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement ps = con.prepareStatement(GET_BY_ID_STATEMENT)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                student = this.getFromResultSet(rs);
            }
        }
        return student;
    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement ps = con.prepareStatement(GET_ALL_STATEMENT)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(this.getFromResultSet(rs));
            }
        }
        return students;
    }

    @Override
    public void delete(Integer id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement ps = con.prepareStatement(DELETE_STATEMENT)) {
            ps.setInt(1, (Integer) id);
            ps.executeUpdate();
        }
    }

    @Override
    public Student update(Student student) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement preparedUpdate = con.prepareStatement(UPDATE_STATEMENT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedUpdate.setString(1, student.getFirstName());
            preparedUpdate.setString(2, student.getLastName());
            preparedUpdate.setString(3, student.getEmail());
            preparedUpdate.setInt(4, student.getId());

            preparedUpdate.executeUpdate();
        }
        return student;
    }
}
