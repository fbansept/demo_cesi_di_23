package edu.cesi.demo.controller;

import edu.cesi.demo.dao.FamilyDao;
import edu.cesi.demo.model.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamilyController {

    @Autowired
    private FamilyDao familyDao;

    @GetMapping("/family/{id}")
    public Family getFamily(@PathVariable int id){
        return familyDao.findById(id).orElse(null);
    }

    @GetMapping("/families")
    public List<Family> getFamilys() {
        List<Family> listFamily = familyDao.findAll();
        return listFamily;
    }

    @DeleteMapping("/family/{id}")
    public void deleteFamily(@PathVariable int id){
        familyDao.deleteById(id);
    }

    @PostMapping("/family")
    public void editFamily(@RequestBody Family family) {
        familyDao.save(family);
    }
}
