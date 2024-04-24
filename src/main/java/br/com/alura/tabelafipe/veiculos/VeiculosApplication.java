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

	// Sobre portfólio, nós temos o 7daysofcode.io da Alura com desafios gratuitos para você desenvolver projetos em sete dias e rechear seu GitHub! Com isso você pode criar um portfólio para apresentar para empresas no futuro.
	//
	//Recomendamos também o techguide.sh para conferir os próximos passos de aprendizagem, para entender o que você deveria dominar após a base, no nível dois, no nível três, e assim por diante, além de assuntos paralelos que você deveria conhecer atualmente, até de outras áreas, como front-end ou dados.
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
