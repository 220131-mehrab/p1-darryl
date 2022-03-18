package com.revature.medic;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Patient> patients = new ArrayList<>();
        String searchId = req.getParameter("patientId");

        if (searchId == null) {
            try {
                ResultSet rs = connection.prepareStatement("select * from Patient").executeQuery();

                while (rs.next()) {
                    patients.add(new Patient(rs.getInt("patientId"), rs.getString("firstName")));
                }
            } catch (SQLException e) {
                System.err.println("Failed to retrieve from db: " + e.getSQLState());
            }
        } else {
            try {
                PreparedStatement stmt = connection.prepareStatement("select * from Patient where patientId = ?");
                stmt.setString(1, searchId);

                ResultSet rs = stmt.executeQuery();
                while (rs.next())
                    patients.add(new Patient(rs.getInt("patientId"), rs.getString("firstName")));
            } catch (SQLException e) {
                System.err.println("Failed to retrieve from db: " + e.getSQLState());
            }
        }

        // Object Mapper
        ObjectMapper mapper = new ObjectMapper();
        String results = mapper.writeValueAsString(patients);
        resp.setContentType("application/json");
        resp.getWriter().println(results);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Patient newPatient = mapper.readValue(req.getInputStream(), Patient.class);

        try {
            PreparedStatement stmt = connection.prepareStatement("insert into Patient values(?, ?)");
            stmt.setInt(1, newPatient.getPatientId());
            stmt.setString(2, newPatient.getFirstName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert: " + e.getMessage());
        }
    }

}