package org.polytech.covid.Service;

import java.io.IOException;
import java.util.List;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Helper.CSVHelper;
import org.polytech.covid.Repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

    @Autowired
    CentreRepository centreRepository;

    public void save(MultipartFile file) {
        try {
            List<Centre> centres = CSVHelper.csvToCentres(file.getInputStream());
            centreRepository.saveAll(centres);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data:" + e.getMessage());
        }
    }

    public List<Centre> getAllCentres() {
        return centreRepository.findAll();
    }

}