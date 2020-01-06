package com.vldrospuskov.matching;

import com.vldrospuskov.matching.model.School;
import com.vldrospuskov.matching.model.Student;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.vldrospuskov.matching.util.CreateObjects.*;

public class Matching {

    private int studentsNumber = 0;
    private int schoolsNumber = 0;
    private int schoolCapacity = 0;
    private int algorithmNumberPhases = 0;
    private int partialInformation = 0;

    private List<Student> studentList;
    private List<School> schoolList;

    public void start() {
        inputData();
        setupStudents();
        setupSchools();
        new GaleShapleyAlgorithm(studentList, schoolList, algorithmNumberPhases);
    }

    private void setupSchools() {
        System.out.println("List of schools");
        randomizeStudentsForSchools();
        printSchools();
    }

    private void randomizeStudentsForSchools() {
        for (int i = 0; i < schoolList.size(); i++) {
            schoolList.get(i).setStudentListPref(randomizeStudents(studentList));
        }
    }

    private List<Student> randomizeStudents(List<Student> studentList) {
        String stringNumber = "";
        for (int i = 1; i <= studentList.size(); i++) {
            stringNumber += i;
        }

        String shuffledString = shuffle(stringNumber);
        List<Student> shuffledStudentList = new ArrayList<>();
        for (int i = 0; i < shuffledString.length(); i++) {
            String i1 = shuffledString.substring(i, i + 1);
            shuffledStudentList.add(new Student(Integer.valueOf(i1)));
        }
        return shuffledStudentList;
    }

    private void printSchools() {
        for (int i = 0; i < schoolList.size(); i++) {
            System.out.println("schools id: " + schoolList.get(i).getId() + " student prefers: "
                    + schoolList.get(i).getStudentListPref());
        }
    }

    private void setupStudents() {
        studentList.get(0).setSchoolListPref(schoolList);
        randomizeSchoolsForStudents(studentList);
        System.out.println("List of students");
        printStudents();
    }

    private void printStudents() {
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println("student id: " + studentList.get(i).getId()
                    + " school prefs: " + studentList.get(i).getSchoolListPref());
        }
    }

    private void randomizeSchoolsForStudents(List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            students.get(i).setSchoolListPref(randomizeSchools(schoolList));
        }
    }

    private List<School> randomizeSchools(List<School> schoolList) {
        String stringNumber = "";
        for (int i = 1; i <= schoolList.size(); i++) {
            stringNumber += i;
        }

        String shuffledString = shuffle(stringNumber);
        List<School> shuffledSchoolList = new ArrayList<>();
        for (int i = 0; i < shuffledString.length(); i++) {
            String i1 = shuffledString.substring(i, i + 1);
            shuffledSchoolList.add(new School(Integer.valueOf(i1)));
        }
        return shuffledSchoolList;
    }

    @SneakyThrows
    private void inputData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Input number of students: ");
            studentsNumber = Integer.parseInt(reader.readLine());
            studentList = createStudents(studentsNumber);
            System.out.print("Input number of schools: ");
            schoolsNumber = Integer.parseInt(reader.readLine());
            schoolList = createSchools(schoolsNumber);
//            System.out.print("School's capacity: ");
//            schoolCapacity = Integer.parseInt(reader.readLine());
            System.out.print("Algorithm number of phases: ");
            algorithmNumberPhases = Integer.parseInt(reader.readLine());
//            System.out.print("Partial information, number of rows: ");
//            partialInformation = Integer.parseInt(reader.readLine());
        }
    }

}
