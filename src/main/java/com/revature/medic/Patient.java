package com.revature.medic;

public class Patient {
    private int patientId;
    private String firstName;

    public Patient(int id, String name) {
        patientId = id;
        firstName = name;
    }

    public Patient(){}

    public int getPatientId() {
        return patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setPatientId(int id) {
        patientId = id;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    @Override
    public String toString() {
        return "Team [teamId=" + patientId + ", name=" + firstName + "]";
    }
}