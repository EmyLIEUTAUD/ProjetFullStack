# ProjetFullStack - Vaccination COVID
Application permettant de prendre un rendez-vous pour une vaccination COVID dans un centre de vaccination en France. <br>
*Créée par Emy LIEUTAUD (31818604), Yunqiao ZHANG () et Romain PERRELLE ()*

Données sur les lieux de vaccination : https://www.data.gouv.fr/fr/datasets/lieux-de-vaccination-contre-la-covid-19/

## Structure du back
Projet JAVA basé sur Maven : les dépendances sont donc décrites dans le fichier *pom.xml*.<br>
Il est accessible sur le navigateur sur **localhost:8080**.

## Structure du front
Projet Angular.<br>
Il est accessible sur le navigateur sur **localhost:4200**.

## Endpoints
**/public/** -> destiné à tout utilisateur qui souhaite prendre un rendez-vous sur l'application<br>
**/admin/** -> destiné aux professionnels : besoin d'être connecté pour y accéder<br>
**/login/** -> reservé aux professionnels pour se créer un compte (**/login/nouveau**) ou se connecter (**/login/authenticate**)<br>
**/actuator/metrics/** -> accéder aux métriques générées<br>
**/swagger-ui** -> accéder au swagger (description de l'API REST)

## Rôles

### SuperAdmin
Il n'appartient pas à un centre précis. <br>
Il peut :
- gérer les centres (**/admin/centres**) : 
  - créer un centre (**POST /admin/centres/nouveau**)
  - afficher les centres (**GET /admin/centres**)
  - modifier un centre (**PUT /admin/centres/modifier/{gid}**)
  - supprimer un centre (**DELETE /admin/centres/supprimer/{gid}**)
- gérer les administrateurs (**/admin/administrateurs**) : 
  - créer un administrateur (**POST /admin/administrateurs/nouveau**)
  - afficher les administrateurs (**GET /admin/administrateurs**)
  - modifier un administrateur (**PUT /admin/administrateurs/modifier/{id}**)
  - supprimer un administrateur (**DELETE /admin/administrateurs/supprimer/{id}**)
  
### Admin
Il est lié à un centre.<br>
Il peut : 
- gérer les médecins de son centre (**admin/medecins**) : 
  - créer un médecin (**POST /admin/medecins/nouveau**)
  - afficher les médecins (**GET /admin/medecins**)
  - modifier un médecin (**PUT /admin/medecins/modifier/{id}**)
  - supprimer un médecin (**DELETE /admin/medecins/supprimer/{id}**)
- gérer les réservations de son centre (**/admin/reservations**) : 
  - afficher les réservations (**GET /admin/reservations**)
  - supprimer une réservation (**DELETE /admin/reservations/supprimer/{id}**)

### Medecin
Il est lié à un centre.<br>
Il peut :
- rechercher une personne dans son centre par son nom (**GET /admin/personnes/{nom}**)
- valider la vaccination d'une personne (**PUT /admin/personnes/validerVaccination/{id}**)

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
