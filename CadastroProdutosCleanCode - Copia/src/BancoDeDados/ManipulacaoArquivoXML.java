package BancoDeDados;
//Pacote utilizado para manipular os bancos de dados

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import Handler.XMLHandlerProdutos;
import Modelos.Produto;
import Util.ListaDeProdutos;

public class ManipulacaoArquivoXML {
	//XML:Cria documentos com dados organizados.

	private static String nomeDoArquivo = "ProdutosCadastrados.xml";

	public static void SalvarArquivoXML() throws ParserConfigurationException, UnsupportedEncodingException, FileNotFoundException, IOException, TransformerException { //
		
		// Construtores de documentos
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();		
		Document doc = db.newDocument();
		
		
		Element produtosTag = doc.createElement("Produtos"); 
		doc.appendChild(produtosTag);

		for(Produto a : ListaDeProdutos.getInstance()) {

			Element produtoTag = doc.createElement("Produto"); 
			produtoTag.setAttribute("id", "1");
			produtosTag.appendChild(produtoTag);

			Element nomeTag = doc.createElement("Nome"); 
			nomeTag.setTextContent(a.getNome());
			produtoTag.appendChild(nomeTag);

			Element categoriaTag = doc.createElement("Categoria");
			categoriaTag.setTextContent(a.getCategoria());
			produtoTag.appendChild(categoriaTag);

			Element marcaTag = doc.createElement("MarcaProduto");
			produtoTag.appendChild(marcaTag);

			Element nomeMarcaTag = doc.createElement("Marca");
			nomeMarcaTag.setTextContent(a.getMarca().getNomeMarca());
			marcaTag.appendChild(nomeMarcaTag);

			Element precoTag = doc.createElement("Preco");
			precoTag.setTextContent(String.valueOf(a.getMarca().getPreco()));  //Converter para String usa-se String.valueOf
			marcaTag.appendChild(precoTag);

		}

		//Formatação e identação do xml
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();

		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");

		DOMSource source = new DOMSource(doc);

		try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(nomeDoArquivo),"ISO-8859-1")) {
			StreamResult result = new StreamResult(osw);
			trans.transform(source, result);
		}

	}

	public static void LerArquivoXML() throws ParserConfigurationException, SAXException, UnsupportedEncodingException, FileNotFoundException, IOException { //
		//Ler o arquivo do xml
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser = spf.newSAXParser();

		//InputStreamReader serve como um adaptador entre as duas classes - lê bytes de um lado, converte em caracteres do outro, através do uso de uma codificação de caracteres. 
		try(InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeDoArquivo), "ISO-8859-1")) {
		InputSource source = new InputSource(isr);
			XMLHandlerProdutos handler = new XMLHandlerProdutos();
			parser.parse(source, handler);

		}
	}
}
