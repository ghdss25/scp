package Model;

import Controller.Conexao_SCP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Produtos extends Conexao_SCP { 
	
	static Scanner teclado = new Scanner(System.in);
	
	public static void listar_produtos() {
		
		String BUSCAR_TODOS = "SELECT * FROM produtos"; 
		
		try {
			
			Connection conn = conectar();
			PreparedStatement produtos = conn.prepareStatement(BUSCAR_TODOS);
			ResultSet res = produtos.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst(); 
			
			if(qtd > 0) {
				
				System.out.println("Listando produtos"); 
				System.out.println("-----------------");
				
				while(res.next()) {
					
					System.out.println("ID: " + res.getInt(1));
					System.out.println("Discriminação: " + res.getString(2)); 
					System.out.println("Valor Unitário: " + res.getFloat(3));
					System.out.println("ID_Cliente: " + res.getInt(4)); 
					System.out.println("-------------------------");
				}
				
			} else {
				
				System.out.println("Não Existem produtos cadastrados");
			}
			
			produtos.close();
			desconectar(conn);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.err.println("Erro buscando produtos"); 
			System.exit(-42);
		}
	}
	
	public static void inserir_produtos() {
		
		System.out.println("Informe a discriminação ou nome do produto: ");
		String discriminacao = teclado.nextLine(); 
		
		System.out.println("Informe o valor unitário do produto: ");
		float vu = teclado.nextFloat(); 
		
		System.out.println("Informe o id do Cliente que escolheu o produto: ");
		int id_cliente = teclado.nextInt();
		
		String INSERIR = "INSERT INTO produtos (discriminacao, valor_unitario, id_cliente) VALUES (?, ?, ?)";
		// SQL Injection
		
		try {
			
			Connection conn = conectar(); 
			
			PreparedStatement salvar = conn.prepareStatement(INSERIR);
			
			salvar.setString(1, discriminacao);
			salvar.setFloat(2, vu);
			salvar.setInt(3, id_cliente);
			
			salvar.executeUpdate();
			salvar.close();
			
			desconectar(conn); 
			
			System.out.println("O produto " + discriminacao + "foi inserido com sucesso"); 
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.err.println("Erro Salvando Produto");
			System.exit(-42); 
		}	
	}
	
	public static void atualizar_produtos() {
		
		System.out.println("Informe o código do produto: "); 
		int id = Integer.parseInt(teclado.nextLine()); 
		
		String BUSCAR_POR_ID = "SELECT * FROM produtos WHERE id = ?"; 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement produto = conn.prepareStatement(BUSCAR_POR_ID); 
			produto.setInt(1, id);
			ResultSet res = produto.executeQuery();
			
			res.last(); 
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				System.out.println("Informe a descriminação ou nome do produto: ");
				String discriminacao = teclado.nextLine(); 
				
				System.out.println("Informe o valor unitário do produto: ");
				float vu = teclado.nextFloat();  
				
				System.out.println("Informe o id do Cliente que escolheu o produto: ");
				int id_cliente = Integer.parseInt(teclado.nextLine()); 
				
				String ATUALIZAR = "UPDATE produtos SET discriminacao = ?, valor_unitario = ?, id_cliente = ? WHERE id = ?"; 
				
				PreparedStatement upd = conn.prepareStatement(ATUALIZAR);  
				
				upd.setString(1, discriminacao);
				upd.setFloat(2, vu); 
				upd.setInt(3, id);
				
				upd.executeUpdate();
				upd.close(); 
				desconectar(conn); 
				
				System.out.println("O produto " + discriminacao + " foi atualizado com sucesso.");
				
			} else {
				
				System.out.println("Não existe produto com o id informado"); 
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("Erro ao atualizar produto");
			System.exit(-42);
		}
	}
	
	public static void deletar_produtos() {
		
		String DELETAR  = "DELETE FROM produtos WHERE id = ?"; 
		String BUSCAR_POR_ID = "SELECT * FROM produtos WHERE id = ?"; 
		
		System.out.println("Informe o código do produto: ");
		int id = Integer.parseInt(teclado.nextLine()); 
		
		try {
			
			Connection conn = conectar(); 
			PreparedStatement produto = conn.prepareStatement(BUSCAR_POR_ID); 
			produto.setInt(1, id); 
			ResultSet res = produto.executeQuery();
			
			res.last();
			int qtd = res.getRow(); 
			res.beforeFirst();
			
			if(qtd > 0) {
				
				PreparedStatement del = conn.prepareStatement(DELETAR);
				del.setInt(1, id);
				del.executeUpdate();
				del.close();
				
				desconectar(conn); 
				
				System.out.println("O Produto foi deletado com sucesso");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Erro ao deletar produto");
			System.exit(-42);	
		}
	}
}
