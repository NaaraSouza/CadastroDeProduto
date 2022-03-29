package Util;


import java.util.ArrayList;
import java.util.List;

import Modelos.Produto;

public class ListaDeProdutos {
	
	private static List<Produto> listaDeProduto = new ArrayList<Produto>(); //Para criar lista

	public static List<Produto> getInstance() { //Método para a lista ser carregada
		return listaDeProduto;
	}
}
