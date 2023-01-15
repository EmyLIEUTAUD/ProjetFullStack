import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ProfessionnelsService } from '../professionnels.service';
import { ReservationsService } from '../reservations.service';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
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

  constructor(public modalRef: MdbModalRef<ListeReservationsComponent>,
    private reservationsService: ReservationsService,
    private token: TokenStorageService,
    private professionnelsService: ProfessionnelsService,
    private adminService: VaccinationAdminServiceService) { }

    ngOnInit() {
      this.currentUser = this.token.getUser();
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        this.adminService.getVaccinationAdminByPersonneIdentifiant(this.personne.identifiant).subscribe((resultAdmin) => {
          this.centre = resultAdmin.centre;
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
