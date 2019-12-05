package CadastrarSubGrupo;

public class SubgrupoDTO {
	private Integer idSubgrupo;
	private String descricao;
	private String grupo;
	
	public Integer getIdSubgrupo() {
		return idSubgrupo;
	}
	public void setIdSubgrupo(Integer idSubgrupo) {
		this.idSubgrupo = idSubgrupo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}
}
