package PrevisaoDeCompras;

public class PrevisaoCompraRelatorioDTO {

	private Integer idMercadoria;
	private String descricao;
	private Double quantidadeVendida;
	private Double quantidadeEstoque;
	private Double quantidadeCompra;
	
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
	public Double getQuantidadeVendida() {
		return quantidadeVendida;
	}
	public void setQuantidadeVendida(Double quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}
	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Double getQuantidadeCompra() {
		return quantidadeCompra;
	}
	public void setQuantidadeCompra(Double quantidadeCompra) {
		this.quantidadeCompra = quantidadeCompra;
	}
}
