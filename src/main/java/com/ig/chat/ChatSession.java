package com.ig.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatSession {
    private static final Logger log = LogManager.getLogger();
    private long sessionId;
    private String customerUsername;
    private Session customer;
    private String representativeUsername;
    private Session representative;
    private ChatMessage creationMessage;
    private final List<ChatMessage> chatLog = new ArrayList<>();

    long getSessionId() {
        return sessionId;
    }

    void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    Session getCustomer() {
        return customer;
    }

    void setCustomer(Session customer) {
        this.customer = customer;
    }

    public String getRepresentativeUsername() {
        return representativeUsername;
    }

    void setRepresentativeUsername(String representativeUsername) {
        this.representativeUsername = representativeUsername;
    }

    Session getRepresentative() {
        return representative;
    }

    void setRepresentative(Session representative) {
        this.representative = representative;
    }

    ChatMessage getCreationMessage() {
        return creationMessage;
    }

    void setCreationMessage(ChatMessage creationMessage) {
        this.creationMessage = creationMessage;
    }

    @JsonIgnore
    void log(ChatMessage message) {
        log.trace("Chat message logged for session {}.", this.sessionId);
        this.chatLog.add(message);
    }

    @JsonIgnore
    void writeChatLog(File file) throws IOException {
        log.debug("Writing chat log to file {}.", file);
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try(FileOutputStream stream = new FileOutputStream(file)) {
            mapper.writeValue(stream, this.chatLog);
        }
        log.exit();
    }
}
