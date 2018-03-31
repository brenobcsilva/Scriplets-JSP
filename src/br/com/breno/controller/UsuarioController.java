package br.com.breno.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.breno.entidades.Usuario;
import br.com.breno.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UsuarioDAO usudao = new UsuarioDAO();
	Usuario usuario = new Usuario();

	public UsuarioController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Chamando Método GET");
		
		String acao = req.getParameter("acao");
		if (acao != null && acao.equals("exc")) {
			String id = req.getParameter("id");
			usuario.setId(Integer.parseInt(id));
			usudao.excluir(usuario);
			resp.sendRedirect("usucontroller.do?acao=lis");
		}

		
		if (acao != null && acao.equals("alt")) {
			String id = req.getParameter("id");
			usuario = usudao.buscarPorId(Integer.parseInt(id));
			req.setAttribute("usuario", usuario);
			RequestDispatcher saida = req.getRequestDispatcher("frmusuario.jsp");
			saida.forward(req, resp);
		}

		if (acao != null && acao.equals("cad")) {
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			req.setAttribute("usuario", usuario);
			RequestDispatcher saida = req.getRequestDispatcher("frmusuario.jsp");
			saida.forward(req, resp);
		}

		
		if (acao != null && acao.equals("lis")) {
			List<Usuario> lista = usudao.buscarTodos();

			req.setAttribute("lista", lista);
			req.setAttribute("meunome", "Breno");

			RequestDispatcher saida = req.getRequestDispatcher("listausuarios.jsp");
			saida.forward(req, resp);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Metodo POST");

		String id = request.getParameter("txtid");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");

		Usuario usu = new Usuario();
		if (id != null && id != "" && id != "0") {
			usu.setId(Integer.parseInt(id));
		}

		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);

		UsuarioDAO usudao = new UsuarioDAO();
		usudao.salvar(usu);

		PrintWriter saida = response.getWriter();
		saida.print("Cadastrado");
	}

}
