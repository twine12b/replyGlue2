package com.replyglue.demo;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//TODO - remove bean before packaging
	//TODO - remove INFO and Logs from application properties

//	@Bean
//	public CommandLineRunner data(RegistrationRepository repository) {
//		return args -> {
//			Stream.of(
//					new User(1l, "a", "a", "a", new GregorianCalendar(1972, Calendar.JUNE, 21)),
//					new User(2l, "b", "b", "b", new GregorianCalendar(1984, Calendar.FEBRUARY,1)),
//					new User(3l,"c", "c", "c", new GregorianCalendar(2014, Calendar.NOVEMBER, 18))
//			).forEach(repository::save);
//		};
//	}
}
