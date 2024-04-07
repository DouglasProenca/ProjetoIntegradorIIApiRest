package com.sistema.apicr7imports.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sistema.apicr7imports.services.DatabaseService;

@Repository
public class CPFRepository {

	@Autowired
	DatabaseService databaseService;
	
	public boolean isCPF(String cpf) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT IIF([dbo].[fn_isCPF] ('"+cpf+"') = 1,'true','false') AS is_cpf");
		
		Connection conexao = databaseService.getDatabaseConnection();
		PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
		
		ResultSet rs = instrucaoSQL.executeQuery();
		rs.next();
		
		return rs.getBoolean("is_cpf");
	}
}
