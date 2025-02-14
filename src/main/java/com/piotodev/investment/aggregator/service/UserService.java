package com.piotodev.investment.aggregator.service;

import com.piotodev.investment.aggregator.controller.CreateUserDTO;
import com.piotodev.investment.aggregator.controller.UpdateUserDTO;
import com.piotodev.investment.aggregator.entities.User;
import com.piotodev.investment.aggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public UUID createUser(CreateUserDTO createUserDTO){

        //DTO --> Entity
        var entity = new User(createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password());

        var userSaved = userRepository.save(entity);

        return userSaved.getId();
    }

    public Optional<User> getUserById(UUID id){
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void updateUserById(UUID id, UpdateUserDTO updateUserDTO){

        var userExists = userRepository.findById(id);

        if(userExists.isPresent()){

            var user = userExists.get();

            if(updateUserDTO.username() != null){
                user.setUsername(updateUserDTO.username());
            }

            if(updateUserDTO.password() != null){
                user.setPassword(updateUserDTO.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteById (UUID id){

        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }

    }


}
