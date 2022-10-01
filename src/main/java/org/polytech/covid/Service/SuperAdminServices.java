package org.polytech.covid.Service;

import java.util.List;

import org.polytech.covid.Entity.Admin;

public interface SuperAdminServices {

    List<Admin> voirAdmins();

    Admin creerAdmin(Admin admin);

}
