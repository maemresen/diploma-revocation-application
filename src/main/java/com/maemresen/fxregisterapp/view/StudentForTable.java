package com.maemresen.fxregisterapp.view;

import com.maemresen.fxregisterapp.model.Student;
import javafx.beans.property.*;


public class StudentForTable {


    private Student student;

    private final IntegerProperty studentID;
    private final StringProperty ssn;
    private final StringProperty studentName;
    private final StringProperty studentSurname;
    private final StringProperty studentFatherName;
    private final StringProperty studentMotherName;
    private final StringProperty studentBirthPlace;
    private final StringProperty studentBirthDate;
    private final StringProperty studentUniversity;
    private final StringProperty studentFaculty;
    private final StringProperty studentDepartment;
    private final StringProperty studentDiplomaSerialNo;
    private final StringProperty studentProvince;
    private final StringProperty studentCountry;
    private final StringProperty studentRegisterDate;
    private final StringProperty registerNo;


    public StudentForTable(Student student) {
        this.student = student;
        this.studentID = new SimpleIntegerProperty(student.getStudentID());
        this.ssn = new SimpleStringProperty(student.getSsn());
        this.studentName = new SimpleStringProperty(student.getStudentName());
        this.studentSurname = new SimpleStringProperty(student.getStudentSurname());
        this.studentFatherName = new SimpleStringProperty(student.getStudentFatherName());
        this.studentMotherName = new SimpleStringProperty(student.getStudentMotherName());
        this.studentBirthPlace = new SimpleStringProperty(student.getStudentBirthPlace());
        this.studentBirthDate = new SimpleStringProperty(student.getStudentBirthDateString());
        this.studentUniversity = new SimpleStringProperty(student.getStudentUniversity());
        this.studentFaculty = new SimpleStringProperty(student.getStudentFaculty());
        this.studentDepartment = new SimpleStringProperty(student.getStudentDepartment());
        this.studentDiplomaSerialNo = new SimpleStringProperty(student.getStudentDiplomaSerialNo());
        this.studentProvince = new SimpleStringProperty(student.getStudentProvince());
        this.studentCountry = new SimpleStringProperty(student.getStudentCountry());
        this.studentRegisterDate = new SimpleStringProperty(student.getStudentRegisterDateString());
        this.registerNo = new SimpleStringProperty(student.getRegisterNo());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStudentID() {
        return studentID.get();
    }

    public IntegerProperty studentIDProperty() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID.set(studentID);
    }

    public String getSsn() {
        return ssn.get();
    }

    public StringProperty ssnProperty() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn.set(ssn);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public String getStudentSurname() {
        return studentSurname.get();
    }

    public StringProperty studentSurnameProperty() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname.set(studentSurname);
    }

    public String getStudentFatherName() {
        return studentFatherName.get();
    }

    public StringProperty studentFatherNameProperty() {
        return studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName.set(studentFatherName);
    }

    public String getStudentMotherName() {
        return studentMotherName.get();
    }

    public StringProperty studentMotherNameProperty() {
        return studentMotherName;
    }

    public void setStudentMotherName(String studentMotherName) {
        this.studentMotherName.set(studentMotherName);
    }

    public String getStudentBirthPlace() {
        return studentBirthPlace.get();
    }

    public StringProperty studentBirthPlaceProperty() {
        return studentBirthPlace;
    }

    public void setStudentBirthPlace(String studentBirthPlace) {
        this.studentBirthPlace.set(studentBirthPlace);
    }

    public String getStudentBirthDate() {
        return studentBirthDate.get();
    }

    public StringProperty studentBirthDateProperty() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(String studentBirthDate) {
        this.studentBirthDate.set(studentBirthDate);
    }

    public String getStudentUniversity() {
        return studentUniversity.get();
    }

    public StringProperty studentUniversityProperty() {
        return studentUniversity;
    }

    public void setStudentUniversity(String studentUniversity) {
        this.studentUniversity.set(studentUniversity);
    }

    public String getStudentFaculty() {
        return studentFaculty.get();
    }

    public StringProperty studentFacultyProperty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty.set(studentFaculty);
    }

    public String getStudentDepartment() {
        return studentDepartment.get();
    }

    public StringProperty studentDepartmentProperty() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment.set(studentDepartment);
    }

    public String getStudentDiplomaSerialNo() {
        return studentDiplomaSerialNo.get();
    }

    public StringProperty studentDiplomaSerialNoProperty() {
        return studentDiplomaSerialNo;
    }

    public void setStudentDiplomaSerialNo(String studentDiplomaSerialNo) {
        this.studentDiplomaSerialNo.set(studentDiplomaSerialNo);
    }

    public String getStudentProvince() {
        return studentProvince.get();
    }

    public StringProperty studentProvinceProperty() {
        return studentProvince;
    }

    public void setStudentProvince(String studentProvince) {
        this.studentProvince.set(studentProvince);
    }

    public String getStudentCountry() {
        return studentCountry.get();
    }

    public StringProperty studentCountryProperty() {
        return studentCountry;
    }

    public void setStudentCountry(String studentCountry) {
        this.studentCountry.set(studentCountry);
    }

    public String getStudentRegisterDate() {
        return studentRegisterDate.get();
    }

    public StringProperty studentRegisterDateProperty() {
        return studentRegisterDate;
    }

    public void setStudentRegisterDate(String studentRegisterDate) {
        this.studentRegisterDate.set(studentRegisterDate);
    }

    public String getRegisterNo() {
        return registerNo.get();
    }

    public StringProperty registerNoProperty() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo.set(registerNo);
    }
}

