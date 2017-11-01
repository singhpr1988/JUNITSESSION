package entities;

public class Student {
    private int rollNumber;
    private String studentName;
    private String[] subjects;
    private double[] subjectMarks;

    public Student(int rollNumber, String studentName) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public double[] getSubjectMarks() {
        return subjectMarks;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public void setSubjectMarks(double[] subjectMarks) {
        this.subjectMarks = subjectMarks;
    }
}
