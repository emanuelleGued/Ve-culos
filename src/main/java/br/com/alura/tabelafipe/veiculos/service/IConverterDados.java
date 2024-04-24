package br.com.alura.tabelafipe.veiculos.service;

import java.util.List;

public interface IConverterDados {
    //esse metodo só retorna um dado
    <T> T obterDados(String json, Class<T> classe);

    // esse metodo retorna uma lista de dados
    <T> List<T> obterlista(String json, Class<T> classe);

}
