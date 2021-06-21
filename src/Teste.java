

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Teste {

	// *****************************************
	// AUTHOR HYAGO SOUZA E LEONARDO CAMARGOS
	// *****************************************

	static Connection conexao = null;

	public static void main(String[] args) throws Exception {

		String menu = "\n Ol� :) Bem vindo ao sistema !"
				+"\nPor favor Digite uma op��o v�lida para prosseguirmos"
				+"\n1. Cadastro de propriet�rio" + "\n2. Remo��o de propriet�rio"
				+ "\n3. Busca de propriet�rio pelo e-mail"
				+ "\n4. Relat�rio de todos os propriet�rios de ve�culos, considerando o nome do propriet�rio em ordem alfab�tica, a placa e a descri��o do ou dos ve�culos os quais ele � propriet�rio"
				+ "\n5. Altera propriet�rio" + "\n6. Cadastro de ve�culo" + "\n7. Remo��o de ve�culo"
				+ "\n8. Busca ve�culo pela placa"
				+ "\n9. Relat�rio de todos os ve�culos de uma dada cor, com o nome do propriet�rio e a quantidade de portas"
				+ "\n10. Busca ve�culos pela cor" + "\n11.Busca ve�culos pela quantidade de portas"
				+ "\n12.Altera ve�culo" + "\n13. Sair\n\n";

		int opcao;

		do {
			System.out.println(menu);
			Scanner lerOpcao = new Scanner(System.in);
			System.out.print("Digite uma op��o: ");
			opcao = lerOpcao.nextInt();

			switch (opcao) {
			//------------------------------------CASE 1 -cadastro de proprietario------------------------------------------------------------
			case 1: { // 1 - cadastro de proprietario
				Proprietario plida = lerDadosProprietario();
				inserirProprietario(plida);
				System.out.println("Proprietario cadastrado!!!");
				break;
			}
			
			//------------------------------------CASE 2 - remocao de proprietario------------------------------------------------------------
			case 2: { // 2 - remocao de proprietario
				Scanner ler = new Scanner(System.in);
				String email;
				System.out.println("Digite o email da proprietario que quer remover");
				email = ler.next();
				removerProprietarioPeloEmail(email);
				System.out.println("Proprietario Excluido com sucesso");
				break;
			}

			//------------------------------------CASE 3. Busca de propriet�rio pelo e-mail------------------------------------------------------------
			case 3: { // 3. Busca de propriet�rio pelo e-mail
				Scanner ler = new Scanner(System.in);
				String email;
				System.out.println("Digite o email da proprietario para realizar a busca");
				email = ler.next();
				Proprietario pEcontrada = buscarProprietarioPeloEmail(email);

				if (pEcontrada != null) {
					mostrarProprietario(pEcontrada);
				} else {
					System.out.println("N�o h� pessoa cadastrada com esse email");
				}
				break;
			}
			
			//------------------------------------CASE - 5 - Alterar Proprietario pelo email------------------------------------------------------------
			case 5: {
				
				Scanner ler = new Scanner(System.in);
				String email;
				System.out.println("Digite o email do Proprietario que deseja alterar os dados: ");
				email = ler.next();
				Proprietario pSendoAlterada = buscarProprietarioPeloEmail(email);
				if(pSendoAlterada == null) {
					System.out.println("Proprietario n�o Encontrado!!!!");
				}
				else {
					mostrarProprietario(pSendoAlterada);
					System.out.println("\nAgora, vamos atualizar os dados do Proprietario!\n");
					pSendoAlterada = lerDadosProprietario();
					alterarProprietario(pSendoAlterada, email);
					
				}
				
				break;
					
			}
			
			//------------------------------------CASE - 6 - cadastro de veiculo------------------------------------------------------------
			case 6: { // 6 - cadastro de veiculo
				Veiculo vlida = lerDadosVeiculo();
				inserirVeiculo(vlida);
				System.out.println("Veiculo cadastrado!!!");
				break;
			}

			//------------------------------------CASE - 7 - remocao de veiculo------------------------------------------------------------
			case 7: { // 7 - remocao de veiculo

				Scanner ler = new Scanner(System.in);
				String placa;
				System.out.println("Digite a placa do ve�culo desejado");
				placa = ler.next();
				removerVeiculo(placa);
				System.out.println("Veiculo excluido com sucesso!!");
				break;

			}
			
			//------------------------------------CASE - 8 - Busca ve�culo pela placa -------------------------------------------------------------
			case 8: { // 8 - Busca ve�culo pela placa - Diferencial pelo nome e n�o pelo ID e // valida��o da placa funcionando ok!!

				Scanner ler = new Scanner(System.in);
				String placa;
				System.out.println("Digite a placa do ve�culo desejado");
				placa = ler.next();
				VeiculoResponse vEcontrada = buscarVeiculoPelaPlaca(placa);

				if (vEcontrada.getPlaca() != null) {
					mostrarVeiculoPelaPlaca(vEcontrada);
				} else {
					System.out.println("N�o � veiculo com essa placa !!! ......");
				}
				break;

			}

			
			//------------------------------------CASE - 10 -Busca ve�culos pela cor -------------------------------------------------------------
			case 10: {
				// Busca ve�culos pela cor
				Scanner ler = new Scanner(System.in);
				String veiculoBuscado;
				System.out.println("Digite a cor do veiculo que ser� buscado: ");
				veiculoBuscado = ler.next();

				ArrayList<VeiculoResponse> vetVeiculos = listaVeiculosBuscadosPelaCor(veiculoBuscado);

				System.out.println("VEICULOS ENCONTRADOS");

				for (int i = 0; i < vetVeiculos.size(); i++) {
					VeiculoResponse v = vetVeiculos.get(i);
					mostrarVeiculosPelaCorOuQuantidadeDePortas(v);
				}
				break;
			}

			//------------------------------------CASE - 11 - Busca ve�culos pela quantidade de portas -------------------------------------------------------------
			
			case 11: {
				// Busca ve�culos pela quantidade de portas
				Scanner ler = new Scanner(System.in);
				String veiculoBuscadoPelaQtdPortas;
				System.out.println("Digite a quantidade de portas � ser buscada!! :  ");
				veiculoBuscadoPelaQtdPortas = ler.next();

				ArrayList<VeiculoResponse> vetVeiculos = listaVeiculosPelaQuantidadeDePortas(
						veiculoBuscadoPelaQtdPortas);
				// Veiculos
				System.out.println("VEICULOS ENCONTRADOS COM ESSA QUANTIDADE DE PORTAS");

				for (int i = 0; i < vetVeiculos.size(); i++) {
					VeiculoResponse v = vetVeiculos.get(i);
					mostrarVeiculosPelaCorOuQuantidadeDePortas(v);
				}
				break;
			}
			
			//------------------------------------CASE - 12 - Altera Ve�culo -------------------------------------------------------------
			case 12:{
				
				Scanner ler = new Scanner(System.in);
				String placa;
				System.out.println("Digite a placa do Ve�culo que deseja alterar os dados: ");
				placa = ler.next();
				Veiculo vSendoAlterada = buscarVeiculoPelaPlacaUsandoClasseVeiculo(placa);
				if(vSendoAlterada == null) {
					System.out.println("Veiculo n�o Encontrado!!!!");
				}
				else {
					mostrarVeiculoPelaPlacaVeiculo(vSendoAlterada);
					System.out.println("\nAgora, insira os dados para atualizarmos os dados do Ve�culo!\n");
					vSendoAlterada = lerDadosVeiculo();
					alterarVeiculo(vSendoAlterada, placa);
				}
				
				break;
				
				
				
				
			}
			
			default: {
				System.out.println("Op��o inv�lida!!!");
			}
			
			}

		}

		while (opcao != 13);
		System.out.println("Encerrado com sucesso!!!");
	}

	// *******************************************************************
	// M�TODOS PARA MANIPULA��O (PERSIST�NCIA DOS DADOS) NO BANCO DE DADOS E LEITURA/MOSTRAR DADOS 
	// *******************************************************************
	
	
	public static Proprietario lerDadosProprietario() {
		Scanner ler = new Scanner(System.in);

		int codCnh;
		String nome;
		String email;
		float peso;
		String sexo;

		System.out.println("Digite os dados do proprietario a ser cadastrado");

		System.out.print("\n Codigo da CNH:");
		codCnh = Integer.parseInt(ler.next());
		System.out.print("\n Nome :");
		nome = ler.next();
		System.out.print("\n Email .:");
		email = ler.next();
		System.out.print("\n Peso:");
		peso = Float.parseFloat(ler.next());
		System.out.print("\n Sexo:");
		sexo = ler.next();

		Proprietario p = new Proprietario(codCnh, nome, email, peso, sexo);

		return p;

	}

	public static Veiculo lerDadosVeiculo() {

		Scanner ler = new Scanner(System.in);

		String placa;
		String cor;
		String descricao;
		String quantidadePortas;
		int IdProprietario;

		System.out.println("Digite os dados do veiculo a ser cadastrado");

		System.out.print("\n Placa:");
		placa = ler.next();
		System.out.print("\n Cor :");
		cor = ler.next();
		System.out.print("\n Descricao .:");
		descricao = ler.next();
		System.out.print("\n Id do proprietario do veiculo(Caso n�o houver digitar null):");
		IdProprietario = ler.nextInt();
		System.out.print("\n Quantidade de Portas:");
		quantidadePortas = ler.next();

		Veiculo v = new Veiculo(placa, cor, descricao, quantidadePortas, IdProprietario);

		return v;

	}

	public static void mostrarProprietario(Proprietario pEcontrada) {
		System.out.println("\n\n");
		System.out.println("C�digo CNH : " + pEcontrada.getCodCnh());
		System.out.println("Nome : " + pEcontrada.getNome());
		System.out.println("E-mail : " + pEcontrada.getEmail());
		System.out.println("Peso : " + pEcontrada.getPeso());
		System.out.println("Sexo : " + pEcontrada.getSexo());

	}

	public static void mostrarVeiculosPelaCorOuQuantidadeDePortas(VeiculoResponse vEcontrado) {
		System.out.print("\n");
		System.out.print("\nPlaca.........: " + vEcontrado.getPlaca());
		System.out.print("\nCor.......: " + vEcontrado.getCor());
		System.out.print("\nDescricao......: " + vEcontrado.getDescricao());
		System.out.print("\nQuantidade de Portas: " + vEcontrado.getQuantidadePortas());
		System.out.print("\nNome do Proprietario: " + vEcontrado.getNome());
		System.out.println("\n");

	}

	public static void mostrarVeiculoPelaPlaca(VeiculoResponse vEcontrada) {
		System.out.println("\n\n");
		System.out.println("Nome do Proprietario do Ve�culo : " + vEcontrada.getNome());
		System.out.println("Placa do ve�culo : " + vEcontrada.getPlaca());
		System.out.println("Cor do ve�culo : " + vEcontrada.getCor());
		System.out.println("Descri��o do ve�culo : " + vEcontrada.getDescricao());
		System.out.println("Quantidade de portas do ve�culos : " + vEcontrada.getQuantidadePortas());

	}
	
	public static void mostrarVeiculoPelaPlacaVeiculo(Veiculo vEcontrada) {
		System.out.println("\n\n");
		System.out.println("ID do Proprietario do Ve�culo : " + vEcontrada.getIdProprietario());
		System.out.println("Placa do ve�culo : " + vEcontrada.getPlaca());
		System.out.println("Cor do ve�culo : " + vEcontrada.getCor());
		System.out.println("Descri��o do ve�culo : " + vEcontrada.getDescricao());
		System.out.println("Quantidade de portas do ve�culos : " + vEcontrada.getQuantidadePortas());


	}

	public static void inserirProprietario(Proprietario plida) throws SQLException {
		conexao = ConexaoBD.getConexao();
		String sql = "insert into proprietario (codCnh, nome, email, peso,sexo) values (?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setInt(1, plida.getCodCnh());
		stmt.setString(2, plida.getNome());
		stmt.setString(3, plida.getEmail());
		stmt.setFloat(4, plida.getPeso());
		stmt.setString(5, plida.getSexo());

		stmt.execute();

		stmt.close();

	}

	public static void removerProprietarioPeloEmail(String email) throws SQLException {

		conexao = ConexaoBD.getConexao();

		String sql = "delete from proprietario where email = ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, email);

		stmt.execute();

		stmt.close();

	}
	

	public static void inserirVeiculo(Veiculo vlida) throws SQLException {
		conexao = ConexaoBD.getConexao();
		String sql = "insert into veiculo (idVeiculo,placa, cor, descricao,IdProprietario,quantidadePortas) values (null,?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, vlida.getPlaca());
		stmt.setString(2, vlida.getCor());
		stmt.setString(3, vlida.getDescricao());
		stmt.setInt(4, vlida.getIdProprietario());
		stmt.setString(5, vlida.getQuantidadePortas());

		stmt.execute();

		stmt.close();

	}


	
	public static void removerVeiculo(String placa) throws SQLException {

		conexao = ConexaoBD.getConexao();
		String sql = "delete from veiculo where placa = ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, placa);

		stmt.execute();

		stmt.close();

	}

	public static Proprietario buscarProprietarioPeloEmail(String email) throws SQLException {

		conexao = ConexaoBD.getConexao();

		String sql = "select * from proprietario where email like ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, email);

		ResultSet resultado = stmt.executeQuery(); // importar a classe resultset para recuperar os dados que o select
													// selecionou

		Proprietario p = null;

		if (resultado.next()) {
			p = new Proprietario(resultado.getInt("codCnh"), resultado.getString("nome"), resultado.getString("email"),
					resultado.getFloat("peso"), resultado.getString("sexo")

			);

		}
		resultado.close();

		return p;
	}
	
	public static Veiculo buscarVeiculoPelaPlacaUsandoClasseVeiculo(String placa) throws SQLException {

		conexao = ConexaoBD.getConexao();

//		String sql = "select * from veiculo where placa like ?";

		String sql = "select * from veiculo where placa like ?";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, placa);

		ResultSet resultado = stmt.executeQuery(); // importar a classe resultset para recuperar os dados que o select
													// selecionou

		Veiculo vec = new Veiculo();

		if (resultado.next()) {
			vec.setIdProprietario(resultado.getInt(1));
			vec.setPlaca(resultado.getString(2));
			vec.setCor(resultado.getString(3));
			vec.setDescricao(resultado.getString(4));
			vec.setQuantidadePortas(resultado.getString(5));

		}
		resultado.close();

		return vec;
	}

	public static VeiculoResponse buscarVeiculoPelaPlaca(String placa) throws SQLException {

		conexao = ConexaoBD.getConexao();

//		String sql = "select * from veiculo where placa like ?";

		String sql = "select v.placa, v.cor , v.descricao, v.quantidadePortas ,p.nome from veiculo v left join proprietario p  on  v.idProprietario = p.idProprietario \r\n"
				+ "where v.placa = ? order by p.nome;";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, placa);

		ResultSet resultado = stmt.executeQuery(); // importar a classe resultset para recuperar os dados que o select
													// selecionou

		VeiculoResponse vec = new VeiculoResponse();

		if (resultado.next()) {
			vec.setPlaca(resultado.getString(1));
			vec.setCor(resultado.getString(2));
			vec.setDescricao(resultado.getString(3));
			vec.setQuantidadePortas(resultado.getString(4));
			vec.setNome(resultado.getString(5));

		}
		resultado.close();

		return vec;
	}

	
	public static ArrayList<VeiculoResponse> listaVeiculosBuscadosPelaCor(String cor) throws Exception {
		ArrayList<VeiculoResponse> vetContas = new ArrayList<VeiculoResponse>();

		conexao = ConexaoBD.getConexao();

		// Por exemplo, SELECT * FROM aula_bd.conta where agencia='1537-6';

		String sql = "select v.placa, v.cor , v.descricao, v.quantidadePortas ,p.nome from veiculo v left join proprietario p  on  v.idProprietario = p.idProprietario \r\n"
				+ "where v.cor = ? order by p.nome;";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cor);
		ResultSet resultado = stmt.executeQuery();

		while (resultado.next()) {

			VeiculoResponse vec = new VeiculoResponse();

			vec.setPlaca(resultado.getString(1));
			vec.setCor(resultado.getString(2));
			vec.setDescricao(resultado.getString(3));
			vec.setQuantidadePortas(resultado.getString(4));
			vec.setNome(resultado.getString(5));

			vetContas.add(vec);
		}

		resultado.close();
		stmt.close();

		return vetContas;
	}

	
	public static ArrayList<VeiculoResponse> listaVeiculosPelaQuantidadeDePortas(String quantidadePortas)
			throws Exception {
		ArrayList<VeiculoResponse> vetVeiculo = new ArrayList<VeiculoResponse>();

		conexao = ConexaoBD.getConexao();

		String sql = "select v.placa, v.cor , v.descricao, v.quantidadePortas ,p.nome from veiculo v left join proprietario p  on  v.idProprietario = p.idProprietario \r\n"
				+ "where v.quantidadePortas = ? order by p.nome";

		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, quantidadePortas);
		ResultSet resultado = stmt.executeQuery();

		while (resultado.next()) {

			VeiculoResponse vec = new VeiculoResponse();

			vec.setPlaca(resultado.getString(1));
			vec.setCor(resultado.getString(2));
			vec.setDescricao(resultado.getString(3));
			vec.setQuantidadePortas(resultado.getString(4));
			vec.setNome(resultado.getString(5));

			vetVeiculo.add(vec);
		}

		resultado.close();
		stmt.close();

		return vetVeiculo;
	}
	
	
    public static void alterarProprietario(Proprietario pSendoAlterada, String email) throws SQLException {
  		Connection conexao = ConexaoBD.getConexao();

  		String sql = "UPDATE proprietario SET codCnh = ?, nome = ?,  email = ?, peso = ? ,sexo = ? "
				+ "WHERE email LIKE ?";
  		
  		PreparedStatement stmt = conexao.prepareStatement(sql);

  		stmt.setInt(1, pSendoAlterada.getCodCnh());
		stmt.setString(2, pSendoAlterada.getNome());
		stmt.setString(3, pSendoAlterada.getEmail());
		stmt.setFloat(4, (float) pSendoAlterada.getPeso());
		stmt.setString(5,pSendoAlterada.getSexo());
		stmt.setString(6, email);
		
		System.out.println("Parab�ns , Proprietario alterado com sucesso!!!!!!");
		
		stmt.execute();
		stmt.close();
      }
    
    //Observa��o : Realizamos a desativa��o de valida��o da chave estrangeira no banco para realizar esse update!! Ou seja o id do proprietario nunca vai ser alterado NESSSE UPDATE ent�o n�o era necess�rio verifica-lo e quebrar nosso script toda vez 
    //Att. Leo e hyago
    
    public static void alterarVeiculo(Veiculo vSendoAlterada, String placa) throws SQLException {
  		Connection conexao = ConexaoBD.getConexao();

  		String sql = "update veiculo set IdProprietario = ? , placa = ? ,  cor = ?, descricao = ? ,quantidadePortas = ? where placa like ?";
  		
  		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, vSendoAlterada.getIdProprietario());
		stmt.setString(2, vSendoAlterada.getPlaca());
		stmt.setString(3, vSendoAlterada.getCor());
		stmt.setString(4, vSendoAlterada.getDescricao());
		stmt.setString(5, vSendoAlterada.getQuantidadePortas());
		stmt.setString(6, placa);
		
		System.out.println("Parab�ns , Veiculo alterado com sucesso!!!!!! At� mais Xd :)");
		
		stmt.execute();
		stmt.close();
		

      }
    
}
