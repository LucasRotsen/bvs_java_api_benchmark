package com.rotsen.dogsapi.repository;

import com.rotsen.dogsapi.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {}
