package info.beraki.emaillambda;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;


public class ComposeEmail {

    ComposeEmail(){

    }
    ComposeEmail(String username,String password,String replyTo,String subject,String text){
        this.username=username;
        this.password=password;
        this.replyTo=replyTo;
        this.subject=subject;
        this.text=text;
    }

    private String replyTo;

    private String getReplyTo() {
        return replyTo;
    }

    void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
    String to;

    private String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }





    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String password;
    private String subject;
    private String text;


    public Optional<String> send(){

        Optional<String> toReturn= Optional.empty();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        try {
            Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("drogba20020@gmail.com"));
            message.setReplyTo(InternetAddress.parse(getReplyTo()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(getTo()));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            toReturn = Optional.of("Email Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return toReturn;
    }
}