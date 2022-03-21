package com.revature.medic;
import com.revature.medic.Patient;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet to accept and deliver Patient data
 */
public class PatientServlet extends HttpServlet {
    private Connection connection;

    public PatientServlet(Connection conn) {
        this.connection = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = new ArrayList<>();
        try {
            ResultSet rs = connection.prepareStatement("select * from patient").executeQuery();
            while (rs.next()) {
                Patient patientToAdd = new Patient(rs.getInt("PatientId"),rs.getString("FirstName"),rs.getString("MiddleName"),rs.getString("LastName"));
                patients.add(patientToAdd);
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve from db: " + e.getSQLState());
        }
        // Get JSON mapper
        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writeValueAsString(patients);
        resp.setContentType("application/json");
        resp.getWriter().println(results);
    }
    /**
     * An override method set up to insert data into the memory database by the user. Used for "Add Patient" functionality.
     * @param req a http request sent from the server to insert the specified fields into the database table.
     * @param resp a http response to the server, adding the fields to the table and printing them to the server.
     * @throws ServletException if this error occurs, it throws the error back to the calling method rather than handling it here.
     * @throws IOException if this error occurs, it throws it back to the method that called it.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Patient newPatient = mapper.readValue(req.getInputStream(), Patient.class);
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into patient values (?,?, ?, ?)");
            stmt.setInt(1, newPatient.getPatientId());
            stmt.setString(2, newPatient.getFirstName());
            stmt.setString(3, newPatient.getMiddleName());
            stmt.setString(4, newPatient.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

}