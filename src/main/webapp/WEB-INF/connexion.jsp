<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
    <title>Connexion</title>
</head>
<body>
    <h2>Connexion</h2>

    <form action="connexion" method="post">
        <!-- Les champs du formulaire -->
        <label for="email">Email:</label>
        <input type="email" name="email" required><br>

        <label for="password">Mot de passe:</label>
        <input type="password" name="password" required><br>

        <input type="submit" value="Se connecter">
    </form>
</body>
</html>