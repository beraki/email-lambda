package info.beraki.emaillambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class Main {
    public static void main(String args[]){
        try {
            ComposeEmail email= new ComposeEmail();
            email.setUsername(System.getenv("username"));
            email.setPassword(System.getenv("password"));
            email.setReplyTo(System.getenv("replyto"));
            email.setSubject("Checking with System Env");
            email.setText("This is an email sent from m function using System env variables passing though SMTP.");
            System.out.print(email.send());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendMail(String input, Context context){

        try {
            JSONObject inputObject=new JSONObject(input);
            if(inputObject.has("email") &&
                inputObject.has("name") &&
                    inputObject.has("message") &&
                        inputObject.has("telephone")){

                //TODO: parse data to vars
                String email=inputObject.getString("email");
                String name=inputObject.getString("name");
                String message=inputObject.getString("message");
                String telephone=inputObject.getString("telephone");

                //TODO: compose email text
                String composeEmailText="You have a new email though your website\n\r" +
                        "Name: " + name + "\r\n" +
                        "Email: " + email + "\r\n" +
                        "Telephone: " + telephone + "\r\n" +
                        "Message: " + message;

                ComposeEmail composeEmail= new ComposeEmail();
                composeEmail.setUsername(System.getenv("username"));
                composeEmail.setPassword(System.getenv("password"));
                composeEmail.setTo(System.getenv("adminEmail"));
                composeEmail.setReplyTo(email);
                composeEmail.setSubject("You have a new Website Message");
                composeEmail.setText(composeEmailText);
                Optional<String> returned=composeEmail.send();
                if(returned.isPresent()){
                    System.out.println(returned.get());
                }else{
                    System.out.println("Problem sending message");
                }
            }else{
                //TODO: handle missing request
                System.out.println("Exiting insufficient data provided");
                System.exit(1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
