# rentmanager
School java project
Fonctionnalités qui marchent :
1.	La page Home
•	Les fonctions compteurs pour utilisateurs, voitures, et réservations sont fonctionnelles.
2.	La page utilisateur
•	Affichage de tous les utilisateurs
•	L’ajout d’un nouvel utilisateur
•	Le détails des réservations d’un utilisateur
•	La modification d’un utilisateur
•	La suppression d’un utilisateur
•	Le nom et le prénom d’un client doivent faire au moins 3 caractères
•	Tous les champs lors de la création d’un utilisateur doivent être renseignés
3.	La page Voitures
•	Affichage de toutes les voitures
•	Ajout d’une nouvelle voiture
•	Une voiture doit avoir au moins 2 places et au maximum 9 places, un constructeur renseigné.
•	La modification d’une voiture
•	La suppression d’une voiture
4.	La page réservation
•	Affichage de toutes les réservations
•	Ajout d’une réservation
•	Modification d’une réservation
•	Suppression d’une réservation


Fonctionnalités qui ne marchent pas ou qui ne sont pas implémentées:
•	La contrainte âge d’un utilisateur (qu’il doit être majeur). J’ai réussi à récupérer l’âge de l’utilisateur et ne pas créer de nouvel utilisateur dans la servlet, mais je n’ai pas réussi mettre un message d’alerte dans les fichier create.jsp.
•	Un client ayant une adresse mail déjà prise ne peut pas être créé.
•	Problème sur l’affichage du nom du client et de la voiture, j’ai eu du mal à faire le lien entre le jsp et la servlet. Lors de sa mise en place soit je n’arrivais pas à récupérer l’idVehicle et l’idClient, ou bien je n’arriverais pas à afficher le nom correspondant.
•	Les contraintes pour la réservation :
o	Une voiture ne peux pas être réservé 2 fois le même jour
o	Une voiture ne peux pas être réservé plus de 7 jours de suite par le même utilisateur
o	Une voiture ne peux pas être réservé 30 jours de suite sans pause
o	Si un client ou un véhicule est supprimé, alors il faut supprimer les réservations associées

