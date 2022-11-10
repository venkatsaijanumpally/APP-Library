package org.library.Model;

import org.junit.*;

import java.util.HashMap;


public class StudentTest {

    final int EXPECTED_ID = 8841;
    final Status EXPECTED_STATUS = Status.ALLOWED;
    final String EXPECTED_EMAIL = "abc@gmail.com";
    final String EXPECTED_PROGRAM = "AEROSPACE";
    final String EXPECTED_PHONE = "8888822222";
    final int EXPECTED_DUE_AMOUNT = 0;
    Student student;

    @Before
    public void setUp(){
        HashMap<String,String> hm= new HashMap<>();
        hm.put("id","8841");
        hm.put("status","ALLOWED");
        hm.put("email","abc@gmail.com");
        hm.put("program","AEROSPACE");
        hm.put("phone","8888822222");
        student=new Student(hm);
    }

    @After
    public void tearDown(){
        if(Student.studentExist(EXPECTED_ID))
            Student.deleteStudent(student.getId());
    }


    @Test
    public void studentTest(){
        Assert.assertEquals(EXPECTED_ID,student.getId());
        Assert.assertEquals(EXPECTED_STATUS,student.getStatus());
        Assert.assertEquals(EXPECTED_EMAIL,student.getEmail());
        Assert.assertEquals(EXPECTED_PROGRAM,student.getProgram());
        Assert.assertEquals(EXPECTED_PHONE,student.getPhone());
        Assert.assertEquals(EXPECTED_DUE_AMOUNT,student.getDue_amount());
    }

    @Test
    public void studentExistTest(){
        Assert.assertTrue(Student.studentExist(EXPECTED_ID));
    }

    @Test
    public void deleteStudentTest(){
        Student.deleteStudent(EXPECTED_ID);
        Assert.assertFalse("Student is deleted successfully", Student.studentExist(EXPECTED_ID));
    }
}
