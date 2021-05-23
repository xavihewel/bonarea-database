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
import java.util.List;
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
    public void tearDown() {
        System.out.println("runs after each test method");
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        Student student = new Student();
        student.setFirstName("Pepe");
        student.setLastName("Soto");
        student.setEmail("pepe@gmail.com");
        assertTrue(studentDao.add(student) != null);
    }

    @Test
    public void testGetById() throws SQLException, ClassNotFoundException {
        Student student = studentDao.getStudentById(5);
        assertTrue(student != null);
    }

    @Test
    public void testGetAll() throws SQLException, ClassNotFoundException {
        List<Student> students = studentDao.getAll();
        assertTrue(!students.isEmpty());
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException {
        Student student = new Student("test", "test", "test");
        student = studentDao.add(student);
        Integer id = student.getId();
        studentDao.delete(id);
        assertTrue(studentDao.getStudentById(id) == null);
    }
    
    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException {
        Student student = new Student("test", "test", "test");
        student = studentDao.add(student);
        student.setFirstName("test2");
        student.setLastName("test2");
        student.setEmail("test2");
        Student studentUpdated = studentDao.update(student);
        assertTrue(studentUpdated.equals(student));
    }
}
