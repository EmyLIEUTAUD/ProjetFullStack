import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ProfessionnelsService } from '../professionnels.service';
import { ProfileComponent } from '../profile/profile.component';
import { ReservationsService } from '../reservations.service';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Reservation } from '../_models/reservation';
import { Role } from '../_models/role';
import { User } from '../_models/user';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-liste-reservations',
  templateUrl: './liste-reservations.component.html',
  styleUrls: ['./liste-reservations.component.scss']
})
export class ListeReservationsComponent {

  reservations: any = [];
  centre: ChoixCentre = {gid: 0, nom: "", comnom: "", numAdresse: "", adresse: "", horairesDimanche: "", horairesJeudi: "", horairesLundi: "", horairesMardi: "", horairesMercredi: "", horairesSamedi: "", horairesVendredi: "", cp: 0};
  currentUser: any;
  personne: User = {identifiant: 0, nom: "", prenom: "", username: "", password: "", role: Role.Admin};

  constructor(private router : Router,
    public modalRef: MdbModalRef<ListeReservationsComponent>,
    private reservationsService: ReservationsService,
    private route: ActivatedRoute,
    private token: TokenStorageService,
    private professionnelsService: ProfessionnelsService,
    private adminService: VaccinationAdminServiceService) { }

    ngOnInit() {
      this.currentUser = this.token.getUser();
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        console.log("id personne : "+ this.personne.identifiant);
        this.adminService.getVaccinationAdminByPersonneIdentifiant(this.personne.identifiant).subscribe((resultAdmin) => {
          this.centre = resultAdmin.centre;
          console.log(this.centre);
        })
      });
      this.loadReservations();
    }
  
    loadReservations(){
      return this.reservationsService.getAllReservationsFromCenter().subscribe((resultReservations:{})=>{
        this.reservations = resultReservations;
      })   
    }

  deleteReservation(id_reservation: number){
    if (window.confirm('Are you sure, you want to delete?')) {
      this.reservationsService.deleteReservation(id_reservation).subscribe((data) => {
        this.loadReservations();
      });
    }
  }

}
