package org.unclecodes.mysalonsystem.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MessageEmail {

    private final JavaMailSender javaMailSender;
    @Value("$(spring.mail.username)")
    private String sender;

    public MessageEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Message message){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(message.getRecipient());
        simpleMailMessage.setSubject(message.getSubject());
        simpleMailMessage.setText(message.getBody());

        javaMailSender.send(simpleMailMessage);
    }
}
