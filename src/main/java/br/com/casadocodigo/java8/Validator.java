package br.com.casadocodigo.java8;

@FunctionalInterface
interface Validador<T> {

	boolean validar(T t);
}