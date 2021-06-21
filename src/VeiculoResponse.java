
public class VeiculoResponse {

	// placa, cor, descrição, quantidade de portas (2 ou 4)

	String placa;
	String cor;
	String descricao;
	String quantidadePortas;
	String nome;

	public VeiculoResponse() {

	}

	public VeiculoResponse(String placa, String cor, String descricao, String quantidadePortas, String nome) {
		this.placa = placa;
		this.cor = cor;
		this.descricao = descricao;
		this.quantidadePortas = quantidadePortas;
		this.nome = nome;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getQuantidadePortas() {
		return quantidadePortas;
	}

	public void setQuantidadePortas(String quantidadePortas) {
		this.quantidadePortas = quantidadePortas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
