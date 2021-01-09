package View;

import java.util.Scanner;

import Model.Clientes;

public class Menu_Clientes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		
		Clientes c = new Clientes();
		
		int opt = 0; 
		
		do {
			
			System.out.println("    Tela de Menu do SCP     ");
			System.out.println("===========================|");
			System.out.println(" 1 - Listar Clientes       |");
			System.out.println(" 2 - Inserir Clientes      |");
			System.out.println(" 3 - Atualizar Clientes    |");
			System.out.println(" 4 - Deletar Clientes      |");
			System.out.println("===========================|");
			
			System.out.println("Informe a opção que você deseja: ");
			opt = teclado.nextInt(); 
			
			switch (opt) {
			
			case 1: 
				
				c.listar_clientes();
				break;
				
			case 2: 
				
				c.inserir_clientes();
				break;
				
			case 3:
				
				c.atualizar_clientes();
				break;
				
			case 4: 
				
				c.deletar_clientes();
				break;
			}
			
		} while(opt < 5);
	}
}
