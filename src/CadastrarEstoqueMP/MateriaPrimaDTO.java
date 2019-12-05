package CadastrarEstoqueMP;

public class MateriaPrimaDTO {
	private Integer idMercadoria;
	private Integer codigo;
	private String descricao;
	private Double quantidade;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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
	public Integer getIdMercadoria() {
		return idMercadoria;
	}
	public void setIdMercadoria(Integer idMercadoria) {
		this.idMercadoria = idMercadoria;
	}
}
