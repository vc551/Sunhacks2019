package sunhacks2;
// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Sms {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC401a7899856ad073cda20ad8b8d0757b";
    public static final String AUTH_TOKEN =
            "5e67e9e63d3651ff6483b75237138159";

    public static void main(String[] args, String num, String name, String message2) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fact = "2+2=4";
        Message message = Message
                .creator(new PhoneNumber("+1" + num), // to
                        new PhoneNumber("+12173963079"), // from: Twilio generated number
                        "Hey " + name + ", did you know that " + message2) //Message here 
                .create();

        System.out.println(message.getSid());
    }
}