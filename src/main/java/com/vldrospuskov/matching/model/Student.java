package com.vldrospuskov.matching.model;

import lombok.Data;

import java.util.List;

@Data
public class Student {

    private int id;

    private List<School> schoolListPref;

    private boolean isFree;

    public Student(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id}=" + id;
    }

}
