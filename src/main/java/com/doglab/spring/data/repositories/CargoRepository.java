package com.doglab.spring.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doglab.spring.data.orm.Cargo;

@Repository
public interface CargoRepository 
	extends CrudRepository<Cargo, Long> {

	// Queries Nativas!
	@Query(value="SELECT * FROM cargos AS c WHERE c.description = :description",
			nativeQuery=true)
	Optional<Cargo> findWithDescription(String description);
	
	boolean existsByDescription(String description);
	
	void deleteByDescription(String description);
	
}
