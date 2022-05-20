package com.example.RealtorStroy;

import com.example.RealtorStroy.model.Estate;
import com.example.RealtorStroy.model.User;
import com.example.RealtorStroy.model.repository.EstateRepo;
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

@SpringBootApplication
public class RealtorStroyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtorStroyApplication.class, args);

	}

	@Autowired
	EstateRepo estateRepo;

	@Bean
	public CommandLineRunner bootstrap(){
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {

				File img01 =new File("./src/main/resources/static/assets/images/img_1.png");
				File img02 =new File("./src/main/resources/static/assets/images/img_2.png");
				File img03 =new File("./src/main/resources/static/assets/images/img_3.png");
				File img04 =new File("./src/main/resources/static/assets/images/img_4.png");

				String title1 = "Simple and useful HTML layout";
				String title2 = "Multi-purpose blog template";
				String title3 = "How can you apply Xtra Blog";
				String title4 = "A little restriction to apply";

				String content = "bmsabsbvlav;svmcmz..haihfbfml";
				String price = "60000$";

				saveEstate(title1, content, price, img01);
				saveEstate(title2, content, price, img02);
				saveEstate(title3, content, price, img03);
				saveEstate(title4, content, price, img04);

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
