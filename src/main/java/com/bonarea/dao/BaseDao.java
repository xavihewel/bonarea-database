/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonarea.dao;

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

    private final static String CONNECTIONSTRING
            = "mysql://ba60efaf494dd2:e757a8f8@eu-cdbr-west-01.cleardb.com/heroku_c2a7aa794927668?reconnect=true";
    private final static String USER = "ba60efaf494dd2";
    private final static String PWD = "e757a8f8";
    
    protected abstract String getTable();

    protected abstract String getFields();
    
    protected abstract String getKey();
    
    

    @Override
    public T add(T t) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T getStudentById(long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected abstract T getFromResultSet(ResultSet rs) throws SQLException;

//    @Override
//    public List<T> getAll() throws SQLException {
//        List<T> llista = new ArrayList<>();
//        String sql = "SELECT " + this.getFields() + " FROM " + this.getTable() + ";";
//        try ( Connection cn = this.getDataSource().getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                llista.add(this.getFromResultSet(rs));
//            }
//        } catch (SQLException ex) {
//            throw ex;
//        }
//
//        return llista;
//    }
//
//    @Override
//    public void delete(long id) throws SQLException {
//        String sql = "DELETE FROM " + this.getTable()
//                + " WHERE " + this.getKey() + "=?;";
//
//        try ( Connection cn = getDataSource().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
//            ps.setLong(1, (Long) id);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            throw ex;
//        }
//    }

    @Override
    public T update(T t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
