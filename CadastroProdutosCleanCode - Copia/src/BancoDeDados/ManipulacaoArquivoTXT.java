package BancoDeDados;
//Pacote utilizado para manipular os bancos de dados

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Modelos.Produto;
import Util.ListaDeProdutos;

public class ManipulacaoArquivoTXT {
	// TXT:� uma forma de manipular arquivos com Java, bem como escrever e ler arquivos no formato de texto (txt).
	

	private static String nomeDoArquivo = "ProdutosCadastrados.txt";

	public static void SalvarArquivoTXT() throws IOException { //Escrever as informa��es cadastradas pelo usu�rio no txt e salvar la
		//BufferedWriter serve para escrever em arquivos de texto;  
		try(BufferedWriter buffer = new BufferedWriter(new FileWriter(nomeDoArquivo ));
				PrintWriter pw = new PrintWriter(buffer)){//PrintWriter serve para ser usado em situa��es que exigem caracteres de escrita em vez de bytes.
			for(Produto a: ListaDeProdutos.getInstance()) {
				pw.println(a);
			}	
		}
}
	
	public static void LerArquivoTXT() throws FileNotFoundException, IOException { //Ler o arquivo do txt e carregar na lista
		//FileWriter serve para escrever diretamente no arquivo
		try (FileWriter arq = new FileWriter(nomeDoArquivo,true)){};
		String line;

		try(BufferedReader reader = new BufferedReader(new FileReader(nomeDoArquivo)))  {

			while((line = reader.readLine())!= null&& !"".equals(line)) { //Checagem de linha para ver se ela n�o esta vazia ou em branco

				Produto produto = new Produto(line);
				ListaDeProdutos.getInstance().add(produto);
			}
		}
	}
}