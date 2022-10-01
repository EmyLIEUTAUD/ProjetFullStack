package org.polytech.covid.ServiceImpl;

import java.util.List;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Service.SuperAdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServicesImpl implements SuperAdminServices {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> voirAdmins() {
        List<Admin> listAdmins = adminRepository.findAll();
        return listAdmins;
    }

    public Admin creerAdmin(Admin admin) {
        Admin _admin = adminRepository
                .save(new Admin(admin.getPersonne(), admin.getCentre()));
        return _admin;
    }

}