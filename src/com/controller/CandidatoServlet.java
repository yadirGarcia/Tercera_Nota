package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import com.entity.*;

@WebServlet(name = "candidato", urlPatterns = { "/candidato" })
public class CandidatoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CandidatoDao dao;

	public CandidatoServlet() {
		super();
		dao = new CandidatoDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			try {
				switch (accion) {
				case "showInsert":
					showInsert(request, response);
					break;
				case "delete":
					delete(request, response);
					break;
				case "showEdit":
					showEdit(request, response);
					break;
				case "list":
					list(request, response);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			try {
				switch (accion) {
				case "insert":
					insert(request, response);
					break;
				case "edit":
					edit(request, response);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		doGet(request, response);
	}

	///////////////////////////////////////////////////////
	// Method
	///////////////////////////////////////////////////////
	private void showInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EleccionDao eDao = new EleccionDao();
		List<Eleccion> list = eDao.list();
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/candidato/index.jsp");
		dispatcher.forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String eleccion = request.getParameter("eleccion");
		if (dao == null) {
			init();
		}
		Eleccion e = new Eleccion((eleccion != null) ? Integer.parseInt(eleccion) : -1);
		List<Candidato> list = (eleccion != null) ? dao.findByFieldList("eleccionBean", e) : dao.list();
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/candidato/list.jsp");
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String eleccion = request.getParameter("eleccion");
		String numero = request.getParameter("numero");
		Candidato c = new Candidato(apellido, documento, nombre, Integer.parseInt(numero),
				new Eleccion(Integer.parseInt(eleccion)));
		if (dao == null) {
			init();
		}
		dao.insert(c);
		response.sendRedirect("candidato?accion=list");
	}
	
	private void showEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		if (dao == null) {
			this.init();
		}
		Candidato c = dao.find(Integer.parseInt(id));
		EleccionDao eDao = new EleccionDao();
		List<Eleccion> list = eDao.list();
		request.setAttribute("list", list);
		request.setAttribute("candidato", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/candidato/index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String eleccion = request.getParameter("eleccion");
		String numero = request.getParameter("numero");
		Candidato c = new Candidato(Integer.parseInt(id), apellido, documento, nombre, Integer.parseInt(numero),
				new Eleccion(Integer.parseInt(eleccion)));
		if (dao == null) {
			this.init();
		}
		dao.update(c);
		response.sendRedirect("candidato?accion=list");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		if (dao == null) {
			this.init();
		}
		dao.delete(Integer.parseInt(id));
		response.sendRedirect("candidato?accion=list");
	}
}
