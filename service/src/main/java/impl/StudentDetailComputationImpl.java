package impl;

import api.FetchStudentDetails;
import api.StudentDetailComputation;
import entities.Student;

public class StudentDetailComputationImpl implements StudentDetailComputation {

    private FetchStudentDetails fetchStudentDetails;

    public boolean hasStudentPassed(int rollNumber) {
        try {
            Student student = fetchStudentDetails.getStudentByRollNumber(rollNumber);
            return processToCheckIfStudentHasPassed(student);
        } catch(RuntimeException e) {
            throw new RuntimeException("Can not process student details", e);
        }
    }

    private boolean processToCheckIfStudentHasPassed(Student student) {
        double marks[] = student.getSubjectMarks();
        for (double subjectMarks : marks) {
            if (subjectMarks < 60) {
                return false;
            }
        }
        return true;
    }

    public void setFetchStudentDetails(FetchStudentDetails fetchStudentDetails) {
        this.fetchStudentDetails = fetchStudentDetails;
    }
}
