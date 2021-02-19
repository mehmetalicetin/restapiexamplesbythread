package com.spring.web.messengerapp.controller;

import com.spring.web.messengerapp.model.Message;
import com.spring.web.messengerapp.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class MessageResources {
    @Autowired
    MessageServiceImpl messageService;

    @GetMapping(value = "/messages", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> findAllMessages() throws Exception {
       return messageService.findAll();
    }

    @GetMapping(value = "/messages/all", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ResponseEntity<List<Message>> findAllMessages1() throws Exception {
        var headers = new HttpHeaders();
        headers.add("Responsed","MessageResources");
        return new ResponseEntity<List<Message>>(messageService.findAll(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/messages/thread/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public CompletableFuture<Message> findById(@PathVariable("id")Long id) throws Exception {
        return messageService.findById(id);
    }

    @GetMapping (value = "/messages/thread", produces={MediaType.APPLICATION_JSON})
    public CompletableFuture<ResponseEntity> getAllMessages(@QueryParam("year")Integer year,
                                                            @QueryParam("start")Integer start,
                                                            @QueryParam("size")Integer size) throws Exception {

        if (year != null)
            return messageService.getAllMessagesForYear(year).thenApply(ResponseEntity::ok);
        else if (start != null && size != null)
            return messageService.getAllMessagesPaginated(start, size).thenApply(ResponseEntity::ok);
        else
            return messageService.getAllMessages().thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/messages/thread", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public CompletableFuture<ResponseEntity<Message>> addMessage(@RequestBody Message message) throws Exception {
        return messageService.add(message).thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/messages/thread", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public CompletableFuture<ResponseEntity<Message>> updateMessage(@RequestBody Message message) throws Exception {
        return messageService.update(message).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping(value = "/messages/thread/{id}", produces = MediaType.APPLICATION_JSON)
    public CompletableFuture<ResponseEntity<Message>> deleteMessage(@PathVariable("id") Long id) throws Exception {
        return messageService.delete(id).thenApply(ResponseEntity::ok);
    }

    @PostMapping("/messages/headers")
    public ResponseEntity createUser(@RequestHeader(value = "Accept") String acceptHeader,
                                     @RequestHeader(value = "Authorization") String authorization) {
        Map<String, String> returnValue = new HashMap<>();
        returnValue.put("Accept_Header", acceptHeader);
        returnValue.put("Authorization_Header", authorization);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}