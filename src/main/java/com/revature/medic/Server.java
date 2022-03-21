package com.revature.medic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Server class to hold all Tomcat server methods
 */
public class Server {
    Tomcat server;
    String webAppName;

    /**
     * Default constructor initalizes the server
     * @throws SQLException
     */
    public Server() throws SQLException {
        server = new Tomcat();
        webAppName = "";

        server.setBaseDir("java.io.tempdir");
        server.getConnector();
        server.addContext("", null);

        // Default Servlet
        server.addServlet(webAppName, "defaultServlet", new DefaultServlet()).addMapping("/*");

        // Patient Servlet
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";
        Connection connection = DriverManager.getConnection(
                url, username, password);

        server.addServlet(webAppName, "patientServlet", new PatientServlet(connection)).addMapping("/patients");
        server.addServlet(webAppName, "addPatient", new AddPatient(connection)).addMapping("/addPatient");
        server.addServlet(webAppName, "allPatients", new AllPatients(connection)).addMapping("/allPatients");
    }

    /**
     * Starts server
     */
    public void run() {
        try {
            server.start();
            System.out.println("Server is running on http://localhost:" + server.getConnector().getLocalPort() + "/addPatient.html");
            System.out.println();
            System.out.println("Server is running on http://localhost:" + server.getConnector().getLocalPort() + "/index.html");
            server.getServer().await();
        } catch (LifecycleException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}