package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Controller.Conexao_SCP;

public class Enderecos extends Conexao_SCP {
	
	static Scanner teclado = new Scanner(System.in); 
	
	public static void listagem_enderecos_clientes() {
		
		String BUSCAR_TODOS = "SELECT * FROM enderecos"; 
		
		try {
			
			Connection conn = conectar();
			PreparedStatement enderecos = conn.prepareStatement(BUSCAR_TODOS);
			ResultSet res = enderecos.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst(); 
			
			if(qtd > 0) {
				
				System.out.println("Listando Endereço dos Clientes"); 
				System.out.println("-----------------");
				
				while(res.next()) {
					
					System.out.println("ID: " + res.getInt(1));
					System.out.println("Cidade: " + res.getString(2)); 
					System.out.println("Estado: " + res.getString(3)); 
					System.out.println("Logradouro: " + res.getString(4)); 
					System.out.println("Bairro: " + res.getString(5)); 
					System.out.println("Número: " + res.getString(6)); 
					System.out.println("Cep: " + res.getString(7)); 
					System.out.println("ID_Cliente: " + res.getInt(8)); 
					System.out.println("-------------------------");
				}
				
			} else {
				
				System.out.println("Não Existem Endereços cadastrados dos Clientes");
			}
			
			enderecos.close();
			desconectar(conn);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.err.println("Erro buscando clientes"); 
			System.exit(-42);
		}
	}
	
	public static void inserir_endereco_dos_clientes() {
		
		System.out.println("Informe a sua cidade: ");
		String cidade = teclado.nextLine();
		
		System.out.println("Informe o seu estado: ");
		String estado = teclado.nextLine(); 
		
		System.out.println("Informe o seu endereço: ");
		String logradouro = teclado.nextLine(); 
		
		System.out.println("Informe o seu bairro: ");
		String bairro = teclado.nextLine(); 
		
		System.out.println("Informe o seu número: ");
		String numero = teclado.nextLine(); 
		
		System.out.println("Informe o seu cep: ");
		String cep = teclado.nextLine(); 
		
		System.out.println("Informe o id do Cliente para esse endereço: ");
		int id_cliente = Integer.parseInt(teclado.nextLine()); 
		
		String INSERIR = "INSERT INTO enderecos (cidade, estado, logradouro, bairro, numero, cep, id_cliente)  VALUES (?, ?, ?, ?, ?, ?, ?)";
		// SQL Injection
		
		try {
			
			Connection conn = conectar(); 
			
			PreparedStatement salvar = conn.prepareStatement(INSERIR);
			
			salvar.setString(1, cidade);
			salvar.setString(2, estado);
			salvar.setString(3, logradouro);
			salvar.setString(4, bairro);
			salvar.setString(5, numero);
			salvar.setString(6, cep);
			salvar.setInt(7, id_cliente);
			
			salvar.executeUpdate();
			salvar.close();
			
			desconectar(conn); 
			
			System.out.println("O Endereço: " + logradouro + " do " + id_cliente + " foi inserido com sucesso "); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			System.err.println(" Erro ao salvar o endereço do cliente ");
			System.exit(-42); 
		}
	}
	
	public static void atualizar_enderecos_dos_clientes() {
		
		System.out.println("Informe o código do endereço do Cliente: "); 
		int id = Integer.parseInt(teclado.nextLine()); 
		
		String BUSCAR_POR_ID = "SELECT * FROM enderecos WHERE id = ?"; 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement enderecos = conn.prepareStatement(BUSCAR_POR_ID); 
			enderecos.setInt(1, id);
			ResultSet res = enderecos.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				System.out.println("Informe a sua cidade: ");
				String cidade = teclado.nextLine();
				
				System.out.println("Informe o seu estado: ");
				String estado = teclado.nextLine(); 
				
				System.out.println("Informe o seu endereço: ");
				String logradouro = teclado.nextLine(); 
				
				System.out.println("Informe o seu bairro: ");
				String bairro = teclado.nextLine(); 
				
				System.out.println("Informe o seu número: ");
				String numero = teclado.nextLine(); 
				
				System.out.println("Informe o seu cep: ");
				String cep = teclado.nextLine(); 
				
				System.out.println("Informe o id do Cliente para esse endereço: ");
				int id_cliente = Integer.parseInt(teclado.nextLine()); 
				
				String ATUALIZAR = "UPDATE enderecos SET cidade = ?, estado = ?, logradouro = ?, bairro = ?, numero = ?, cep = ?, id_cliente = ? WHERE id = ?"; 
				
				PreparedStatement upd = conn.prepareStatement(ATUALIZAR);  
				
				upd.setString(1, cidade);
				upd.setString(2, estado);
				upd.setString(3, logradouro);
				upd.setString(4, bairro);
				upd.setString(5, numero);
				upd.setString(6, cep);
				upd.setInt(7, id_cliente);
				upd.setInt(8, id);
				
				upd.executeUpdate();
				upd.close(); 
				desconectar(conn); 
				
			} else {
				
				System.out.println("Não existe endereços com o id informado"); 
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("Erro ao atualizar o endereço");
			System.exit(-42);
		}
	}
	
	public static void deletar_enderecos_dos_clientes() {
		
		String DELETAR  = "DELETE FROM enderecos WHERE id = ?"; 
		String BUSCAR_POR_ID = "SELECT * FROM enderecos WHERE id = ?"; 
		
		System.out.println("Informe o código de endereço do cliente: ");
		int id = Integer.parseInt(teclado.nextLine()); 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement enderecos = conn.prepareStatement(BUSCAR_POR_ID); 
			enderecos.setInt(1, id); 
			ResultSet res = enderecos.executeQuery();
			
			res.last();
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				PreparedStatement del = conn.prepareStatement(DELETAR);
				del.setInt(1, id);
				del.executeUpdate();
				del.close();
				
				desconectar(conn); 
				
				System.out.println("O Endereços dos Clientes foi deletado com sucesso");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Erro ao deletar o Endereço dos Cliente");
			System.exit(-42);
		}
	}
}
