package com.example.gerenciadorDePedidos;

import com.example.gerenciadorDePedidos.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorDePedidosApplication implements CommandLineRunner {
	@Autowired
	Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDePedidosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		principal.executar();
	}
}

