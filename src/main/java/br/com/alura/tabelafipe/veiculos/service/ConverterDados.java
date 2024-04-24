package br.com.alura.tabelafipe.veiculos.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverterDados implements IConverterDados {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterlista(String json, Class<T> classe) {
        // getTypeFactory:  construir uma coleção a partir da classe que for passado
        // constroi uma lista baseado na classe que for passada
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
