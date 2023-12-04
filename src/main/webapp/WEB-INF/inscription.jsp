<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
</head>
<body>

<h2>Inscription</h2>

<form action="inscription" method="post">
    Nom: <input type="text" name="nom" required><br>
    Pr�nom: <input type="text" name="prenom" required><br>
    Email: <input type="email" name="email" required><br>
    Mot de passe: <input type="password" name="password" required><br>
    Num�ro de t�l�phone: <input type="text" name="numTel" required><br>
   <label for="sexe">Sexe :</label>
    <input type="radio" id="masculin" name="sexe" value="M" checked>
    <label for="masculin">Masculin</label>
    <input type="radio" id="feminin" name="sexe" value="F">
    <label for="feminin">F�minin</label><br>
    Type: <select name="type" id="type" onchange="afficherChampsSpecifiques()">
        <option value="patient" selected>Patient</option>
        <option value="medecin">M�decin</option>
    </select><br>
    <div id="champsMedecin" style="display: none;">
        Sp�cialit�: <input type="text" name="specialite"><br>
        Adresse: <input type="text" name="adresse" ><br>
    </div>

    <!-- Champs sp�cifiques pour le type "Patient" -->
    <div id="champsPatient" style="display: none;">
        Contact d'urgence: <input type="text" name="contact_urgence"><br>
    </div>
    <%-- Afficher le message d'erreur s'il y en a un --%>
        <c:if test="${not empty requestScope.erreurMessage}">
            <p style="color: red;">${requestScope.erreurMessage}</p>
        </c:if>
    <input type="submit" value="S'inscrire">

    <script>
        function afficherChampsSpecifiques() {
            console.log("Fonction appel�e");
            var typeSelectionne = document.getElementById("type");
            var champsMedecin = document.getElementById("champsMedecin");
            var champsPatient = document.getElementById("champsPatient");

            // Masquer tous les champs sp�cifiques
            champsMedecin.style.display = "none";
            champsPatient.style.display = "none";

            // Afficher les champs sp�cifiques en fonction du type s�lectionn�
            if (typeSelectionne.value === "medecin") {
                champsMedecin.style.display = "block";
            } else if (typeSelectionne.value === "patient") {
                champsPatient.style.display = "block";
            }
        }
    </script>
</form>

</body>
</html>
