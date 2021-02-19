package com.spring.web.messengerapp.service;

import com.spring.web.messengerapp.database.DataBaseClass;
import com.spring.web.messengerapp.model.Message;
import com.spring.web.messengerapp.model.Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class ProfileServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    private static Map<String, Profile> profileMap = DataBaseClass.getProfileMap();

    @Async
    public CompletableFuture<List<Profile>> getAllProfiles() throws Exception {

        LOGGER.info("Request to get a list of profiles --->"+ Thread.currentThread().getName());

        final List<Profile> profiles = findAll();
        return CompletableFuture.completedFuture(profiles);
    }

    public List findAll() throws Exception {
        if(profileMap.isEmpty())
            throw new Exception("There is no any profile");
        return new ArrayList(profileMap.values());
    }

    @Async
    public CompletableFuture<Profile>  findById(String profileName) throws Exception {
        LOGGER.info("Request to add list of a profile --->"+ Thread.currentThread().getName());

        Optional<Profile> optionalProfile =  profileMap.values().stream().filter(x->x.getProfileName().equalsIgnoreCase(profileName)).findAny();
        Profile profile = optionalProfile.orElseThrow(()-> new Exception("There is no any profile by profile name:"+profileName));
        return CompletableFuture.completedFuture(profile);
    }

    public CompletableFuture<Profile> add(Profile profile) throws Exception {
        LOGGER.info("Request to add profile at list --->"+ Thread.currentThread().getName());

        if(profile == null)
            throw new Exception("There is no find any profile to add !!");
        profileMap.put(profile.getProfileName(), profile);
        return CompletableFuture.completedFuture(profile);
    }

    public CompletableFuture<Profile> update(Profile profile) throws Exception {
        LOGGER.info("Request to update message at list --->"+Thread.currentThread().getName());

        if(profile == null)
            throw new Exception("There is no find any profile to update !!");

        profileMap.replace(profile.getProfileName(), profile);
        return CompletableFuture.completedFuture(profile);
    }

    public CompletableFuture<Profile> delete(String profileName) throws Exception {
        if (profileName == null)
            throw new Exception("Profile Name cannot be null");
        if (!profileMap.containsKey(profileName))
            throw new Exception("Profile Name cannot be found in profile list");
        Profile profile = profileMap.remove(profileName);
        return CompletableFuture.completedFuture(profile);
    }

}
