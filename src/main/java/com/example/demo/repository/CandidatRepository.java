package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Candidat;


@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Long>{
	
	List<Candidat> findCandidatByName(String name);
    List<Candidat> findCandidatBySurename(String surename);
    List<Candidat> findCandidatBySexe(String sexe);
    List<Candidat> findCandidatByStatus(Enum<?> status);
	List<Candidat> findAll();
	List<Candidat> findByNameContaining(String name);
	Candidat findByMail(String mail);

}
