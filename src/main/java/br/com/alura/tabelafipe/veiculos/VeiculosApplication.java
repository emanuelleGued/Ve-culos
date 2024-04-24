package br.com.alura.tabelafipe.veiculos;

import br.com.alura.tabelafipe.veiculos.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeiculosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VeiculosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
