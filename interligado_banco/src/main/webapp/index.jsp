<%@ page import="java.util.List" %>
<%@ page import="br.com.teste.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página Inicial</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            color: #333;
            padding: 40px;
        }

        h1, h2 {
            color: #2c3e50;
        }

        form {
            background: #ffffff;
            padding: 20px;
            margin-bottom: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            max-width: 400px;
        }

        input[type="text"],
        input[type="email"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-top: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #3498db;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .no-users {
            color: #888;
            font-style: italic;
        }
        .form-container {
    display: flex;
    gap: 20px;
    flex-wrap: wrap;
    margin-bottom: 30px;
}

.form-box {
    flex: 1;
    min-width: 300px;
}
    </style>
</head>
<body>
    <h1>Bem-vindo ao sistema</h1>
    
   <div class="form-container">
    <div class="form-box">
        <h2>Cadastrar Novo Usuário</h2>
        <form method="post" action="usuarios">
            Nome:
            <input type="text" name="nome" required>
            Email:
            <input type="email" name="email" required>
            <input type="submit" value="Cadastrar">
        </form>
    </div>

    <div class="form-box">
        <h2>Deletar Usuário</h2>
        <form method="post" action="usuarios">
            ID do usuário:
            <input type="number" name="deleteId" id="deleteId" required>
            <input type="submit" value="Deletar">
        </form>
    </div>
</div>

    <h2>Lista de Usuários</h2>

    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        if (usuarios != null && !usuarios.isEmpty()) {
    %>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
            </tr>
            <%
                for (Usuario u : usuarios) {
            %>
            <tr>
                <td><%= u.getId() %></td>
                <td><%= u.getNome() %></td>
                <td><%= u.getEmail() %></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p class="no-users">Nenhum usuário encontrado.</p>
    <%
        }
    %>

</body>
</html>