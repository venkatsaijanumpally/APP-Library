package org.library.Views;

import org.library.Model.Student;

import java.util.List;

public class StudentView {
    List<Student> students;

    public StudentView() {}

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
