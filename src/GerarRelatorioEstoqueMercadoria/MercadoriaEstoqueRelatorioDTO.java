package GerarRelatorioEstoqueMercadoria;

public class MercadoriaEstoqueRelatorioDTO {
	
	private Integer idMercadoria;
	private String descricao;
	private Double quantidade;
	
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
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
