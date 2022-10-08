# ProjetFullStack

Lien des données des lieux de vaccination : https://www.data.gouv.fr/fr/datasets/lieux-de-vaccination-contre-la-covid-19/

Générer diagramme UML dans PgAdmin :
clique droit sur covid-db > Generate ERD

Swagger url: http://localhost:8080/swagger-ui/

## TD1
![image](https://user-images.githubusercontent.com/67641786/191991447-46ee7022-9c08-46ba-ac32-1d26369c394f.png)

*TODO*
- Faire des tests unitaires
- regarder DTO
- Admin : ne peut chercher que les médecins de son centre
- Admin : gestion des réservations dans son centre
- Mettre en place Login/mdp
- Mettre des restrictions sur /admin (basic Auth)
- Mettre des vérifications avant d'appeler une méthode (ex : vérifier que ce soit bien un admin qui gère les médecins de son centre (que ce soit par le front ou pas postman) ; autre ex : vérifier que ce soit la personne qui possède le compte qui veut supprimer le sien ; ...)
