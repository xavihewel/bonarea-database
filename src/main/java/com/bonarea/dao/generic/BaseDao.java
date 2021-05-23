/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao.generic;

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
public abstract class BaseDao<T> implements Dao<T> {

    protected abstract String getTable();

    protected abstract String getKey();

    protected abstract String getConnectionString();

    protected abstract String getUser();

    protected abstract String getPwd();
    
    protected abstract String getDriver();

    private final String GET_ALL_STATEMENT = "SELECT * FROM " + this.getTable();

    private final String DELETE_STATEMENT = "DELETE FROM " + this.getTable()
            + " WHERE " + this.getKey() + "=?";

    private final String GET_BY_ID_STATEMENT = "SELECT * FROM "
            + this.getTable() + " WHERE " + this.getKey() + "=?";

    protected abstract T getFromResultSet(ResultSet rs) throws SQLException;

    @Override
    public List<T> getAll() throws SQLException, ClassNotFoundException {
        Class.forName(this.getDriver());
        List<T> llista = new ArrayList<>();

        try ( Connection cn = DriverManager.getConnection(this.getConnectionString(), this.getUser(), this.getPwd());  PreparedStatement ps = cn.prepareStatement(GET_ALL_STATEMENT);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                llista.add(this.getFromResultSet(rs));
            }
        }
        return llista;
    }

    @Override
    public T getById(Integer id) throws SQLException, ClassNotFoundException {
        T result = null;
        Class.forName(this.getDriver());

        try ( Connection con = DriverManager.getConnection(this.getConnectionString(), this.getUser(), this.getPwd());  PreparedStatement ps = con.prepareStatement(GET_BY_ID_STATEMENT)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = this.getFromResultSet(rs);
            }
        }
        return result;
    }

    @Override
    public void delete(long id) throws SQLException , ClassNotFoundException{
        Class.forName(this.getDriver());
        try ( Connection cn = DriverManager.getConnection(this.getConnectionString(), this.getUser(), this.getPwd());  PreparedStatement ps = cn.prepareStatement(DELETE_STATEMENT)) {
            ps.setLong(1, (Long) id);
            ps.executeUpdate();
        }
    }

    @Override
    public abstract T add(T t) throws SQLException, ClassNotFoundException;

    @Override
    public abstract T update(T t) throws SQLException, ClassNotFoundException;
}
