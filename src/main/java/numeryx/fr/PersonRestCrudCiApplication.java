package numeryx.fr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PersonRestCrudCiApplication {

	public static void main(String[] args) {
//		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
//		System.out.println(encode.encode("user"));
		SpringApplication.run(PersonRestCrudCiApplication.class, args);
	}

}
