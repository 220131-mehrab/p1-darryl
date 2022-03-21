package com.revature.medic;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Server server = new Server();
        server.run();
    }
}