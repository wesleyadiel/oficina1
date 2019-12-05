package Encomenda;

import java.time.LocalDate;
import java.util.Date;

public class Encomenda {
	private Integer idMercadoria;
	private Integer idCliente;
	private Double quantidade;
	private LocalDate data;
	private Boolean finalizada;
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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Boolean getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}
}
