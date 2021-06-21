
public class Veiculo {

	//placa, cor, descrição, quantidade de portas (2 ou 4)
	
	String placa;
	String cor;
	String descricao;
	String quantidadePortas;
	int idProprietario;
	
	public Veiculo() {
		
	}
	
	public Veiculo(String placa, String cor, String descricao, String quantidadePortas,int idProprietario) {
		this.placa = placa;
		this.cor = cor;
		this.descricao = descricao;
		this.quantidadePortas = quantidadePortas;
		this.idProprietario = idProprietario;
	}

	public int getIdProprietario() {
		return idProprietario;
	}

	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
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
	
	
}
