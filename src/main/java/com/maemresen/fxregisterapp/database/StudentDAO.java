package com.maemresen.fxregisterapp.database;


import com.maemresen.fxregisterapp.model.Student;

import java.util.List;

public interface StudentDAO {


    /* CRUD */
    void insertStudent(Student student);

    void updateStudent(Student student);

    /* To search */
    List<Student> getStudentList();

    Student getStudentByStudentID(int studentID);

    List<Student> searchStudent(Student student);

    /**/
    Boolean hasStudentWithId(int studentID);

}
