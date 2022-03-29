package Aplica��o;
//Pacote utilizado para manipular o objeto

import java.io.BufferedReader;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import BancoDeDados.ManipulacaoArquivoTXT;
import BancoDeDados.ManipulacaoArquivoXML;
import Modelos.Marca;
import Modelos.Produto;
import Util.ListaDeProdutos;
import View.ViewCRUDProduto;



//***Todos os Coment�rios sobre o que entendi de alguns comando espec�ficos***:

	//CRUD:� respons�vel pela cria��o, consulta, atualiza��o e destrui��o de dados; utilizadas em bases de dados relacionais. 
	//Get and Set : Usamos get para obter informa��es(ele retorna um valor).Set define valores(n�o retorna valores).
	//getInstance retornar a instancia de um objeto para uma poss�vel compara��o.
	//TXT:� uma forma de manipular arquivos com Java, bem como escrever e ler arquivos no formato de texto (txt).
	//XML:Cria documentos com dados organizados.
	//A classe BufferedReader serve para ler arquivos em formato texto.
	//BufferedWriter serve para escrever em arquivos de texto;  
	//FileWriter serve para escrever diretamente no arquivo
	//InputStreamReader serve como um adaptador entre as duas classes - l� bytes de um lado, converte em caracteres do outro, atrav�s do uso de uma codifica��o de caracteres. 
	//Handler � um �Manipulador�
	//O this � usado para referenciar a um atributo externo fora do metodo


public class CRUDProduto {
	
	//CRUD:� respons�vel pela cria��o, consulta, atualiza��o e destrui��o de dados; utilizadas em bases de dados relacionais. 
	
	
	

	public static void SalvarProduto(BufferedReader reader) throws IOException, ParserConfigurationException, TransformerException { //Objeto criado sem utilizar o "new", e sim o static.
		//M�todo que serve para salvar as informa��es inseridas pelo usu�rio
		
		
		Produto produto = new Produto();
		Marca marca = new Marca();

		String[] dadosProduto = ViewCRUDProduto.ViewMenuSalvarProduto(reader);

		//Get and Set : Usamos get para obter informa��es(ele retorna um valor).Set define valores(n�o retorna valores).
		produto.setNome(dadosProduto[0]);
		produto.setCategoria(dadosProduto[1]);
		marca.setNomeMarca(dadosProduto[2]);
		marca.setPreco(Float.parseFloat(dadosProduto[3]));
		produto.setMarca(marca);

		ListaDeProdutos.getInstance().add(produto);
		
		ManipulacaoArquivoTXT.SalvarArquivoTXT();
		ManipulacaoArquivoXML.SalvarArquivoXML();
		ViewCRUDProduto.ViewMsgFinal(0);
	}


	public static void ListarProdutosSalvos() throws IOException {
		//M�todo a qual carrega a lista e mostra informa��es para o usu�rio
		//getInstance retornar a instancia de um objeto para uma poss�vel compara��o.
		ListaDeProdutos.getInstance().clear();
		ManipulacaoArquivoTXT.LerArquivoTXT();
		ViewCRUDProduto.ViewListaDeProdutosEditada();

	}


	public static void DeletarProduto(BufferedReader reader) throws NumberFormatException, IOException, ParserConfigurationException, TransformerException {
		//M�todo para deletar da lista um produto cadastrado
		int indice = ViewCRUDProduto.ViewMenuListaIndexada("deletar", reader);
		ListaDeProdutos.getInstance().remove(indice);
		// TXT:� uma forma de manipular arquivos com Java, bem como escrever e ler arquivos no formato de texto (txt).
		ManipulacaoArquivoTXT.SalvarArquivoTXT();
		//XML:Cria documentos com dados organizados.
		ManipulacaoArquivoXML.SalvarArquivoXML();
		ViewCRUDProduto.ViewMsgFinal(1);
	}


	public static void EditarProduto(BufferedReader reader) throws NumberFormatException, IOException, ParserConfigurationException, TransformerException {
		//M�todo para editar algum atributo da lista
		int indice = ViewCRUDProduto.ViewMenuListaIndexada("editar", reader);
		String[] dadosEditados = {"",""};
		Produto produtos = ListaDeProdutos.getInstance().get(indice);

		dadosEditados = ViewCRUDProduto.ViewOpcaoEdicao(reader);

		switch(Integer.parseInt(dadosEditados[0])) {
		case 1:
			produtos.setNome(dadosEditados[1]);
			break;
		case 2:
			produtos.setCategoria(dadosEditados[1]);
			break;
		case 3:
			produtos.getMarca().setNomeMarca(dadosEditados[1]);
			break;
		case 4:
			produtos.getMarca().setPreco(Float.parseFloat(dadosEditados[1]));
			break;
		}

		ListaDeProdutos.getInstance().set(indice, produtos);
		ManipulacaoArquivoTXT.SalvarArquivoTXT();
		ManipulacaoArquivoXML.SalvarArquivoXML();
		ViewCRUDProduto.ViewMsgFinal(2);
	}
}


