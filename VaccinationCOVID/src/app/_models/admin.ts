import { ChoixCentre } from "../choix-centre/choix-centre"
import { User } from "./user"

export interface Admin{
    
        centre: ChoixCentre;
        id_admin: number;
        personne: User;
        
}