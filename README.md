# ProjetFullStack
Application permettant de prendre un rendez-vous pour une vaccination COVID dans un centre de vaccination en France. <br>
*Créée par Emy LIEUTAUD (31818604), Yunqiao ZHANG () et Romain PERRELLE ()*

Données sur les lieux de vaccination : https://www.data.gouv.fr/fr/datasets/lieux-de-vaccination-contre-la-covid-19/

- rendez-vous.temps.enregistrement : voir le temps d'engeristrement d'un rendez-vous
- rendez-vous.nombre : voir le nombre de rendez-vous pris

## Structure du back
Projet JAVA basé sur Maven : les dépendances sont donc décrites dans le fichier pom.xml

## Structure du front
Projet Angular

## Endpoints
**/public** -> destinée à tout utilisateur qui souhaite prendre un rendez-vous sur l'application<br>
**/admin** -> destinée aux professionnels : besoin d'être connecté pour y accéder<br>
**/login** -> reservée aux professionnels pour se créer un compte (**/login/nouveau**) ou se connecter (**/login/authenticate**)<br>
**/actuator/metrics** -> accéder aux métriques générées
**/swagger-ui** -> accéder au swagger (description de l'API REST)

## Rôles
### SuperAdmin
Il n'appartient pas à un centre précis. 
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
Il est lié à un centre.
Il peut : 
- gérer les médecins de son centre (**admin/medecins**) : 
  - créer un médecin (**POST /admin/medecins/nouveau**)
  - afficher les médecins (**GET /admin/medecins**)
  - modifier un médecin (**PUT /admin/medecins/modifier/{id}**)
  - supprimer un médecin (**DELETE /admin/medecins/supprimer/{id}**)
- gérer les réservations de son centre (**/admin/reservations**) : 
  - afficher les réservations (** GET /admin/reservations**)
  - supprimer une réservation (** DELETE /admin/reservations/supprimer/{id}**)

### Medecin
Il est lié à un centre.
Il peut : 
- rechercher une personne dans son centre par son nom (** GET /admin/personnes/{nom}**)
- valider la vaccination d'une personne (** PUT /admin/personnes/validerVaccination/{id}**)


## Docker

## Versions
Angular CLI : 15.0.3
Docker : 20.10.21
Java (jdk) : 17.0.5
Maven : 3.6.3
Node : 18.12.1
npm : 8.19.2
