package com.drew.sendlogger.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.drew.sendlogger.models.Climb;
import com.drew.sendlogger.respositories.ClimbRepository;

@Service
public class ClimbService {
	
	private final ClimbRepository climbRepo;
	
	public ClimbService(ClimbRepository climbRepo) {
		this.climbRepo = climbRepo;
	}
	
//	C
	public Climb createClimb(Climb climb) {
		return climbRepo.save(climb);
	}
	
//	R
	public ArrayList<Climb> findAllClimbs(){
		return (ArrayList<Climb>) climbRepo.findAll();
	}
	public Climb findClimbById(Long id) {
		Optional<Climb> optionalClimb = climbRepo.findClimbById(id);
		if(optionalClimb.isPresent()) {
			return optionalClimb.get();
		} else {
			return null;
		}
	}
	
//	public ArrayList<Climb> findAllByASC(){
//		return (ArrayList<Climb>) climbRepo.findAllByASC();
//	}
	
//	U
	public Climb updateClimb(Climb diffClimb) {
		return climbRepo.save(diffClimb);
	}
	
//	D
	public void deleteClimbById(Long id) {
		climbRepo.deleteById(id);
	}
}
