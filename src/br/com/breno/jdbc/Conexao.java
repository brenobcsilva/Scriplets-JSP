package br.com.breno.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancocjweb1", "postgres", "rambo111");
			System.out.println("Conectado com sucesso");
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "N�o pode conectar" + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Driver n�o encontrado");
		}
		return con;
	}

}
