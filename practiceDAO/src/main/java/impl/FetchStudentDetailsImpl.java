package impl;

import api.FetchStudentDetails;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import entities.Student;

import java.util.List;
import java.util.Map;

public class FetchStudentDetailsImpl implements FetchStudentDetails {

    Map<Integer, Student> mapOne = prepareMapOne();

    ListMultimap<String, Student> mapTwo = prepareMapTwo();

    private ListMultimap<String,Student> prepareMapTwo() {
        ListMultimap<String, Student> map = ArrayListMultimap.create();
        for (Integer rollNumber : mapOne.keySet()) {
            Student s = mapOne.get(rollNumber);
            map.put(s.getStudentName(), s);
        }
        return map;
    }

    private Map<Integer, Student> prepareMapOne() {
        Map<Integer, Student> map = Maps.newHashMap();
        Student studentOne = new Student(1, "ABC");
        Student studentTwo = new Student(2, "ABC");
        Student studentThree = new Student(3, "XYZ");
        Student studentFour = new Student(4, "PQR");
        map.put(studentOne.getRollNumber(), studentOne);
        map.put(studentTwo.getRollNumber(), studentTwo);
        map.put(studentThree.getRollNumber(), studentThree);
        map.put(studentFour.getRollNumber(), studentFour);
        return map;
    }

    public List<Student> getStudentByName(String name) {
        List<Student> students = mapTwo.get(name);
        if (students.size() != 0) {
            return students;
        }
        throw new RuntimeException("Student with name " + name + " "+ "does not exists in DB");
    }

    public Student getStudentByRollNumber(int rollNumber) {
        Student student = mapOne.get(rollNumber);
        if (student != null) {
            return student;
        }
        throw new RuntimeException("Student with roll number " + rollNumber + " "+ "doesn't exist in DB");
    }

}
