package View;

import java.util.Scanner;

import Model.Telefones;

public class Menu_Telefones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in); 
		
		Telefones t = new Telefones();
		
		int opt = 0; 
		
		do {
			
			System.out.println("    Tela de Menu do SCP     ");
			System.out.println("===========================|");
			System.out.println(" 1 - Listar Telefones      |");
			System.out.println(" 2 - Inserir Telefones     |");
			System.out.println(" 3 - Atualizar Telefones   |");
			System.out.println(" 4 - Deletar Telefones     |");
			System.out.println("===========================|");
			
			System.out.println("Informe a opção que você deseja: ");
			opt = teclado.nextInt(); 
			
			switch (opt) {
			
			case 1: 
				
				t.listar_telefone_dos_clientes();
				break;
				
			case 2: 
				
				t.inserir_telefone_dos_clientes();
				break;
				
			case 3:
				
				t.atualizar_telefone_dos_clientes();
				break;
				
			case 4: 
				
				t.deletar_telefones_dos_clientes();
				break;
			}
			
		} while(opt < 5);
	}
}
