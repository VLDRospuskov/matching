package com.vldrospuskov.matching;

import com.vldrospuskov.matching.model.School;
import com.vldrospuskov.matching.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GaleShapleyAlgorithm {

    private int engagedCount;
    private List<Student> studentList; // количество студентов
    private List<School> schoolList; // количество школ
    private int algorithmNumberPhases;

    public GaleShapleyAlgorithm(List<Student> studentList, List<School> schoolList, int algorithmNumberPhases) {
        this.studentList = studentList;
        this.schoolList = schoolList;
        this.algorithmNumberPhases = algorithmNumberPhases;
        engagedCount = 0;
        startAlgo();
    }

    public void startAlgo() {

        while (engagedCount < algorithmNumberPhases) {

            int free;
            for (free = 0; free < studentList.size(); free++) {
                if (!studentList.get(free).isFree())
                    break;
            }

            List<School> preferSchools = new ArrayList<>();
            for (int i = 0; i < studentList.size() && !studentList.get(free).isFree(); i++) {
                School preferSchool = studentList.get(i).getSchoolListPref().get(engagedCount);
                preferSchool.setStudent(studentList.get(i));
                preferSchools.add(preferSchool);
            }

            //sets busy - free students
            setStudentIsFree(preferSchools);

            engagedCount++;
        }
    }

    private void setStudentIsFree(List<School> preferSchools) {
        List<School> listUniqueSchools = listUniqueSchools(preferSchools);
        for (int i = 0; i < listUniqueSchools.size(); i++) {
            listUniqueSchools.get(i).getStudent().setFree(true);
        }
        System.out.println(listUniqueSchools);
    }

    private List<School> listUniqueSchools(List<School> preferSchools) {
        ArrayList<School> duplicateSchools = new ArrayList<>();
        for (int i = 0; i < preferSchools.size(); i++) {
            for (int j = i + 1; j < preferSchools.size(); j++) {
                if (preferSchools.get(i).equals(preferSchools.get(j))) {
                    duplicateSchools.add(preferSchools.get(j));
                }
            }
        }
        return Stream.concat(preferSchools.stream(), duplicateSchools.stream())
                .distinct()
                .filter(x -> !preferSchools.contains(x) || !duplicateSchools.contains(x))
                .collect(Collectors.toList());
    }

}
