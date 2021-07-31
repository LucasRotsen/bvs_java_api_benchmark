package com.rotsen.dogsapi.controller;

import com.rotsen.dogsapi.controller.dto.DogDto;
import com.rotsen.dogsapi.model.Dog;
import com.rotsen.dogsapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogsController {

    @Autowired
    private DogRepository dogRepository;

    @GetMapping
    public List<DogDto> findAll(){
        List<Dog> dogs = dogRepository.findAll();
        return DogDto.convert(dogs);
    }

    @GetMapping("/{id}")
    public DogDto findById(@PathVariable Long id) {
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return DogDto.convert(dog);
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody DogDto dogDto){
        Dog dog = new Dog(dogDto.getName());
        dogRepository.save(dog);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DogDto> update(@PathVariable Long id, @RequestBody DogDto dogDto){
        Dog dog = dogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        dog.setName(dogDto.getName());

        return ResponseEntity.ok(DogDto.convert(dog));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        dogRepository.deleteById(id);
    }
}
