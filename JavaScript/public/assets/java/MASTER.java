package sunhacks2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class MASTER {

	public static void main(String[] args) {
		
		Timer timer = new Timer();
		FileInputStream refreshToken;
		try {
			refreshToken = new FileInputStream("C:\\Users\\ericw\\Downloads\\sunhacks2019-7fdf6-a549428af409.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(refreshToken))
				    .setDatabaseUrl("https://sunhacks2019-7fdf6.firebaseio.com/")
				    .build();
					//System.out.println("Yes");
				FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	timer.schedule( new TimerTask() {
    	    public void run() {
    	    	
    	    	ArrayList<Long> phones = FireBaseTest.getList();
    			ArrayList<String> names = FireBaseTest.getNames();
    			ArrayList<String> messages = FireBaseTest.getMessages();
    			FireBaseTest.main(null);
    			for(int i = 0; i < phones.size(); i++)
    			{
    				Sms.main(null, Long.toString(phones.get(i)), names.get(i), messages.get(i));
    			}
    			phones.clear();
				names.clear();
				messages.clear();
    	    	
    	    }
    	    //below makes the timer 1 hour
    	 }, 0, 2*60*1000);
		
		
		

	}

}
