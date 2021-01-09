package View;

import java.util.Scanner;

import Model.Enderecos;

public class Menu_Enderecos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		
		Enderecos e = new Enderecos();
		
		int opt = 0;  
		
		do {
			
			System.out.println("    Tela de Menu do SCP     ");
			System.out.println("===========================|");
			System.out.println(" 1 - Listar Endereços      |");
			System.out.println(" 2 - Inserir Endereços     |");
			System.out.println(" 3 - Atualizar Endereços   |");
			System.out.println(" 4 - Deletar Endereços     |");
			System.out.println("===========================|");
			
			System.out.println("Informe a opção que você deseja: ");
			opt = teclado.nextInt(); 
			
			switch (opt) {
			
			case 1: 
				
				e.listagem_enderecos_clientes();
				break;
				
			case 2: 
				
				e.inserir_endereco_dos_clientes();
				break;
				
			case 3:
				
				e.atualizar_enderecos_dos_clientes();
				break;
				
			case 4: 
				
				e.deletar_enderecos_dos_clientes();
				break;
			}
			
		} while(opt < 5);
	}
}
