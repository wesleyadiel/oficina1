package RealizarVenda;

public class MercadoriaVendaDTO {
	private Integer idMercadoria;
	private String descricao;
	private Integer idCliente;
	private String nomeCompleto;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
