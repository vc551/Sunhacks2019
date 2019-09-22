package sunhacks2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;


public class FireBaseTest {
	static ArrayList<Long> nums = new ArrayList<Long>();
	static ArrayList<String> names = new ArrayList<String>();
	static ArrayList<String> messages = new ArrayList<String>();
	public static void main(String[] args) {
		//FirebaseDatabase.getInstance().goOnline();
		
		/**FileInputStream refreshToken;
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
		}*/
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> query = db.collection("ohgod").get();
		ApiFuture<QuerySnapshot> randoms = db.collection("random").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot;
		QuerySnapshot randomSnapshot;
		try {
			querySnapshot = query.get();
			randomSnapshot= randoms.get();
			QueryDocumentSnapshot randomme = randomSnapshot.getDocuments().get(0);
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {
			  //System.out.println("User: " + document.getId());
			  //System.out.println("Name: " + document.getString("name"));
//			  if (document.contains("middle")) {
//			    System.out.println("Middle: " + document.getString("middle"));
//			  }
			  //System.out.println("Fact: " + document.getString("message"));
			  nums.add(document.getLong("phone"));
			  names.add(document.getString("name"));
			  messages.add(randomme.getString("fact"));
			  //FirebaseDatabase.getInstance().goOffline();
			  //System.out.println("PhNO: " + document.getLong("phone"));
			  
			}
			//System.out.println("\n This is the list we need: " + nums);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

}
	public static ArrayList getList()
	{
		return nums;
	}
	public static ArrayList getNames()
	{
		return names;
	}
	public static ArrayList getMessages()
	{
		return messages;
	}
}