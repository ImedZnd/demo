package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Candidat;
import com.example.demo.model.Cv;
import com.example.demo.model.Rapport;
import com.example.demo.service.CandidatService;
import com.example.demo.service.CvService;
import com.example.demo.service.RapportService;

@RestController
@RequestMapping("/can")
@EnableAutoConfiguration
public class CandidatController {
	
	@Autowired
	CandidatService candidatService;
	@Autowired
	RapportService rapportService;
	@Autowired
	CvService cvService;
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/id/{id}")
    public ResponseEntity <Optional<Candidat>> getCandidatbyId( @PathVariable(value = "id") Long i) {
    return new ResponseEntity<>(candidatService.getById(i),HttpStatus.OK);
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public ResponseEntity <List<Candidat>> getAllCandidat( ) {
    return new ResponseEntity<>(candidatService.getAllCandidat(),HttpStatus.OK);
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/cv/{canId}")
    public ResponseEntity <Cv> getCv( @PathVariable(value = "canId") Long canId) {
    return new ResponseEntity<>(cvService.getByCandidatId(canId),HttpStatus.OK);
    }
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/rap/{canId}")
	    public ResponseEntity <List<Rapport>> getByCanId(@PathVariable("can") Long canId) {
		 return new ResponseEntity<>(rapportService.getByCandidatId(canId), HttpStatus.OK);
	    }
    
	@CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/mail/{mail}")
	    public ResponseEntity <Candidat> getBymail(@PathVariable("mail") String mail) {
		 return new ResponseEntity<>(candidatService.getByMail(mail), HttpStatus.OK);
	    }
	
	@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/sor/{sortby}")
    public ResponseEntity<List> getAllCandidatSorted(@PathVariable String sortby){
        System.out.println("get all candidats \n");

        switch (sortby.toLowerCase()) {
            case "name":
                sortby = "name";
                break;
            case "sexe":
                sortby = "sexe";
                break;
            case "statu":
                sortby = "status";
                break;
            default:
                sortby = "inscription";
                break;
        }
        return new ResponseEntity<>(candidatService.getAll(sortby), HttpStatus.OK);
    }
	

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/name/{name}"})
    public ResponseEntity<List> getCandidatsByName( @PathVariable(value = "name") String name) {

        System.out.println("get candidats with name\n");
            return new ResponseEntity<>(
                    candidatService.getByName(name),
                    HttpStatus.OK
            );
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public ResponseEntity<Candidat> addCandidat(@Valid @RequestBody Candidat candidat) {

            System.out.println("add a candidat \n");
            return new ResponseEntity<>(candidatService.addOne(candidat),HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCandidat(@PathVariable ("id") Long id) {

            System.out.println("delete a candidat \n");
            candidatService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public ResponseEntity<Candidat> updateCandidat( @RequestBody Candidat newCandidat)  {
            System.out.println("update candidat \n");
            Optional<Candidat> oldCandidat = getCandidatbyId(newCandidat.getId()).getBody();
            if (oldCandidat.isPresent()) {
                return new ResponseEntity<>(addCandidat(newCandidat).getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
}
