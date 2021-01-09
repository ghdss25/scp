package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Controller.Conexao_SCP;

public class Telefones extends Conexao_SCP {
	
	static Scanner teclado = new Scanner(System.in); 
	
	public static void listar_telefone_dos_clientes() {
		
		String BUSCAR_TODOS = "SELECT * FROM telefones"; 
		
		try {
			
			Connection conn = conectar();
			PreparedStatement telefones = conn.prepareStatement(BUSCAR_TODOS);
			ResultSet res = telefones.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst(); 
			
			if(qtd > 0) {
				
				System.out.println("Listando Telefone dos Clientes"); 
				System.out.println("-----------------");
				
				while(res.next()) {
					
					System.out.println("ID: " + res.getInt(1));
					System.out.println("Número: " + res.getString(2)); 
					System.out.println("ID_Cliente: " + res.getInt(3)); 
					System.out.println("-------------------------");
				}
				
			} else {
				
				System.out.println("Não Existem Telefone dos Clientes cadastrados");
			}
			
			telefones.close();
			desconectar(conn);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.err.println("Erro buscando telefones"); 
			System.exit(-42);
		}
	}
	
	public static void inserir_telefone_dos_clientes() {
		
		System.out.println("Informe o número de telefone do cliente: ");
		String numero = teclado.nextLine();
		
		System.out.println("Informe o id do Cliente para esse telefone: ");
		int id_cliente = Integer.parseInt(teclado.nextLine()); 
		
		String INSERIR = "INSERT INTO telefones (numero, id_cliente) VALUES (?, ?)";
		// SQL Injection
		
		try {
			
			Connection conn = conectar(); 
			
			PreparedStatement salvar = conn.prepareStatement(INSERIR);
			
			salvar.setString(1, numero);
			salvar.setInt(2, id_cliente);
			
			salvar.executeUpdate();
			salvar.close();
			
			desconectar(conn); 
			
			System.out.println("O Telefone: " + numero + " do " + id_cliente + " foi inserido com sucesso "); 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			System.err.println(" Erro ao salvar o número de telefone do cliente ");
			System.exit(-42); 
		}
	}
	
	public static void atualizar_telefone_dos_clientes() {
		
		System.out.println("Informe o código do telefone do Cliente: "); 
		int id = Integer.parseInt(teclado.nextLine()); 
		
		String BUSCAR_POR_ID = "SELECT * FROM telefones WHERE id = ?"; 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement telefones = conn.prepareStatement(BUSCAR_POR_ID); 
			telefones.setInt(1, id);
			ResultSet res = telefones.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				System.out.println("Informe o número de telefone do cliente: ");
				String numero = teclado.nextLine();
				
				System.out.println("Informe o id do Cliente para esse telefone: ");
				int id_cliente = Integer.parseInt(teclado.nextLine()); 
				
				String ATUALIZAR = "UPDATE telefones SET numero = ?, id_cliente = ? WHERE id = ?"; 
				
				PreparedStatement upd = conn.prepareStatement(ATUALIZAR);  
				
				upd.setString(1, numero);
				upd.setInt(2, id_cliente);
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
	
	public static void deletar_telefones_dos_clientes() {
		
		String DELETAR  = "DELETE FROM telefones WHERE id = ?"; 
		String BUSCAR_POR_ID = "SELECT * FROM telefones WHERE id = ?"; 
		
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
				
				System.out.println("O Telefone do Cliente foi deletado com sucesso");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Erro ao deletar o telefone do cliente");
			System.exit(-42);
		}
	}
}
