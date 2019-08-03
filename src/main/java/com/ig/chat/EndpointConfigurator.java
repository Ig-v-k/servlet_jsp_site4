package com.ig.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class EndpointConfigurator extends ServerEndpointConfig.Configurator{
    private static final Logger log = LogManager.getLogger();
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response)
    {
        log.entry();
        super.modifyHandshake(config, request, response);
        config.getUserProperties().put("com.ig.ws.HTTP_SESSION", request.getHttpSession());
        log.exit();
    }
}
