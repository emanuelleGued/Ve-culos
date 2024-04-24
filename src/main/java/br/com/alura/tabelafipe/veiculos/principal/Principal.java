package br.com.alura.tabelafipe.veiculos.principal;


import br.com.alura.tabelafipe.veiculos.model.Dados;
import br.com.alura.tabelafipe.veiculos.model.Modelos;
import br.com.alura.tabelafipe.veiculos.model.Veiculo;
import br.com.alura.tabelafipe.veiculos.service.ConsumoApi;
import br.com.alura.tabelafipe.veiculos.service.ConverterDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverterDados conversor = new ConverterDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
            *** OPÇÕES ***
            Carro
            Moto
            Caminhão
            
            Digite uma das opções para consulta:
            
            """;

        System.out.println(menu);
        var opcao = leitura.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }



        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterlista(json, Dados.class);
        // para obter a lista de marcase imprimir na ordem:
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
        System.out.println("Informe o codigo da marca para consulta: ");
        var codigoMarca = leitura.nextLine();

        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consumo.obterDados(endereco);
        // o modelos ja esta representado com uma lista e nao precisa usar o metodo obterLista
        var modelosLista = conversor.obterDados(json, Modelos.class);

        System.out.println("\nModelos dessa marca: ");
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("\nInforme o trecho do veiculo: ");
        var veiculo = leitura.nextLine();

        List<Dados> modelosFiltrados = modelosLista.modelos().stream()
                .filter(v -> v.nome().toLowerCase().contains(veiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Informe o codigo do veiculo que voce quer buscar os valores: ");
        var codigoVeiculo = leitura.nextLine();

        endereco = endereco + "/" + codigoVeiculo + "/anos";
        json = consumo.obterDados(endereco);
        List<Dados> anos = conversor.obterlista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = endereco + "/" + anos.get(i).codigo();
            json = consumo.obterDados(enderecoAnos);
            Veiculo veiculo1= conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculo1);
        }
        System.out.println("Todos os veiculos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);



    }
}
