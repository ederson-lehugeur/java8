package br.com.casadocodigo.java8;

class Capitulo3 {

	public static void main(String[] args) {

		Runnable r1 = new Runnable(){
			@Override
			public void run(){
				for (int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			}
		};
		new Thread(r1).start();

		Runnable r2 = () -> {
			for (int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		};
		new Thread(r2).start();

		new Thread(() -> {
			for (int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		}).start();

		Validador<String> validadorCEP = new Validador<String>() {
			@Override
			public boolean validar(String valor) {
				return valor.matches("[0-9]{5}-[0-9]{3}");
			}
		};

		Validador<String> validadorCEPComLambda =
				valor -> valor.matches("[0-9]{5}-[0-9]{3}");

		System.out.println(validadorCEPComLambda.validar("91000-000"));

		Validador<Integer> validadorNumeroParComLambda =
				valor -> valor % 2 == 0 ? true : false;

		System.out.println(validadorNumeroParComLambda.validar(3));

		Runnable o = () -> {
			System.out.println("O que sou eu? Que lambda?");
		};

		System.out.println(o);
		System.out.println(o.getClass());

		int numero = 5;
		new Thread(() -> System.out.println(numero)).start();

		final int numero2 = 10;
		new Thread(() -> System.out.println(numero2)).start();
	}
}
