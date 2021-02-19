package com.spring.web.messengerapp.controller;

import com.spring.web.messengerapp.model.Profile;
import com.spring.web.messengerapp.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProfileResources {
    @Autowired
    ProfileServiceImpl profileService;

    @GetMapping(value = "/profiles", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Profile> findAllProfiles() throws Exception {
        return profileService.findAll();
    }

    @GetMapping(value = "/profiles/thread/{profileName}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public CompletableFuture<Profile> findById(@PathVariable("profileName")String profileName) throws Exception {
        return profileService.findById(profileName);
    }

    @GetMapping (value = "/profiles/thread", produces={MediaType.APPLICATION_JSON})
    public CompletableFuture<ResponseEntity> getAllProfiles() throws Exception {
        return profileService.getAllProfiles().thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "/profiles/thread", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public CompletableFuture<ResponseEntity<Profile>> addProfile(@RequestBody Profile profile) throws Exception {
        return profileService.add(profile).thenApply(ResponseEntity::ok);
    }

    @PutMapping(value = "/profiles/thread", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public CompletableFuture<ResponseEntity<Profile>> updateProfile(@RequestBody Profile profile) throws Exception {
        return profileService.update(profile).thenApply(ResponseEntity::ok);
    }

    @DeleteMapping(value = "/profiles/thread/{profileName}", produces = MediaType.APPLICATION_JSON)
    public CompletableFuture<ResponseEntity<Profile>> deleteProfile(@PathVariable("profileName") String profileName) throws Exception {
        return profileService.delete(profileName).thenApply(ResponseEntity::ok);
    }
}
