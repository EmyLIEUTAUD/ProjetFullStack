import { Role } from "./role";
export class User{
    id: number;
    username: string;
    password: string;
    prenom: string;
    nom: string;
    role: Role;
    token?: string;

}