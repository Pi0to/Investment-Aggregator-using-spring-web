package com.piotodev.investment.aggregator.controller;

import com.piotodev.investment.aggregator.controller.dto.*;
import com.piotodev.investment.aggregator.entities.User;
import com.piotodev.investment.aggregator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Set;
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

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

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

    @DeleteMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("userId") String userId,
                                              @PathVariable("accountId") String accountId){

        userService
                .getUserById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        userService.deleteAccount(UUID.fromString(accountId));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountDTO>> getAccounts(@PathVariable("userId") String userId){

        var accounts = userService.getAccounts(UUID.fromString(userId));

        var accountsDTO = accounts.stream()
                .map(ac -> new AccountDTO(ac.getAccountId(), ac.getDescription()));

        return ResponseEntity.ok().body(accountsDTO.toList());
    }

    @GetMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable("userId") String userId,
                                                     @PathVariable("accountId") String accountId){

        userService
                .getUserById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        var accountUpdated = userService.getAccountById(accountId);

        return ResponseEntity.ok().body(accountUpdated);
    }


    @PostMapping("/{userId}/accounts/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable("userId") String userId,
                                                    @PathVariable("accountId") String accountId,
                                                    @RequestBody UpdateAccountDTO accountDTO){

        userService
                .getUserById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        userService.updateAccount(accountId, accountDTO);


        return ResponseEntity.ok().build();
    }
}
