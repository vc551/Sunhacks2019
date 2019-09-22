package sunhacks2;
import java.util.ArrayList;
import java.util.Random;

// Install the Java helper library from twilio.com/docs/libraries/java
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Sms {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
           /*API DATA GOES HERE*/);
    public static final String AUTH_TOKEN =
           /*API DATA GOES HERE*/);

    public static void main(String[] args, String num, String name, String message2) {
    	Random rand = new Random();
    	int rando = rand.nextInt(7);
    	ArrayList<String> list = new ArrayList<String>();
    	list.add("Hey " + name + ", did you know that ");
    	list.add(name + ", I bet you're getting tired of these by now. By the way, did you know that ");
    	list.add(name + "!!! Time to learn something!!! ");
    	list.add("I bet you're doing something unproductive right now... here's a fact: ");
    	list.add("I hope you're getting quiz ready for this stuff " + name + ". Next fact: ");
    	list.add("you up...?\nGood. Because it's time to freaking learn. New fact: ");
    	list.add("aye, lemme get yo number. tahaha, sike, I already got it. But did you know that ");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String fact = "2+2=4";
        Message message = Message
                .creator(new PhoneNumber("+1" + num), // to
                        new PhoneNumber(/*API DATA GOES HERE*/)), // from: Twilio generated number
                        list.get(rando) + message2) //Message here 
                .create();

        System.out.println(message.getSid());
    }
}