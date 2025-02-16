package com.piotodev.investment.aggregator.service;

import com.piotodev.investment.aggregator.controller.dto.*;
import com.piotodev.investment.aggregator.entities.Account;
import com.piotodev.investment.aggregator.entities.BillingAdress;
import com.piotodev.investment.aggregator.entities.User;
import com.piotodev.investment.aggregator.repository.AccountRepository;
import com.piotodev.investment.aggregator.repository.UserRepository;
import com.piotodev.investment.aggregator.repository.BillingAdressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private AccountRepository accountRepository;

    private BillingAdressRepository billingAdressRepository;


    public UserService(UserRepository userRepository,
                       AccountRepository accountRepository,
                       BillingAdressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAdressRepository = billingAddressRepository;
    }

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

    public void createAccount(String userId, CreateAccountDTO createAccountDTO){

        var userExists = userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        Account account = new Account(
                createAccountDTO.description(),
                userExists,
                null,
                new HashSet<>()
        );

        var billingAdress = new BillingAdress(
                account,
                createAccountDTO.street(),
                createAccountDTO.number());

        account.setBillingAdress(billingAdress);
        accountRepository.save(account);
        billingAdressRepository.save(billingAdress);
    }

    public void deleteAccount(UUID accountId){

        if(accountRepository.findById(accountId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        accountRepository.deleteById(accountId);
    }

    public Set<Account> getAccounts(UUID userId){
        return userRepository.findById(userId)
                .map(User::getAccounts)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public AccountDTO getAccountById(String id){


        var account = accountRepository.getById(UUID.fromString(id));



        return new AccountDTO(account.getAccountId(), account.getDescription());
    }

    public void updateAccount(String accountID, UpdateAccountDTO accountDTO){

        var accountExists = accountRepository.findById(UUID.fromString(accountID));

        if(accountExists.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
         var account = accountExists.get();

        account.setDescription(accountDTO.description());

        accountRepository.save(account);
    }

}
