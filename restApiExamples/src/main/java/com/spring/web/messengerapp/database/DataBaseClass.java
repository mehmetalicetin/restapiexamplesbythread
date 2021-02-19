package com.spring.web.messengerapp.database;

import com.spring.web.messengerapp.model.Message;
import com.spring.web.messengerapp.model.Profile;

import java.util.*;

public class DataBaseClass {
    private static Map<Long, Message> messageMap = new HashMap<>();

    private static Map<String, Profile> profileMap = new HashMap<>();

    public static Map<Long, Message> getMessageMap() {
        Message message1 = new Message(1l,"hello world!!",new Date(),"ali");
        Message message2 = new Message(2l,"hello jersey!!",new Date(),"mehmet");
        messageMap.put(message1.getId(),message1);
        messageMap.put(message2.getId(),message2);
        return messageMap;
    }

    public static Map<String, Profile> getProfileMap() {
        Profile profile1 = new Profile(1l,"mehmetali","Mehmet","Cetin");
        Profile profile2 = new Profile(2l,"latifddd","Latif","Korkmaz");
        profileMap.put(profile1.getProfileName(), profile1);
        profileMap.put(profile2.getProfileName(), profile2);
        return profileMap;
    }
}
