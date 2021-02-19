package com.spring.web.messengerapp.service;

import com.spring.web.exception.MessageServiceException;
import com.spring.web.messengerapp.database.DataBaseClass;
import com.spring.web.messengerapp.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageServiceImpl{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);


    private Map<Long, Message> messageMap = DataBaseClass.getMessageMap();

    @Async
    public CompletableFuture<List<Message>> getAllMessages() throws MessageServiceException {

        LOGGER.info("Request to get a list of messages --->"+ Thread.currentThread().getName());

        final List<Message> messages = findAll();
        return CompletableFuture.completedFuture(messages);
    }

    @Async
    public CompletableFuture<List<Message>> getAllMessagesForYear(int year) throws MessageServiceException {

        LOGGER.info("Request to get a list of messages by year--->"+ Thread.currentThread().getName());

        final List<Message> messages = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Message message : findAll()){
            calendar.setTime(message.getCreated());
            if(calendar.get(Calendar.YEAR) == year)
                messages.add(message);
        }
        return CompletableFuture.completedFuture(messages);
    }

    @Async
    public CompletableFuture<List<Message>> getAllMessagesPaginated(int start, int size) throws MessageServiceException {

        LOGGER.info("Request to get a list of messages paginated --->"+ Thread.currentThread().getName());
        final List<Message> messageList = findAll();
        if(start+size > messageList.size())
            throw new MessageServiceException("Size cannot be over message's size.");
        final List<Message> messages = messageList.subList(start, size);
        return CompletableFuture.completedFuture(messages);
    }


    public List<Message> findAll() throws MessageServiceException {
        if(messageMap.isEmpty())
            throw new MessageServiceException("There is no any message");
        return new ArrayList(messageMap.values());
    }

    @Async
    public CompletableFuture<Message>  findById(long id) throws MessageServiceException {
       LOGGER.info("Request to get list of a message --->"+ Thread.currentThread().getName());

        Optional<Message> optionalMessage =  messageMap.values().stream().filter(x->x.getId() == id).findAny();
        Message message = optionalMessage.orElseThrow(()-> new MessageServiceException("There is no any message by id:"+id));
        return CompletableFuture.completedFuture(message);
    }

    public CompletableFuture<Message> add(Message message) throws MessageServiceException {
        LOGGER.info("Request to add message at list --->"+ Thread.currentThread().getName());

        if(message == null)
            throw new MessageServiceException("There is no find any message to add !!");
        messageMap.put(message.getId(), message);
        return CompletableFuture.completedFuture(message);
    }

    public CompletableFuture<Message> update(Message message) throws MessageServiceException {
        LOGGER.info("Request to update message at list --->"+Thread.currentThread().getName());

        if(message == null)
            throw new MessageServiceException("There is no find any message to update !!");

        messageMap.replace(message.getId(), message);
        return CompletableFuture.completedFuture(message);
    }

    public CompletableFuture<Message> delete(Long id) throws MessageServiceException {
        if (id == null)
            throw new MessageServiceException("Id cannot be null");
        if (!messageMap.containsKey(id))
            throw new MessageServiceException("Id cannot be found in message list");
        Message message = messageMap.remove(id);
        return CompletableFuture.completedFuture(message);
    }
}
