package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Competence;
import com.example.demo.model.Cv;
import com.example.demo.repository.CompetenceRepository;
import com.example.demo.service.CompetenceService;

@Service
public class CompetenceServiceImpl implements CompetenceService {

	@Autowired
	CompetenceRepository competenceRepository;
	
	@Override
	public List<Competence> getAll(String sortby) {
		// TODO Auto-generated method stub
		return competenceRepository.findAll(Sort.by(sortby));
	}

	@Override
	public Optional<Competence> getById(Long id) {
		// TODO Auto-generated method stub
		return competenceRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		competenceRepository.deleteById(id);
	}

	@Override
	public Competence addOne(Competence competence) {
		// TODO Auto-generated method stub
		return competenceRepository.save(competence);
	}

	@Override
	public List<Competence> getByName(String name) {
		// TODO Auto-generated method stub
		return competenceRepository.findCompetenceByName(name);
	}

	@Override
	public List<Competence> getByLevel(String level) {
		// TODO Auto-generated method stub
		return competenceRepository.findCompetenceByLevel(level);
	}

	@Override
	public List<Competence> getByMonthOfExp(String monthOfExp) {
		// TODO Auto-generated method stub
		return competenceRepository.findCompetenceByMonthOfExp(monthOfExp);
	}

	@Override
	public List<Competence> getByCv(Cv cv) {
		// TODO Auto-generated method stub
		return competenceRepository.findCompetenceByCv(cv);
	}

	@Override
	public boolean getByCvIfExist(Long id) {
		// TODO Auto-generated method stub
		return competenceRepository.existsById(id);
	}

	@Override
	public List<Competence> getAll() {
		// TODO Auto-generated method stub
		return competenceRepository.findAll();
	}

	@Override
	public List<Competence> getByCvId(Long cvId) {
		// TODO Auto-generated method stub
		return competenceRepository.findByCvId(cvId);
	}

}
