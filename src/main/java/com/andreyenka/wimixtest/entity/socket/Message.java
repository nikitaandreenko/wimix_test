package com.andreyenka.wimixtest.entity.socket;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Message {

        private String from;
        private String to;
        private String text;

}
