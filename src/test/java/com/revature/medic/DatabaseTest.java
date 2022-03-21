package com.revature.medic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @BeforeEach
    void setUp() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";
        try (Connection connection = DriverManager.getConnection(
                url, username, password)) {
        }catch (SQLException e){
            e.printStackTrace();;
        }
    }

   /* @Test
    void getAllData() {
        List<Patient> patientlist = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            ResultSet rs = conn.prepareStatement("select * from patient where patient.PatientId=1").executeQuery();
            while (rs.next()) {
                patientlist.add(
                        new Patient(rs.getInt("patientId"),
                                rs.getString("firstName"),
                                rs.getString("middleName"),
                                rs.getString("lastName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("1", patientlist.get(0).getPatientId());
        Assertions.assertEquals("Floppa", patientlist.get(1).getFirstName());
        Assertions.assertEquals("", patientlist.get(2).getMiddleName());
        Assertions.assertEquals("", patientlist.get(3).getLastName());
    }*/
}
