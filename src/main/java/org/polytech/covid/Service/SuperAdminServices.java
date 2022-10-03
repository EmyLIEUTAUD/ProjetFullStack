package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;

public interface SuperAdminServices {

    List<Admin> voirAdmins();

    Admin creerAdmin(Admin admin);

    Admin modifierAdmin(Optional<Admin> adminData, Admin admin);

}
