package br.com.casadocodigo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Capitulo7 {
	public static void main (String... args) throws Exception {

		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 90);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		usuarios.subList(0,1).forEach(Usuario::tornaModerador);

		usuarios.forEach(u -> System.out.println(u.isModerador()));

		Collections.sort(usuarios, new Comparator<Usuario>() {
			@Override
			public int compare(Usuario u1, Usuario u2) {
				return u1.getPontos() - u2.getPontos();
			}
		});

		Collections.reverse(usuarios);
		List<Usuario> top10 = usuarios.subList(0, 1);
		for(Usuario usuario : top10) {
			usuario.tornaModerador();
		}

		Stream<Usuario> stream = usuarios
				.stream()
				.filter(u -> u.getPontos() > 100);

		stream.forEach(System.out::println);
		// Os métodos da interface Stream não alteram os elementos do stream original
		usuarios.forEach(System.out::println);

		usuarios.stream().filter(u -> u.getPontos() > 140).forEach(Usuario::tornaModerador);
		usuarios.stream().filter(Usuario::isModerador).forEach(System.out::println);

		List<Usuario> maisQue110 = new ArrayList<>();

		//usuarios.stream().filter(u -> u.getPontos() > 100).forEach(u -> maisQue110.add(u));
		usuarios.stream().filter(u -> u.getPontos() > 100).forEach(maisQue110::add);

		maisQue110.forEach(u -> System.out.println(u.getNome()));

		Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
		BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
		BiConsumer<ArrayList<Usuario>,ArrayList<Usuario>> combiner = ArrayList::addAll;

		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.collect(supplier, accumulator, combiner);

		usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		List<Usuario> maisQue100 = usuarios.stream()
			.filter(u -> u.getPontos() > 100)
			.collect(Collectors.toList());

		ArrayList<Integer> pontos = new ArrayList<>();
		usuarios.forEach(u -> pontos.add(u.getPontos()));

		List<Integer> pontos2 = usuarios.stream()
			.map(u -> u.getPontos())
			.collect(Collectors.toList());

		List<Integer> pontos3 = usuarios.stream()
			.map(Usuario::getPontos)
			.collect(Collectors.toList());

		Stream<Integer> stream2 = usuarios.stream()
			.map(Usuario::getPontos);

		IntStream stream3 = usuarios.stream()
			.mapToInt(Usuario::getPontos);

		double pontuacaoMedia = usuarios.stream()
			.mapToInt(Usuario::getPontos)
			.average()
			.getAsDouble();

		usuarios.stream()
			.mapToInt(Usuario::getPontos)
			.average()
			.orElseThrow(IllegalStateException::new);

		List<Usuario> vazia = Collections.emptyList();

		double media = vazia.stream()
			.mapToInt(Usuario::getPontos)
			.average()
			.orElse(0.0);

		OptionalDouble media2 = usuarios
			.stream()
			.mapToInt(Usuario::getPontos)
			.average();

		double pontuacaoMedia2 = media2.orElse(0.0);

		Optional<Usuario> max = usuarios
			.stream()
			.max(Comparator.comparingInt(Usuario::getPontos));

		Optional<String> maxNome = usuarios
			.stream()
			.max(Comparator.comparingInt(Usuario::getPontos))
			.map(u -> u.getNome());

		Optional<String> maxNome2 = usuarios
				.stream()
				.max(Comparator.comparingInt(Usuario::getPontos))
				.map(Usuario::getNome);
	}
}

