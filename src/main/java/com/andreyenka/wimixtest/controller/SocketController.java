package com.andreyenka.wimixtest.controller;

import com.andreyenka.wimixtest.entity.socket.Message;
import com.andreyenka.wimixtest.entity.socket.OutputMessage;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.andreyenka.wimixtest.util.ConstantsForWebsocket.*;

@RestController
public class SocketController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(SECURED_CHAT)
    @SendTo(SECURED_CHAT_HISTORY)
    public OutputMessage sendAll(com.andreyenka.wimixtest.entity.socket.Message msg) throws Exception {
        OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(), new SimpleDateFormat("HH:mm").format(new Date()));
        return out;
    }
    @MessageMapping(SECURED_CHAT_ROOM)
    public void sendSpecific(@Payload Message msg, Principal user, @Header("simpSessionId") String sessionId) throws Exception {
        OutputMessage out = new OutputMessage(msg.getFrom(), msg.getText(), new SimpleDateFormat("HH:mm").format(new Date()));
        simpMessagingTemplate.convertAndSendToUser(msg.getTo(), SECURED_CHAT_SPECIFIC_USER, out);
    }
}
