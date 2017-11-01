package impl;

import api.FetchStudentDetails;
import entities.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FetchStudentDetailsImplTest {

    private FetchStudentDetails unit;

    @Before
    public void setUp() {
        unit = new FetchStudentDetailsImpl();
    }

    @Test
    public void shouldReturnStudentWhenAskedStudentIsPresentInDBCaseOne() {
        Student expectedStudent = new Student(1, "ABC");
        Student actualStudent = unit.getStudentByRollNumber(expectedStudent.getRollNumber());
        Assert.assertNotNull(actualStudent);
        verifyStudent(expectedStudent, actualStudent);
    }

    @Test
    public void shouldReturnStudentWhenAskedStudentIsPresentInDBCaseTwo() {
        Student expectedStudent = new Student(3, "XYZ");
        Student actualStudent = unit.getStudentByRollNumber(expectedStudent.getRollNumber());
        Assert.assertNotNull(actualStudent);
        verifyStudent(expectedStudent, actualStudent);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenAskedStudentIsNotPresentInDB() {
        Student expectedStudent = new Student(5, "ABC");
        unit.getStudentByRollNumber(expectedStudent.getRollNumber());
    }

    @Test
    public void shouldReturnListOfStudentsWhenStudentsWithSameNameExistsInDBCaseOne() {
        Student expectedStudent = new Student(1, "ABC");
        List<Student> actualStudents = unit.getStudentByName(expectedStudent.getStudentName());
        Assert.assertEquals(2, actualStudents.size());
    }

    @Test
    public void shouldReturnListOfStudentsWhenStudentsWithSameNameExistsInDBCaseTwo() {
        Student expectedStudent = new Student(3, "XYZ");
        List<Student> actualStudents = unit.getStudentByName(expectedStudent.getStudentName());
        Assert.assertEquals(1, actualStudents.size());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenStudentWithGivenNameDoesNotExistsInDB() {
        Student expectedStudent = new Student(6, "KKK");
        unit.getStudentByName(expectedStudent.getStudentName());
    }

    private void verifyStudent(Student expectedStudent, Student actualStudent) {
        Assert.assertEquals(expectedStudent.getRollNumber(), actualStudent.getRollNumber());
        Assert.assertEquals(expectedStudent.getStudentName(), actualStudent.getStudentName());
    }

}
