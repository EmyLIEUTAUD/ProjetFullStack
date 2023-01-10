import { ChoixCentre } from "../choix-centre/choix-centre";
import { User } from "../_models/user";

export interface ChoixMedecin {
    id_medecin: number;
    personne: User;
    centre: ChoixCentre;
}
