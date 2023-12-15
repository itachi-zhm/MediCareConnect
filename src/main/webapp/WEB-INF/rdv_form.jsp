<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fixer un rendez-vous</title>
     <style>
        /* Ajouter un style pour la section de liste avec défilement */
        .doctor-list {
            height: 200px; /* Hauteur fixe */
            overflow: auto; /* Ajouter une barre de défilement en cas de dépassement de la hauteur */
        }
    </style>
    
</head>
<body>
    <h1>Fixer un rendez-vous avec le médecin</h1>

    <form action="FixerRendezVousServlet" method="post">
    
    	<div class="doctor-list">
        <table border="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Spécialité</th>
                <th>Adresse</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="medecin" items="${listeMedecins}">
                <tr>
                    <td>${medecin.id_medecin}</td>
                    <td>${medecin.nom}</td>
                    <td>${medecin.prenom}</td>
                    <td>${medecin.email}</td>
                    <td>${medecin.specialite}</td>
                    <td>${medecin.adresse}</td>
                    <td>
                    	<input type="checkbox" name="choix_med">
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    	</div>
    
        <label for="startDate">Date de début de disponibilité :</label>
        <input type="date" id="startDate" name="startDate" required><br>

        <label for="endDate">Date de fin de disponibilité :</label>
        <input type="date" id="endDate" name="endDate" required onchange="setMinMaxDate()"><br>

        <label for="startTime">Heure de début de disponibilité :</label>
        <input type="time" id="startTime" name="startTime" required><br>

        <label for="endTime">Heure de fin de disponibilité :</label>
        <input type="time" id="endTime" name="endTime" required onchange="setMinMaxTime()"><br>


        <input type="submit" value="Fixer le rendez-vous">
    </form>
</body>
</html>
