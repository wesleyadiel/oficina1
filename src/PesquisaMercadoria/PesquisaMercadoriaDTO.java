package PesquisaMercadoria;

public class PesquisaMercadoriaDTO {
	private Integer idMercadoria;
	private String descricao;
	private Double qtdEstoque;
	private Double preco;
	
	public Integer getIdMercadoria() {
		return idMercadoria;
	}
	public void setIdMercadoria(Integer idMercadoria) {
		this.idMercadoria = idMercadoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Double qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
