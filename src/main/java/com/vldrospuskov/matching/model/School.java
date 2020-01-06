package com.vldrospuskov.matching.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class School {

    private int id;

    private List<Student> studentListPref;

    private Student student;

    public School(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "School{" +
                "id}=" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id &&
                Objects.equals(studentListPref, school.studentListPref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentListPref);
    }
}
