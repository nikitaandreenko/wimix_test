package com.andreyenka.wimixtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static com.andreyenka.wimixtest.util.ConstantsForWebsocket.SECURED_CHAT_HISTORY;
import static com.andreyenka.wimixtest.util.ConstantsForWebsocket.SECURED_CHAT_SPECIFIC_USER;

@Configuration
public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(SECURED_CHAT_HISTORY, SECURED_CHAT_SPECIFIC_USER);
        config.setApplicationDestinationPrefixes("/spring-security-socket");
        config.setUserDestinationPrefix("/secured/user");
    }
}
