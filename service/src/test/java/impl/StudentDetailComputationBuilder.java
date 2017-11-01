package impl;

import api.FetchStudentDetails;
import api.StudentDetailComputation;

public class StudentDetailComputationBuilder {

    private static StudentDetailComputationBuilder builder;
    private FetchStudentDetails api;

    public static StudentDetailComputationBuilder getInstance() {
        builder = new StudentDetailComputationBuilder();
        return builder;
    }

    public StudentDetailComputationBuilder withFetchStudentDetails(FetchStudentDetails api) {
        builder.setFetchStudentDetails(api);
        return builder;
    }

    public StudentDetailComputation build() {
        StudentDetailComputationImpl studentDetailComputation = new StudentDetailComputationImpl();
        studentDetailComputation.setFetchStudentDetails(builder.getFetchStudentDetails());
        return studentDetailComputation;
    }

    public void setFetchStudentDetails(FetchStudentDetails api) {
        this.api = api;
    }

    public FetchStudentDetails getFetchStudentDetails() {
        return api;
    }
}
