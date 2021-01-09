package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao_SCP {
	
	public static Connection conectar() {
		
		String CLASS_DRIVER = "com.mysql.jdbc.Driver";
		String USUARIO = "gustavo";
		String SENHA = "123";
		String URL_SERVIDOR = "jdbc:mysql://localhost:3306/SCP?useSSL=false"; 
		
		try {
			
			Class.forName(CLASS_DRIVER); 
			return DriverManager.getConnection(URL_SERVIDOR, USUARIO, SENHA);
			
		} catch (Exception e) {
			
			if(e instanceof ClassNotFoundException) {
				
				System.out.println("Verifique o driver de conexão"); 
				
			} else {
				
				System.out.println("Verifique se o servidor está ativo."); 
				
			} 
			
			System.exit(-42); 
			return null;
		}
	}
	
	public static void desconectar(Connection conn) {
		
		if(conn != null) {
			
			try {
				
				conn.close();
				
			} catch (SQLException e) {
				
				System.out.println("Não foi possível fechar a conexão");
				e.printStackTrace();
			}
		}
	}
}
