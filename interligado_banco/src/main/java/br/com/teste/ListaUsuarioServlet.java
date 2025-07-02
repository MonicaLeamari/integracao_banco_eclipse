package br.com.teste;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;


public class ListaUsuarioServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try {
        	Class.forName("org.postgresql.Driver");
            Connection con = Conexao.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email")
                );
                listaUsuarios.add(usuario);
            }

            rs.close();
            stmt.close();
            con.close();

            request.setAttribute("usuarios", listaUsuarios);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro ao listar usuários", e);
        }
    }
	
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

		 String nome = request.getParameter("nome");
		    String email = request.getParameter("email");
		    String deleteId = request.getParameter("deleteId");

		    try {
		        Class.forName("org.postgresql.Driver");
		        Connection con = Conexao.getConnection();

		        if (deleteId != null && !deleteId.isEmpty()) {
		         
		            int id = Integer.parseInt(deleteId);
		            String sql = "DELETE FROM usuarios WHERE id = ?";
		            PreparedStatement ps = con.prepareStatement(sql);
		            ps.setInt(1, id);
		            int rows = ps.executeUpdate();
		            System.out.println("Linhas deletadas: " + rows);
		            ps.close();
		        } else if (nome != null && email != null) {
		            
		            String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
		            PreparedStatement ps = con.prepareStatement(sql);
		            ps.setString(1, nome);
		            ps.setString(2, email);
		            ps.executeUpdate();
		            ps.close();
		        }

		        con.close();
		        response.sendRedirect("usuarios");

		    } catch (Exception e) {
		        throw new ServletException("Erro ao processar requisição POST", e);
		    }
	    }
	 
	 
    
    
}