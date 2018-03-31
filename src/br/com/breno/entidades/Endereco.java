package br.com.breno.entidades;

public class Endereco {

	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String Pais;
	private Usuario usuario;
	
	public Endereco() {
	
	}
	
	public Endereco(String cep, String rua, String bairro, String cidade, String estado, String pais, Usuario usuario) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		Pais = pais;
		this.usuario = usuario;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return Pais;
	}
	public void setPais(String pais) {
		Pais = pais;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Endereco: cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", estado="
				+ estado + ", Pais=" + Pais + ", usuario=" + usuario;
	}

	
}
