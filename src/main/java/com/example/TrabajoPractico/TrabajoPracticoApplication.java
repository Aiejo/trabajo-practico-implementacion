package com.example.TrabajoPractico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TrabajoPracticoApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("CONNECTION_URL", dotenv.get("CONNECTION_URL"));
		System.out.println(dotenv.get("CONNECTION_URL"));
		System.setProperty("USER", dotenv.get("USER"));
		System.setProperty("PASSWORD", dotenv.get("PASSWORD"));
		System.out.println(dotenv.get("CONNECTION_URL"));
		SpringApplication.run(TrabajoPracticoApplication.class, args);
	}

}
