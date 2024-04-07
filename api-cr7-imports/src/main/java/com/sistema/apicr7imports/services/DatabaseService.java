package com.sistema.apicr7imports.services;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	    private final DataSource dataSource;

	    public DatabaseService(DataSource dataSource) {
	        this.dataSource = dataSource;
	    }

	    public Connection getDatabaseConnection() throws SQLException {
	        return dataSource.getConnection();
	    }
}
