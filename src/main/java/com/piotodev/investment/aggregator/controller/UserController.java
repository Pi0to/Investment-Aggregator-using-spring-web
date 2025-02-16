package com.piotodev.investment.aggregator.controller;

import com.piotodev.investment.aggregator.controller.dto.CreateAccountDTO;
import com.piotodev.investment.aggregator.controller.dto.CreateUserDTO;
import com.piotodev.investment.aggregator.controller.dto.UpdateUserDTO;
import com.piotodev.investment.aggregator.entities.User;
import com.piotodev.investment.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO body){


        var userID = userService.createUser(body);
        return ResponseEntity.created(URI.create("/v1/users" + userID.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByID(@PathVariable("userId") String userId){

        var user = userService.getUserById(UUID.fromString(userId));

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        var users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable String userId){
        var uuid = UUID.fromString(userId);

        userService.deleteById(uuid);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO body){

        var uuid = UUID.fromString(userId);

        userService.updateUserById(uuid, body);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId, @RequestBody CreateAccountDTO accountDTO){

        userService.createAccount(userId, accountDTO);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("accountId") String accountId){

        userService.deleteAccount(UUID.fromString(accountId));

        return ResponseEntity.ok().build();
    }

}
