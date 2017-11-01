package impl;

import api.FetchStudentDetails;
import api.StudentDetailComputation;
import com.google.common.collect.Lists;
import entities.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class StudentDetailComputationImplTest {

    private StudentDetailComputation unit;
    private FetchStudentDetails fetchStudentDetails;

    @Before
    public void setUp() {
        fetchStudentDetails = Mockito.mock(FetchStudentDetails.class);
    }

    @Test
    public void shouldComputeStudentDetailsWhenStudentIsPassedInAllSubjects() {
        Student s = createStudent();
        s.setSubjectMarks(prepareSubjectMarks(1));

        Mockito.when(fetchStudentDetails.getStudentByRollNumber(s.getRollNumber())).thenReturn(s);

        StudentDetailComputationBuilder builder = StudentDetailComputationBuilder.getInstance();
        builder.withFetchStudentDetails(fetchStudentDetails);
        unit = builder.build();

        Assert.assertTrue(unit.hasStudentPassed(s.getRollNumber()));
    }

    @Test
    public void shouldComputeStudentDetailsWhenStudentHasFailed() {
        Student s = createStudent();
        s.setSubjectMarks(prepareSubjectMarks(0));

        Mockito.when(fetchStudentDetails.getStudentByRollNumber(s.getRollNumber())).thenReturn(s);

        StudentDetailComputationBuilder builder = StudentDetailComputationBuilder.getInstance();
        builder.withFetchStudentDetails(fetchStudentDetails);
        unit = builder.build();

        Assert.assertFalse(unit.hasStudentPassed(s.getRollNumber()));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenAskedStudentDoesNotExistInTheSystem() {
        Student s = createStudent();
        s.setSubjectMarks(prepareSubjectMarks(1));
        Exception e = new RuntimeException("Student with roll number " + s.getRollNumber() + " "+ "doesn't exist in DB");

        Mockito.when(fetchStudentDetails.getStudentByRollNumber(s.getRollNumber())).thenThrow(e);

        StudentDetailComputationBuilder builder = StudentDetailComputationBuilder.getInstance();
        builder.withFetchStudentDetails(fetchStudentDetails);
        unit = builder.build();

        unit.hasStudentPassed(s.getRollNumber());
    }

    private double[] prepareSubjectMarks(int num) {
        double marks[] = new double[4];
        if (num == 1) {
            ArrayList<Integer> list = Lists.newArrayList(90, 80, 82, 71);
            marks = prepare(list, marks);
        } else {
            ArrayList<Integer> list = Lists.newArrayList(60, 50, 82, 72);
            marks = prepare(list, marks);
        }
        return marks;
    }

    private double[] prepare(ArrayList<Integer> list, double[] marks) {
        int i = 0;
        for (Integer num : list) {
            marks[i] = num;
            i++;
        }
        return marks;
    }

    private Student createStudent() {
        return new Student(1, "PPP");
    }
}
