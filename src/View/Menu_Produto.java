package View;

import java.util.Scanner;

import Model.Produtos;

public class Menu_Produto {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		
		Produtos p = new Produtos();
		
		int opt = 0; 
		
		do {
			
			System.out.println("    Tela de Menu do SCP     ");
			System.out.println("===========================|");
			System.out.println(" 1 - Listar Produtos       |");
			System.out.println(" 2 - Inserir Produtos      |");
			System.out.println(" 3 - Atualizar Produtos    |");
			System.out.println(" 4 - Deletar Produtos      |");
			System.out.println("===========================|");
			
			System.out.println("Informe a opção que você deseja: ");
			opt = teclado.nextInt(); 
			
			switch (opt) {
			
			case 1: 
				
				p.listar_produtos();
				break;
				
			case 2: 
				
				p.inserir_produtos();
				break;
				
			case 3:
				
				p.atualizar_produtos();
				break;
				
			case 4: 
				
				p.deletar_produtos();
				break;
			}
			
		} while(opt < 5);
	}
}
