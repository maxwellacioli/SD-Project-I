package br.com.ic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.com.ic.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"br.com.ic"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
