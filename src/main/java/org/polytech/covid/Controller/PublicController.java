package org.polytech.covid.Controller;

import org.polytech.covid.Dao.CentreDao;
import org.polytech.covid.Service.RechercheCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private RechercheCentreService rechercheCentreService;

    @GetMapping(path = "/centers/{id}")
    public List<CentreDao> rechercheCentreByVille(@PathVariable(value ="id") Integer idVille){
        return rechercheCentreService.rechercheCentreByVille(idVille);
    }

}
