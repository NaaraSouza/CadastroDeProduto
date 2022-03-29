package Modelos;

public class Marca {
	
	private String nomeMarca;
	private Float preco;
	
	
	public Marca(String[] dados) {
		
		
		String[] nomeMarca = dados[3].split("=");
		String[] preco = dados[4].split("=");
		
		this.nomeMarca = nomeMarca[1].trim();
		this.preco = Float.parseFloat(preco[1].trim());
		
	}
	
	public Marca() {
		super();
	}
	public String getNomeMarca() {
		return nomeMarca;
	}
	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	@Override
	public String toString() {
		return nomeMarca + ", Preço= " + preco;
	}
	
	

}