import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	public static Connection getConexao() {
		
		Connection conn = null;
		
		
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AULA_BD?useTimezone=true&serverTimezone=UTC","root","1234");
//			System.out.println("Conectado com sucesso!");
		
		}
		catch (SQLException  e) {
			e.printStackTrace();
			System.out.println("Erro ao conectar");
		}
		return conn;
	}
	

	
	public static void main(String[] args) {
		ConexaoBD.getConexao();
	}
	

}
