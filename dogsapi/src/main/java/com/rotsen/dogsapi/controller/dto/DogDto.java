package com.rotsen.dogsapi.controller.dto;

import com.rotsen.dogsapi.model.Dog;

import java.util.List;
import java.util.stream.Collectors;

public class DogDto {

    private Long id;
    private String name;

    public DogDto(){}

    public DogDto(Dog dog){
        this.id = dog.getId();
        this.name = dog.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static List<DogDto> convert(List<Dog> dogs) {
        return dogs.stream().map(DogDto::new).collect(Collectors.toList());
    }

    public static DogDto convert(Dog dog) {
        return new DogDto(dog);
    }
}
