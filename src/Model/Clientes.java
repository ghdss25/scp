package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Controller.Conexao_SCP;

public class Clientes extends Conexao_SCP{ 
	
	static Scanner teclado = new Scanner(System.in); 
	
	public static void listar_clientes() {
		
		String BUSCAR_TODOS = "SELECT * FROM clientes"; 
		
		try {
			
			Connection conn = conectar();
			PreparedStatement clientes = conn.prepareStatement(BUSCAR_TODOS);
			ResultSet res = clientes.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst(); 
			
			if(qtd > 0) {
				
				System.out.println("Listando clientes"); 
				System.out.println("-----------------");
				
				while(res.next()) {
					
					System.out.println("ID: " + res.getInt(1));
					System.out.println("Nome: " + res.getString(2)); 
					System.out.println("CPF: " + res.getString(3)); 
					System.out.println("-------------------------");
				}
				
			} else {
				
				System.out.println("Não Existem clientes cadastrados");
			}
			
			clientes.close();
			desconectar(conn);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.err.println("Erro buscando clientes"); 
			System.exit(-42);
		}
	}
	
	public static void inserir_clientes() {
		
		System.out.println("Informe o nome do Cliente: ");
		String nome = teclado.nextLine();
		
		System.out.println("Informe o Cpf do Cliente: "); 
		String cpf = teclado.nextLine(); 
		
		String INSERIR = "INSERT INTO clientes (nome, cpf) VALUES (?, ?)";
		// SQL Injection
		
		try {
			
			Connection conn = conectar(); 
			
			PreparedStatement salvar = conn.prepareStatement(INSERIR);
			
			salvar.setString(1, nome);
			salvar.setString(2, cpf);
			
			salvar.executeUpdate();
			salvar.close();
			
			desconectar(conn); 
			
			System.out.println("O cliente " + nome + "foi inserido com sucesso"); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			System.err.println("Erro Salvando Cliente");
			System.exit(-42); 
		}
	}
	
	public static void atualizar_clientes() {
		
		System.out.println("Informe o código do cliente: "); 
		int id = Integer.parseInt(teclado.nextLine()); 
		
		String BUSCAR_POR_ID = "SELECT * FROM clientes WHERE id = ?"; 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement cliente = conn.prepareStatement(BUSCAR_POR_ID); 
			cliente.setInt(1, id);
			ResultSet res = cliente.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				System.out.println("Informe nome do cliente: ");
				String nome = teclado.nextLine(); 
				
				System.out.println("Informe o cpf do cliente: ");
				String cpf = teclado.nextLine(); 
				
				String ATUALIZAR = "UPDATE clientes SET nome = ?, cpf = ? WHERE id = ?"; 
				
				PreparedStatement upd = conn.prepareStatement(ATUALIZAR);  
				
				upd.setString(1, nome);
				upd.setString(2, cpf); 
				upd.setInt(3, id);
				
				upd.executeUpdate();
				upd.close(); 
				desconectar(conn); 
				
			} else {
				
				System.out.println("Não existe cliente com o id informado"); 
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("Erro ao atualizar o cliente");
			System.exit(-42);
		}
	}
	
	public static void deletar_clientes() {
		
		System.out.println("Deletando clientes...");
		
		String DELETAR  = "DELETE FROM clientes WHERE id = ?"; 
		String BUSCAR_POR_ID = "SELECT * FROM clientes WHERE id = ?"; 
		
		System.out.println("Informe o código do cliente: ");
		int id = Integer.parseInt(teclado.nextLine()); 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement cliente = conn.prepareStatement(BUSCAR_POR_ID); 
			cliente.setInt(1, id); 
			ResultSet res = cliente.executeQuery();
			
			res.last();
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				PreparedStatement del = conn.prepareStatement(DELETAR);
				del.setInt(1, id);
				del.executeUpdate();
				del.close();
				
				desconectar(conn); 
				
				System.out.println("O Cliente foi deletado com sucesso");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Erro ao deletar o cliente");
			System.exit(-42);
		}
	}
}
