package br.com.breno.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.breno.entidades.Usuario;
import br.com.breno.jdbc.UsuarioDAO;

@WebServlet(name = "AutenticadorController", urlPatterns = { "/autcontroller.do" })
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Autenticador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		
		if(sessao!=null) {
			sessao.invalidate();
		}
		response.sendRedirect("login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		UsuarioDAO usudao = new UsuarioDAO();

		Usuario usuretorno = usudao.autenticar(usuario);
		if (usuretorno != null) {
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			sessao.setAttribute("usulogador", usuretorno);

			request.getRequestDispatcher("index.jsp");
		} else {
			response.sendRedirect("login.html");
		}
	}

}
