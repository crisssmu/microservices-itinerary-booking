package Flys.Itinerary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"Flys.Itinerary", "Flys.Controllers"})
public class ItineraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItineraryApplication.class, args);
	}

}
