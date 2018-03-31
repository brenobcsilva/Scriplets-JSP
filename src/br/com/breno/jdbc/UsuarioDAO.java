package br.com.breno.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.breno.entidades.Endereco;
import br.com.breno.entidades.Usuario;

public class UsuarioDAO {
	Connection con = Conexao.getConnection();

	public void cadastrarEndereco(Endereco novoUsuario) {
		String sql = "insert into endereco (cep,rua,bairro,cidade,estado,pais)values(?,?,?,?,?,?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, novoUsuario.getCep());
			preparador.setString(2, novoUsuario.getRua());
			preparador.setString(3, novoUsuario.getBairro());
			preparador.setString(1, novoUsuario.getCidade());
			preparador.setString(2, novoUsuario.getEstado());
			preparador.setString(3, novoUsuario.getPais());
			
			preparador.execute();
			preparador.close();
			System.out.println("Novo endereco cadastrado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void cadastrar(Usuario usuario) {
		String sql = "insert into usuario (nome,login, senha)values(?,?,?)";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());

			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Usuario usuario) {
		String sql = "update usuario set nome=?, login=?, senha=? where id =?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro " + e.getMessage());
		}

	}

	public void excluir(Usuario usuario) {
		String sql = "DELETE FROM USUARIO where id=?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getId());

			preparador.execute();
			preparador.close();
			System.out.println("Excluido cmo sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvar(Usuario usu) {
		if (usu.getId() != null && usu.getId() != 0) {
			alterar(usu);
		} else {
			cadastrar(usu);
		}

	}

	public List<Usuario> buscarTodos() {
		String sql = "SELECT * FROM USUARIO";

		PreparedStatement preparador;

		List<Usuario> lista = new ArrayList<>();
		try {
			preparador = con.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();

				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));

				lista.add(usuario);
			}
			preparador.close();
			System.out.println("Buscar todos");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario buscarPorId(Integer id) {
		String sql = "SELECT * FROM Usuario where id=?";
		Usuario usu = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usu;
	}

	public List<Usuario> buscarPorNome(String nome) {
		String sql = "SELECT * FROM Usuario where nome=?";
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + nome + "%");

			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				Usuario usu = new Usuario();
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setLogin(resultado.getString("login"));
				usu.setSenha(resultado.getString("senha"));
				lista.add(usu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Busca por login e senha de Usuario
	 * @param usuario objeto com login e senha a ser consultado no banco
	 * @return Null quando não encontra no banco ou um Ponteiro a objeto do usuario completo quando encontra
	 */
	
	public Usuario autenticar(Usuario usuario) {
		String sql = "SELECT * FROM Usuario where login=? and senha=?";
		Usuario usuRetorno = null;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuRetorno.getLogin());
			preparador.setString(2, usuRetorno.getSenha());

			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuRetorno;
	}

	public boolean existeUsuarioAutenticar(Usuario usuario) {
		String sql = "SELECT * FROM Usuario where login=? and senha=?";
		boolean ret = false;
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());

			ResultSet resultado = preparador.executeQuery();

			ret = resultado.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
