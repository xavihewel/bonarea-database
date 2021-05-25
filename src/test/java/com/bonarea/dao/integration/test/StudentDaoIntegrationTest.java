package com.bonarea.dao.integration.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.bonarea.dao.StudentDao;
import com.bonarea.dao.StudentDaoImpl;
import com.bonarea.model.Student;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xavier.verges
 */
public class StudentDaoIntegrationTest {

    StudentDao studentDao = new StudentDaoImpl();

    public StudentDaoIntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("runs ont time on startup");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("runs one time when finsish all tests");
    }

    @Before
    public void setUp() {
        System.out.println("runs before each test method");
    }

    @After
    public void tearDown() throws SQLException {
        System.out.println("runs after each test method");
        Integer result = studentDao.deleteAll();
    }

    @Test
    public void testAdd() throws SQLException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        assertTrue(studentDao.add(student) != null);
    }

    @Test
    public void testGetById() throws SQLException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        Student studentAdded = studentDao.add(student);
        assertTrue(studentDao.getStudentById(studentAdded.getId()) != null);
    }

    @Test
    public void testGetAll() throws SQLException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        Student studentAdded = studentDao.add(student);
        assertTrue(!studentDao.getAll().isEmpty());
    }

    @Test
    public void testDelete() throws SQLException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        Student studentAdded = studentDao.add(student);
        assertTrue(studentDao.delete(studentAdded.getId()) == 1);
    }

    @Test
    public void testUpdate() throws SQLException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        student = studentDao.add(student);
        student.setFirstName("test2");
        student.setLastName("test2");
        student.setEmail("test2");
        Student studentUpdated = studentDao.update(student);
        assertTrue(studentUpdated.equals(student));
    }

    @Test
    public void testDeleteAll() throws SQLException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        student = studentDao.add(student);
        assertTrue(studentDao.deleteAll() == 1);
    }
}
