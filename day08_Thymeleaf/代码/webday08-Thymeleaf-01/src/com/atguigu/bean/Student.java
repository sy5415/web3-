package com.atguigu.bean;

import java.util.List;
import java.util.Map;

/**
 * 包名:com.atguigu.bean
 *
 * @author Leevi
 * 日期2021-05-13  10:55
 */
public class Student {
    private String studentName;
    private Subject subject;
    private List<School> schoolList;
    private Map<String,Teacher> teacherMap;

    public Student() {
    }

    public Student(String studentName, Subject subject, List<School> schoolList, Map<String, Teacher> teacherMap) {
        this.studentName = studentName;
        this.subject = subject;
        this.schoolList = schoolList;
        this.teacherMap = teacherMap;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public Map<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }
}
