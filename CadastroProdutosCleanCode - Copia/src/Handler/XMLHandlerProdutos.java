package Handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import Modelos.Marca;
import Modelos.Produto;

public class XMLHandlerProdutos extends DefaultHandler{
	//Handler é um “Manipulador”
	private StringBuilder texto;
	Produto produto;
	Marca marcaProduto;
	
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Início do Documento");
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Fim do Documento");
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { //Quando começa uma tag
		
		if(qName.equals("Produto")) { 
			produto = new Produto();
			marcaProduto = new Marca();
		} else {
			texto = new StringBuilder();
		}
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException { //Quando termina uma tag

		if(qName.equals("Nome")) {
			produto.setNome(texto.toString());
		} else if(qName.equals("Categoria")) {
			produto.setCategoria(texto.toString());
		} else if(qName.equals("Marca")) {
			marcaProduto.setNomeMarca(texto.toString());
		} else if(qName.equals("Preco")) {
			marcaProduto.setPreco(Float.parseFloat(texto.toString()));
		}
	}
	
	//Quando ele termina de ler o arquivo XML, ele carega o objeto
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {  //Quando tem conteudo dentro de uma tag, ele lê caractere por caractere
		
		texto.append(ch, start, length); //Quando receber um caractere vai junta-los e construir a String formando as palavras
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		
	}
	
	
}
