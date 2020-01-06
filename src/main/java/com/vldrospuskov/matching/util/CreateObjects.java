package com.vldrospuskov.matching.util;

import com.vldrospuskov.matching.model.School;
import com.vldrospuskov.matching.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CreateObjects {

    public static List<Student> createStudents(int numberStudents) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= numberStudents; i++) {
            studentList.add(new Student(i));
        }
        return studentList;
    }

    public static List<School> createSchools(int numberSchools) {
        List<School> schoolList = new ArrayList<>();
        for (int i = 1; i <= numberSchools; i++) {
            schoolList.add(new School(i));
        }
        return schoolList;
    }

    public static String shuffle(String string) {
        List<Character> list = string.chars().mapToObj(c -> new Character((char) c))
                .collect(Collectors.toList());
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        list.forEach(c -> sb.append(c));

        return sb.toString();
    }

}
