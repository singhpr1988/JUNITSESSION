package api;

import entities.*;
import java.util.List;

public interface FetchStudentDetails {

    List<Student> getStudentByName(String name);
    Student getStudentByRollNumber(int rollNumber);
}
