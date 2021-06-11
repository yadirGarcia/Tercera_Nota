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

@WebServlet(name = "votante", urlPatterns = { "/votante" })
public class VotanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VotanteDao dao;

	///////////////////////////////////////////////////////
	// Builders
	///////////////////////////////////////////////////////
	public VotanteServlet() {
		super();
		dao = new VotanteDao();
	}

	///////////////////////////////////////////////////////
	// Method - Servlet
	///////////////////////////////////////////////////////
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
				case "showValidate":
					showValidate(request, response);
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
				case "validate":
					validate(request, response);
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
	public List<Candidato> listEleccion(String eleccion) {
		int idEleccion = (eleccion != null ? Integer.parseInt(eleccion) : 1);
		CandidatoDao cDao = new CandidatoDao();
		return cDao.findByFieldList("eleccionBean", new Eleccion(idEleccion));
	}

	public List<Tipodocumento> listTipoDocumento() {
		TipoDocumentoDao tDao = new TipoDocumentoDao();
		return tDao.list();
	}

	///////////////////////////////////////////////////////
	// Method - Votante
	///////////////////////////////////////////////////////
	private void showInsert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EstamentoDao a = new EstamentoDao();
		EleccionDao b = new EleccionDao();
		TipoDocumentoDao c = new TipoDocumentoDao();
		request.setAttribute("estamentos", a.list());
		request.setAttribute("tipos", c.list());
		request.setAttribute("procesos", b.list());
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/votante/index.jsp");
		dispatcher.forward(request, response);
	}

	private void showEdit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("votante"));
		Votante aux = dao.find(id);
		EstamentoDao Edao = new EstamentoDao();
		EleccionDao eDao = new EleccionDao();
		TipoDocumentoDao tDao = new TipoDocumentoDao();
		List<Estamento> e = Edao.list();
		List<Tipodocumento> t = tDao.list();
		List<Eleccion> el = eDao.list();
		request.setAttribute("estamentos", e);
		request.setAttribute("tipos", t);
		request.setAttribute("procesos", el);
		request.setAttribute("votante", aux);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/votante/index.jsp");
		dispatcher.forward(request, response);
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String eleccion = request.getParameter("eleccion");
		if (dao == null) {
			init();
		}
		List<Votante> list = (eleccion != null)
				? dao.findByFieldList("eleccionBean", new Eleccion(Integer.parseInt(eleccion)))
				: dao.list();
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/votante/list.jsp");
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String nombre = request.getParameter("nombre");
		// String estamento = request.getParameter("estamento");
		int tipo_documento = Integer.parseInt("" + request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		int eleccion = Integer.parseInt("" + request.getParameter("procesos"));

		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(documento, email, nombre, new Eleccion(eleccion), new Tipodocumento(tipo_documento));
		dao.insert(aux);
		response.sendRedirect("votante?accion=list");
	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		// String estamento = request.getParameter("estamento");
		int tipo_documento = Integer.parseInt("" + request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		int eleccion = Integer.parseInt("" + request.getParameter("procesos"));

		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(id, documento, email, nombre, new Eleccion(eleccion),
				new Tipodocumento(tipo_documento));
		dao.update(aux);
		response.sendRedirect("votante?accion=list");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("votante"));
		dao.delete(id);
		response.sendRedirect("votante?accion=list");
	}

	///////////////////////////////////////////////////////
	// Method - Validate
	///////////////////////////////////////////////////////

	private void showValidate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		EleccionDao eDao = new EleccionDao();
		request.setAttribute("procesos", eDao.list());
		request.setAttribute("tipos", listTipoDocumento());
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/voto/index.jsp");
		dispatcher.forward(request, response);
	}

	private void validate(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		String nombre = request.getParameter("nombre");
		int tipo_documento = Integer.parseInt("" + request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");

		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(documento, email, nombre, new Eleccion(-1), new Tipodocumento(tipo_documento));
		aux = dao.findByField("documento", aux.getDocumento());

		boolean aux2 = false;
		if (aux != null && aux.getNombre().equalsIgnoreCase(nombre)) {
			if (aux.getEmail().equalsIgnoreCase(email)) {
				if (aux.getTipodocumentoBean() != null && aux.getTipodocumentoBean().getId() == tipo_documento) {
					aux2 = true;
				}
			}
		}
		if (aux2) {
			VotoDao vDao = new VotoDao();
			Voto vo = vDao.findByField("votanteBean", new Votante(aux.getId()));
			if (vo == null) {
				request.setAttribute("votanteValidado", aux2);
				String q = request.getParameter("eleccion");
				request.setAttribute("eleccion", q == null ? 1 : q);
				request.setAttribute("listCandidato", listEleccion(q));
			}
		}
		request.setAttribute("votante", aux);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/voto/voto.jsp");
		dispatcher.forward(request, response);
	}
}
