package RealizarVenda;

public class MercadoriaVenda {
	private Integer idMercadoria;
	private Integer idCliente;
	private Double quantidade;
	private Double valorMercadoria;
	private Double valorVenda;
	private Double valorDesconto;
	
	public Integer getIdMercadoria() {
		return idMercadoria;
	}
	public void setIdMercadoria(Integer idMercadoria) {
		this.idMercadoria = idMercadoria;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorMercadoria() {
		return valorMercadoria;
	}
	public void setValorMercadoria(Double valorMercadoria) {
		this.valorMercadoria = valorMercadoria;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
}
