package edu.eci.arsw.drawitapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.drawit"})
public class DrawItAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrawItAPIApplication.class, args);
	}
}
