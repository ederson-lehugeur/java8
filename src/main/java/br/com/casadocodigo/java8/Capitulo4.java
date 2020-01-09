package br.com.casadocodigo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Capitulo4 {

	public static void main(String... args) {

		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 190);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nomes");

		Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());

		Consumer<Usuario> mostraSegundaMensagem = u -> System.out.println("depois de imprimir os nomes");

		usuarios.forEach(mostraMensagem.andThen(imprimeNome).andThen(mostraSegundaMensagem));

		List<Usuario> usuarios2 = new ArrayList<>();
		usuarios2.addAll(usuarios);

		Predicate<Usuario> predicado = new Predicate<Usuario>() {
			@Override
			public boolean test(Usuario u) {
				return u.getPontos() > 160;
			}
		};

		usuarios2.removeIf(predicado);
		usuarios2.forEach(u -> System.out.println(u));

		usuarios2.removeIf(u -> u.getPontos() > 140);
		usuarios2.forEach(u -> System.out.println(u));
	}
}