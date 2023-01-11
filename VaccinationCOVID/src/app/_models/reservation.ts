import { ChoixCentre } from "../choix-centre/choix-centre";
import { User } from "./user";

export interface Reservation {

    id_reservation: number,
    date_reservation: string,
    centre: ChoixCentre,
    identifiant: User

}
