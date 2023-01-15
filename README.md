# ProjetFullStack - Vaccination COVID
Application permettant de prendre un rendez-vous pour une vaccination COVID dans un centre de vaccination en France. <br>
*Créée par Emy LIEUTAUD (31818604), Yunqiao ZHANG () et Romain PERRELLE (32024667)*

Données sur les lieux de vaccination : https://www.data.gouv.fr/fr/datasets/lieux-de-vaccination-contre-la-covid-19/

## Structure du back
Projet JAVA basé sur Maven : les dépendances sont donc décrites dans le fichier *pom.xml*.<br>
Technologies implémentées :<br>
- Basic Auth
- Contrôleur REST
- ETag
- Hibernate
- JWT
- Liquibase
- Métriques avec Micrometer
- PostgreSQL
- RateLimit via Token Bucket
- Spring (Web, Security)
- Swagger <br>

Plusieurs méthodes existent pour lancer le back : <br>
- avec VScode : <br>
Il faut premièrement ouvrir le projet dans VScode.<br>
Ensuite, il faut faire un **clique droit sur la classe *CovidApiApplication.java*** qui se trouve dans src/main/java/org/polytech/covid/.<br>
Enfin, il suffit de cliquer sur **Run Java**.<br>
- en ligne de commandes : <br>
Il faut tout d'abord **aller à la racine du projet**.
Puis, il faut exécuter la commande suivante : <br>```mvn exec:java -Dexec.mainClass=org.polytech.covid.CovidApiApplication```

Le back est maintenant accessible sur le navigateur sur **localhost:8080**.

### Endpoints
**/public/** -> destiné à tout utilisateur qui souhaite prendre un rendez-vous sur l'application<br>
**/admin/** -> destiné aux professionnels : besoin d'être connecté pour y accéder<br>
**/login/** -> reservé aux professionnels pour se créer un compte (**/login/nouveau**) ou se connecter (**/login/authenticate**)<br>
**/actuator/metrics/** -> accéder aux métriques générées<br>
**/swagger-ui** -> accéder au swagger (description de l'API REST)

## Structure du front
Projet Angular.<br>
Technologies implémentées :<br>
- Bootstrap
- File d'attente
- Formulaire BasicAuth
- Interceptors
- Material
- Modales
- Promises <br>

Pour lancer le front, il faut tout d'abord **aller dans le dossier VaccinationCOVID**.<br>
Ensuite, il suffit d'exécuter la commande suivante : ```ng serve```.<br>
Le front est maintenant accessible sur le navigateur sur **localhost:4200**.

### Endpoints
**/home/** -> destiné à tout utilisateur qui souhaite prendre un rendez-vous sur l'application<br>
**/register/** -> destiné aux professionnels pour se créer un compte<br>
**/login/** -> reservé aux professionnels pour se connecter<br>

## Rôles

### SuperAdmin
Il n'appartient pas à un centre précis. <br>
Un seul SuperAdmin existe.<br>
Il peut :
- gérer les centres (**/admin/centres/**) : 
  - créer un centre (**POST /admin/centres/nouveau**)
  - afficher les centres (**GET /admin/centres** et **GET /public/centres** et **GET /public/centres/id/{id}**)
  - modifier un centre (**PUT /admin/centres/modifier/{gid}**)
  - supprimer un centre (**DELETE /admin/centres/supprimer/{gid}**)
- gérer les administrateurs (**/admin/administrateurs/**) : 
  - récupérer les professionnels en attente d'affectation (**GET /admin/professionnels**)
  - créer un administrateur (**POST /admin/administrateurs/nouveau**)
  - afficher les administrateurs (**GET /admin/administrateurs**)
  - modifier un administrateur (**PUT /admin/administrateurs/modifier/{id}**)
  - supprimer un administrateur (**DELETE /admin/administrateurs/supprimer/{id}**)
  
### Admin
Il est lié à un centre.<br>
Il peut : 
- gérer les médecins de son centre (**admin/medecins/**) : 
  - récupérer les professionnels en attente d'affectation (**GET /admin/professionnels**)
  - créer un médecin (**POST /admin/medecins/nouveau**)
  - afficher les médecins (**GET /admin/medecins** et **GET /admin/medecins/id/{id}**)
  - modifier un médecin (**PUT /admin/medecins/modifier/{id}**)
  - supprimer un médecin (**DELETE /admin/medecins/supprimer/{id}**)
- gérer les réservations de son centre (**/admin/reservations/**) : 
  - afficher les réservations (**GET /admin/reservations** et **GET /admin/reservations/centre**)
  - supprimer une réservation (**DELETE /admin/reservations/supprimer/{id}**)

### Medecin
Il est lié à un centre.<br>
Il peut :
- rechercher une personne dans son centre par son nom (**GET /admin/personnes/{nom}**)
- valider la vaccination d'une personne (**PUT /admin/personnes/validerVaccination/{id}**)

## Comptes pré-créés
### SuperAdmin
mail : superAdmin@gmail.com<br>
mdp : superAdminPassword
### Admins
- Admin 1 : <br>
mail : admin1@gmail.com<br>
mdp : admin1<br>
Appartient au centre numéro 3157
- Admin 2 : <br>
mail : admin2@gmail.com<br>
mdp : admin2<br>
Appartient au centre numéro 1072
### Médecins
- Médecin 1 : <br>
mail : medecin1@gmail.com<br>
mdp : medecin1<br>
Appartient au centre numéro 3157
- Médecin 2 : <br>
mail : medecin2@gmail.com<br>
mdp : medecin2<br>
Appartient au centre numéro 188<br>
<br>
Une réservation est également pré-crée dans le centre numéro 3157.

## Métriques
Pour accéder aux métriques, il est nécessaire d'avoir appelé au moins une fois l'endpoint lié. 

### rendez-vous.temps.enregistrement
Elle sert à voir le temps d'enregistrement d'un rendez-vous. <br>
Endpoint : **GET /actuator/metrics/rendez-vous.temps.enregistrement**<br>
Métrique mise à jour lors de l'appel du endpoint suivant : **POST /public/inscription**

### rendez-vous.nombre
Elle sert à voir le nombre de rendez-vous pris. <br>
Endpoint : **GET /actuator/metrics/rendez-vous.nombre**<br>
Métrique incrémentée lors de l'appel du endpoint suivant : **POST /public/inscription**

## Docker
Il est possible de lancer notre application (back + front) avec Docker.<br>
Pour cela, il faut **aller à la racine du projet** (là où se trouve le fichier *docker-compose.yml*).<br>
Remarque : Docker doit être lancé (état : running).<br>
Ensuite, il est nécessaire d'exécuter la commande suivante : ```docker-compose up -d --build```.<br>
L'application fonctionne alors dans des conteneurs. Vous pouvez y accéder sur votre navigateur :
- **localhost:4200** pour accéder au front
- **localhost:8080** pour accéder au back

## Versions
Angular CLI : 15.0.3<br>
Docker : 20.10.21<br>
Java (jdk) : 17.0.5<br>
Maven : 3.6.3<br>
Node : 18.12.1<br>
npm : 8.19.2<br>
<br>
/!\ Ne fonctionne que sur Windows 10 et 11
