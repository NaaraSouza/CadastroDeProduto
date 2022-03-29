package Aplicação;
//Pacote utilizado para manipular o objeto

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import View.ViewCRUDProduto;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, ParserConfigurationException, TransformerException {
		//A classe BufferedReader serve para ler arquivos em formato texto.
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//Ler e carregar as informações inseridas pelo usuário por meio do teclado
		int menu = 0;
		while (menu != 5) {

			menu = ViewCRUDProduto.ViewMenuPrincipal(reader);
			//Mostrar as opções do menu principal para que o usuário escolha
				switch (menu) {

				case 1:
					CRUDProduto.SalvarProduto(reader);
					break;
					
				case 2:
					CRUDProduto.ListarProdutosSalvos();
					break;
					
				case 3:
					CRUDProduto.DeletarProduto(reader);
					break;
					
				case 4:
					CRUDProduto.EditarProduto(reader);
					break;
				
				case 5:
					ViewCRUDProduto.ViewMsgFinal(3);
					break;
				
				}
		}

	}
}
