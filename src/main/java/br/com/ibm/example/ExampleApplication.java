package br.com.ibm.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ExampleApplication.class, args);

		try {
			FileInputStream serviceAccount =
					new FileInputStream("./serviceAccountKey.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

			FirebaseApp.initializeApp(options);
		}
		catch (Exception e) {
			System.out.print("error");
		}
	}

}
