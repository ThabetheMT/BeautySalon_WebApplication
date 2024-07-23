package org.unclecodes.mysalonsystem.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String messageFrom;
    private String subject;
    private String body;
    private String recipient;


}
