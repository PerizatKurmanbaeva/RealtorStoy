package com.example.RealtorStroy;

import com.example.RealtorStroy.model.Estate;
import com.example.RealtorStroy.model.Role;
import com.example.RealtorStroy.model.User;
import com.example.RealtorStroy.model.repository.EstateRepo;
import com.example.RealtorStroy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

@SpringBootApplication
public class RealtorStroyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtorStroyApplication.class, args);

	}

	@Autowired
	EstateRepo estateRepo;
	@Autowired
	UserService userService;


	@Bean
	public CommandLineRunner bootstrap(){
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {

				File img01 =new File("./src/main/resources/static/assets/images/img_1.png");
				File img02 =new File("./src/main/resources/static/assets/images/img_2.png");
				File img03 =new File("./src/main/resources/static/assets/images/img_3.png");
				File img04 =new File("./src/main/resources/static/assets/images/img_4.png");

				String title1 = "Сottage";
				String title2 = "Сottage";
				String title3 = "Сottage";
				String title4 = "Сottage";

				String content = "bmsabsbvlav;svmcmz..haihfbfml";
				String price = "60000$";

				saveEstate(title1, content, price, img01);
				saveEstate(title2, content, price, img02);
				saveEstate(title3, content, price, img03);
				saveEstate(title4, content, price, img04);

				Role role_admin = new Role("ROLE_ADMIN");
				Role role_user= new Role(("ROLE_USER"));
				User admin = saveUser("Perizat",
						"Kurmanbaeva",
						"123",
						role_admin);
				User user = saveUser("Asel", "Kurmanbaev", "456", role_user);

			}
			private User saveUser(String firstname, String lastname, String username_password, Role role_user) throws IOException {
					User user = new User().setFirstName(firstname)
							.setLastName(lastname)
							.setUsername(username_password)
							.setPassword(username_password)
							.setRoles(Set.of(role_user));

					userService.saveUser(user);
					return user;

			}

			private void saveEstate(String title, String content, String price,File img01) throws IOException {
				try (FileInputStream fileInputStream = new FileInputStream(img01)) {
					Estate estate = new Estate()
							.setCreatedDate(LocalDateTime.now())
							.setTitle(title)
							.setContent(content)
							.setPhoto(fileInputStream.readAllBytes())
							.setPrice(price);
					estateRepo.save(estate);
				}
			}

		};

	}
	@Bean("base64encoder")
	public Base64EncoderToString base64() {
		return bytes -> Base64.getEncoder().encodeToString(bytes);
	}
}

interface Base64EncoderToString{
	String encode(byte[] bytes);
}
