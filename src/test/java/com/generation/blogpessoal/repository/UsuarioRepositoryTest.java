package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {

		usuarioRepository.deleteAll();

		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@gmail.com.br", "13579246",
				"https://i.pinimg.com/564x/e2/0d/34/e20d3487c55e9101439ada5b7e30b4dd.jpg"));

		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manu@gmail.com.br", "13579246",
				"https://i.pinimg.com/564x/a1/04/58/a1045800f7b3630e98db4d19fe4416eb.jpg"));

		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@gmail.com.br", "13579246",
				"https://i.pinimg.com/564x/70/61/45/7061456380ac0ab8914e8b40f9a420c0.jpg"));

		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@gmail.com.br", "13579246",
				"https://i.pinimg.com/564x/c5/89/31/c5893126fbd7530aab95a72452fbd24a.jpg"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@gmail.com.br");

		assertTrue(usuario.get().getUsuario().equals("joao@gmail.com.br"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");

		assertEquals(3, listaDeUsuarios.size());

		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));

	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}

}