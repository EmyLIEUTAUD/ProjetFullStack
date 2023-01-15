package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Superadmin;

public interface SuperAdminServices {

    /***
     * Liste des services pour les superAdmins
     ***/

    List<Admin> voirAdmins();

    List<Admin> voirAdminsByCentre(Integer id);

    List<Superadmin> voirSuperAdmins();

    Admin creerAdmin(Admin admin);

    Admin modifierAdmin(Optional<Admin> adminData, Admin admin);

}
