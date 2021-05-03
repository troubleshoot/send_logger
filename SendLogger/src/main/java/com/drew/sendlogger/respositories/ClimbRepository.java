package com.drew.sendlogger.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.drew.sendlogger.models.Climb;

public interface ClimbRepository extends CrudRepository<Climb,Long> {
	List<Climb> findAll();
	Optional<Climb> findClimbById(Long id);
//	List<Idea> findAllOrderByUsersAsc();
//	@Query(
//	value = "SELECT * FROM ideas ORDER BY likes ASC",
//	nativeQuery = true
//	)
//	List<Idea> findAllByASC();
	
	
}
