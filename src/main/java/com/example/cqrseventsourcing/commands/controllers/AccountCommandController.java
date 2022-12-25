package com.example.cqrseventsourcing.commands.controllers;

import com.example.cqrseventsourcing.commonApi.commands.CreateAccountCommand;
import com.example.cqrseventsourcing.commonApi.commands.CreditAccountCommand;
import com.example.cqrseventsourcing.commonApi.commands.DebitAccountCommand;
import com.example.cqrseventsourcing.commonApi.dto.CreateAccountRequestDTO;
import com.example.cqrseventsourcing.commonApi.dto.CreditAccountRequestDTO;
import com.example.cqrseventsourcing.commonApi.dto.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController  {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO createAccountRequestDto)
    {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountRequestDto.getInitialBalance(),
                createAccountRequestDto.getCurrency()
        ));
        return commandResponse;
    }

    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO creditAccountRequestDto)
    {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreditAccountCommand(
                creditAccountRequestDto.getId(),
                creditAccountRequestDto.getAmount(),
                creditAccountRequestDto.getCurrency()
        ));
        return commandResponse;
    }

    @PutMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debitAccountRequestDto)
    {
        CompletableFuture<String> commandResponse = commandGateway.send(new DebitAccountCommand(
                debitAccountRequestDto.getId(),
                debitAccountRequestDto.getAmount(),
                debitAccountRequestDto.getCurrency()
        ));
        return commandResponse;
    }


    // Get all events for an account
    @GetMapping("/eventStore/{accountId}")
    public Stream eventStore(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception)
    {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

}