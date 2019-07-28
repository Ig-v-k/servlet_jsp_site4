package com.ig.chat;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class EndpointConfigurator extends ServerEndpointConfig.Configurator{
//    String a = ChatEndpoint.getSessionProperty(1);
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response)
    {
        super.modifyHandshake(config, request, response);
        config.getUserProperties().put("com.ig.ws.HTTP_SESSION", request.getHttpSession());
    }
}
