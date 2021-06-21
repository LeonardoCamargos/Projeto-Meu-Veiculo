
public class Proprietario {
	
	//nome, e-mail, peso, sexo e número da CNH
	
	int codCnh;
	String nome;
	String email;
	float peso;
	String sexo;
	
	
	
	public Proprietario() {
		
	}

	
	public Proprietario(int codCnh, String nome, String email, float peso, String sexo) {
		this.codCnh = codCnh;
		this.nome = nome;
		this.email = email;
		this.peso = peso;
		this.sexo = sexo;
	}
	
	
	
	public int getCodCnh() {
		return codCnh;
	}
	public void setCodCnh(int codCnh) {
		this.codCnh = codCnh;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	


	
}
