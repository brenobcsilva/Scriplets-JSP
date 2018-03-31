package br.com.breno.jdbc.teste;

import java.util.List;

import br.com.breno.entidades.Endereco;
import br.com.breno.entidades.Usuario;
import br.com.breno.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {

		 testAlterar();
//		 testCadastrar();
		// testExcluir();
		// testBuscarTodos();
//		testAutenticar();
	}

	private static void testCadastrarEndereco() {
		Endereco end = new Endereco();

		end.setCep("36.259-858");
		end.setRua("São Pedro");
		end.setBairro("Maria Hermano");
		end.setCidade("BH");
		end.setEstado("MG");
		end.setPais("Brasil");

		UsuarioDAO usudao = new UsuarioDAO();
		usudao.cadastrarEndereco(end);
	}
	
	private static void testCadastrar() {
		Usuario usu = new Usuario();

		usu.setNome("ddd");
		usu.setLogin("brddeno");
		usu.setSenha("ddddsssdddddd");

		UsuarioDAO usudao = new UsuarioDAO();
		usudao.cadastrar(usu);
	}

	private static void testAlterar() {
		Usuario usu = new Usuario();

		usu.setNome("bbbbbbbbbbbb");
		usu.setLogin("bcbbbbbbb");
		usu.setSenha("bcbcbcbbbbbbbbbbb");
		usu.setId(3);

		UsuarioDAO usudao = new UsuarioDAO();
		usudao.alterar(usu);
	}

	private static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(1);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usu);
	}

	private static void testBuscarTodos() {

		UsuarioDAO usudao = new UsuarioDAO();

		List<Usuario> listaResultado = usudao.buscarTodos();

		for (Usuario u : listaResultado) {
			System.out.println(u.getNome());
			System.out.println(u.getLogin());
			System.out.println(u.getSenha());
		}
	}

	private static void testAutenticar() {

		Usuario usuario = new Usuario();
		usuario.setLogin("juam");
		usuario.setSenha("123");

		UsuarioDAO usudao = new UsuarioDAO();
		System.out.println(usudao.autenticar(usuario).getNome());
	}

	public static void testBuscarPorId() {
		UsuarioDAO usudao = new UsuarioDAO();
		System.out.println(usudao.buscarPorId(10));

		Usuario usuRetorno = usudao.buscarPorId(1);
		if (usuRetorno != null) {
			System.out.println("nome: " + usuRetorno.getNome());
		}
	}
}