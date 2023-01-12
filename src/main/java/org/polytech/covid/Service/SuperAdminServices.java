package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Superadmin;

public interface SuperAdminServices {

    List<Admin> voirAdmins();

    Optional<Admin> voirAdminsById(Integer id);

    List<Superadmin> voirSuperAdmins();

    Admin creerAdmin(Admin admin);

    Admin modifierAdmin(Optional<Admin> adminData, Admin admin);

}
