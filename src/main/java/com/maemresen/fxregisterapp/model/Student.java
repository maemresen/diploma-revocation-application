package com.maemresen.fxregisterapp.model;


import com.maemresen.fxregisterapp.constants.FieldName;
import com.maemresen.fxregisterapp.constants.PrimaryKey;
import com.maemresen.fxregisterapp.constants.RequiredField;

import java.sql.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")
public class Student {

    @PrimaryKey
    private int studentID;

    @FieldName(name = "TC No")
    private String ssn;

    @RequiredField(message = "Lütfen Adınızı Giriniz")
    @FieldName(name = "Ad")
    private String studentName;

    @RequiredField(message = "Lütfen Soyadınızı Giriniz")
    @FieldName(name = "Soyad")
    private String studentSurname;

    @FieldName(name = "Baba Adı")
    private String studentFatherName;

    @FieldName(name = "Ana Adı")
    private String studentMotherName;

    @FieldName(name = "Doğum Tarihi")
    private Date studentBirthDate;

    @FieldName(name = "Doğum Yeri")
    private String studentBirthPlace;

    @FieldName(name = "İl")
    private String studentProvince;

    @FieldName(name = "İlçe")
    private String studentCountry;

    @FieldName(name = "Üniversite")
    private String studentUniversity;

    @FieldName(name = "Fakülte")
    private String studentFaculty;

    @FieldName(name = "Bölüm")
    private String studentDepartment;

    @FieldName(name = "Diploma Seri No")
    private String studentDiplomaSerialNo;

    @FieldName(name = "Kayıt Tarihi")
    private Date studentRegisterDate;


    @FieldName(name = "Dosya No")
    private String registerNo;

    public Student() {

    }

    public Student(int studentID, String ssn, String studentName, String studentSurname, String studentFatherName,
                   String studentMotherName, Date studentBirthDate, String studentBirthPlace, String studentProvince,
                   String studentCountry, String studentUniversity, String studentFaculty, String studentDepartment,
                   String studentDiplomaSerialNo, Date studentRegisterDate, String registerNo) {

        this.studentID = studentID;
        this.ssn = ssn;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentFatherName = studentFatherName;
        this.studentMotherName = studentMotherName;
        this.studentBirthDate = studentBirthDate;
        this.studentBirthPlace = studentBirthPlace;
        this.studentProvince = studentProvince;
        this.studentCountry = studentCountry;
        this.studentUniversity = studentUniversity;
        this.studentFaculty = studentFaculty;
        this.studentDepartment = studentDepartment;
        this.studentDiplomaSerialNo = studentDiplomaSerialNo;
        this.studentRegisterDate = studentRegisterDate;
        this.registerNo = registerNo;
    }

    public Student(String ssn, String studentName, String studentSurname, String studentFatherName,
                   String studentMotherName, Date studentBirthDate, String studentBirthPlace, String studentProvince,
                   String studentCountry, String studentUniversity, String studentFaculty, String studentDepartment,
                   String studentDiplomaSerialNo, Date studentRegisterDate, String registerNo) {

        this.studentID = -1;
        this.ssn = ssn;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentFatherName = studentFatherName;
        this.studentMotherName = studentMotherName;
        this.studentBirthDate = studentBirthDate;
        this.studentBirthPlace = studentBirthPlace;
        this.studentProvince = studentProvince;
        this.studentCountry = studentCountry;
        this.studentUniversity = studentUniversity;
        this.studentFaculty = studentFaculty;
        this.studentDepartment = studentDepartment;
        this.studentDiplomaSerialNo = studentDiplomaSerialNo;
        this.studentRegisterDate = studentRegisterDate;
        this.registerNo = registerNo;
    }


    // getters


    public int getStudentID() {
        return studentID;
    }

    public String getSsn() {
        return ssn;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getStudentFatherName() {
        return studentFatherName;
    }

    public String getStudentMotherName() {
        return studentMotherName;
    }

    public Date getStudentBirthDate() {
        return studentBirthDate;
    }

    public String getStudentBirthDateString() {
        Date date = getStudentBirthDate();
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// FOrmat in This Format or you change Change as well
        return format.format(date);
    }

    public String getStudentBirthPlace() {
        return studentBirthPlace;
    }

    public String getStudentProvince() {
        return studentProvince;
    }

    public String getStudentCountry() {
        return studentCountry;
    }

    public String getStudentUniversity() {
        return studentUniversity;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public String getStudentDiplomaSerialNo() {
        return studentDiplomaSerialNo;
    }

    public Date getStudentRegisterDate() {
        return studentRegisterDate;
    }

    public String getStudentRegisterDateString() {
        Date date = getStudentRegisterDate();
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");// FOrmat in This Format or you change Change as well
        return format.format(date);
    }


    public String getRegisterNo() {
        return registerNo;
    }


    // setters

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public void setStudentMotherName(String studentMotherName) {
        this.studentMotherName = studentMotherName;
    }

    public void setStudentBirthDate(Date studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public void setStudentBirthPlace(String studentBirthPlace) {
        this.studentBirthPlace = studentBirthPlace;
    }

    public void setStudentProvince(String studentProvince) {
        this.studentProvince = studentProvince;
    }

    public void setStudentCountry(String studentCountry) {
        this.studentCountry = studentCountry;
    }

    public void setStudentUniversity(String studentUniversity) {
        this.studentUniversity = studentUniversity;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public void setStudentDiplomaSerialNo(String studentDiplomaSerialNo) {
        this.studentDiplomaSerialNo = studentDiplomaSerialNo;
    }

    public void setStudentRegisterDate(Date studentRegisterDate) {
        this.studentRegisterDate = studentRegisterDate;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", ssn='" + ssn + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", studentFatherName='" + studentFatherName + '\'' +
                ", studentMotherName='" + studentMotherName + '\'' +
                ", studentBirthDate=" + studentBirthDate +
                ", studentBirthPlace='" + studentBirthPlace + '\'' +
                ", studentProvince='" + studentProvince + '\'' +
                ", studentCountry='" + studentCountry + '\'' +
                ", studentUniversity='" + studentUniversity + '\'' +
                ", studentFaculty='" + studentFaculty + '\'' +
                ", studentDepartment='" + studentDepartment + '\'' +
                ", studentDiplomaSerialNo='" + studentDiplomaSerialNo + '\'' +
                ", studentRegisterDate=" + studentRegisterDate +
                ", registerNo='" + registerNo + '\'' +
                '}';
    }
}

