package com.ig.servlet.listeners;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public final class SessionRegistry {
    private static final Map<String, HttpSession> SESSIONS = new Hashtable<>();
    static void addSession(HttpSession session) {
        SESSIONS.put(session.getId(), session);
    }
    static void updateSessionId(HttpSession session, String oldSessionId) {
        synchronized (SESSIONS) {
            SESSIONS.remove(oldSessionId);
            addSession(session);
        }
    }
    static void removeSession(HttpSession session) {
        SESSIONS.remove(session.getId());
    }
    public static List<HttpSession> getAllSession() {
        return new ArrayList<>(SESSIONS.values());
    }
    public static int getNumberOfSession() {
        return SESSIONS.size();
    }
}
