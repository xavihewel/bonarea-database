/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao.generic;

import com.bonarea.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xavier.verges
 */
public class GenericStudentDao extends BaseDao<Student> implements Dao<Student> {

    private static final String TABLE = "student";
    private static final String KEY = "id";
    private final static String CONNECTIONSTRING
            = "jdbc:mysql://ba60efaf494dd2:e757a8f8@eu-cdbr-west-01.cleardb.com/heroku_c2a7aa794927668?reconnect=true";
    private final static String USER = "ba60efaf494dd2";
    private final static String PWD = "e757a8f8";

    private final static String INSERT_STATEMENT = "INSERT INTO " + TABLE
            + "(firstname, lastname, email) values (?,?,?)";

    private final static String UPDATE_STATEMENT = "UPDATE " + TABLE + " SET"
            + " firstname=?, lastname=?, email=? where id=?";

    private final static String DRIVER = "com.mysql.jdbc.Driver";

    @Override
    protected String getTable() {
        return TABLE;
    }

    @Override
    protected String getKey() {
        return KEY;
    }

    @Override
    protected String getConnectionString() {
        return CONNECTIONSTRING;
    }

    @Override
    protected String getUser() {
        return USER;
    }

    @Override
    protected String getPwd() {
        return PWD;
    }

    @Override
    protected String getDriver() {
        return DRIVER;
    }

    @Override
    protected Student getFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("firstname"));
        student.setLastName(rs.getString("lastname"));
        student.setEmail(rs.getString("email"));
        return student;
    }

    @Override
    public Student add(Student t) throws SQLException, ClassNotFoundException {
        Class.forName(this.getDriver());

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement preparedInsert = con.prepareStatement(INSERT_STATEMENT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedInsert.setString(1, t.getFirstName());
            preparedInsert.setString(2, t.getLastName());
            preparedInsert.setString(3, t.getEmail());

            preparedInsert.executeUpdate();
            ResultSet resultset = preparedInsert.getGeneratedKeys();
            while (resultset.next()) {
                int generatedKey = resultset.getInt(1);
                t.setId(generatedKey);
                System.out.println("Clave generada = " + generatedKey);
            }
        }
        return t;
    }

    @Override
    public Student update(Student t) throws SQLException, ClassNotFoundException {
        Class.forName(this.getDriver());

        try ( Connection con = DriverManager.getConnection(CONNECTIONSTRING, USER, PWD);  PreparedStatement preparedUpdate = con.prepareStatement(UPDATE_STATEMENT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedUpdate.setString(1, t.getFirstName());
            preparedUpdate.setString(2, t.getLastName());
            preparedUpdate.setString(3, t.getEmail());
            preparedUpdate.setInt(4, t.getId());

            preparedUpdate.executeUpdate();
        }
        return t;
    }
}
