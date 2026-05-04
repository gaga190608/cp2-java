package com.logistica.logistica;

import com.logistica.model.Entrega;
import com.logistica.model.Entregador;
import com.logistica.entregador.EntregadorMoto;
import com.logistica.entregador.EntregadorBicicleta;
import com.logistica.entregador.EntregadorCarro;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Entregador> entregadores = new ArrayList<>();
    static ArrayList<Entrega> entregas = new ArrayList<>();
    static int proximoIdEntrega = 1;
    static int proximoIdEntregador = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        System.out.println("======================================");
        System.out.println("  BEM-VINDO AO SISTEMA DE LOGISTICA  ");
        System.out.println("======================================");

        while (opcao != 6) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar entregador");
            System.out.println("2 - Criar entrega");
            System.out.println("3 - Listar entregadores");
            System.out.println("4 - Listar entregas");
            System.out.println("5 - Atribuir entrega a entregador");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            if (opcao == 1) {
                cadastrarEntregador(sc);
            } else if (opcao == 2) {
                criarEntrega(sc);
            } else if (opcao == 3) {
                listarEntregadores();
            } else if (opcao == 4) {
                listarEntregas();
            } else if (opcao == 5) {
                atribuirEntrega(sc);
            } else if (opcao == 6) {
                System.out.println("Encerrando o sistema. Até logo!");
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        sc.close();
    }

    static void cadastrarEntregador(Scanner sc) {
        System.out.println("\n===== CADASTRAR ENTREGADOR =====");
        System.out.println("Tipo de entregador:");
        System.out.println("1 - Moto");
        System.out.println("2 - Bicicleta");
        System.out.println("3 - Carro");
        System.out.print("Escolha: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do entregador: ");
        String nome = sc.nextLine().trim();  

        
        if (nome.isEmpty() || nome.length() < 3) {
            System.out.println("Erro: nome inválido. Deve ter pelo menos 3 caracteres.");
            return;
        }

        System.out.print("CPF (somente números, 11 dígitos): ");
        String cpf = sc.nextLine().trim();  

        if (cpf.length() != 11) {
            System.out.println("Erro: CPF inválido. Deve conter exatamente 11 dígitos.");
            return;
        }

        Entregador novoEntregador = null;

        if (tipo == 1) {
            novoEntregador = new EntregadorMoto(proximoIdEntregador, nome, cpf);
        } else if (tipo == 2) {
            novoEntregador = new EntregadorBicicleta(proximoIdEntregador, nome, cpf);
        } else if (tipo == 3) {
            novoEntregador = new EntregadorCarro(proximoIdEntregador, nome, cpf);
        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        entregadores.add(novoEntregador);
        proximoIdEntregador++;
        System.out.println("Entregador cadastrado com sucesso!");
        novoEntregador.exibirInformacoes();
    }

    static void criarEntrega(Scanner sc) {
        System.out.println("\n===== CRIAR ENTREGA =====");
        sc.nextLine();

        System.out.print("Nome do cliente: ");
        String nomeCliente = sc.nextLine().trim();  

        if (nomeCliente.isEmpty() || nomeCliente.length() < 3) {
            System.out.println("Erro: nome do cliente inválido. Deve ter pelo menos 3 caracteres.");
            return;
        }

        System.out.print("Endereço de destino: ");
        String endereco = sc.nextLine().trim();  

        if (endereco.isEmpty() || endereco.length() < 5) {
            System.out.println("Erro: endereço inválido. Deve ter pelo menos 5 caracteres.");
            return;
        }

        System.out.print("Peso do pacote (kg): ");
        double peso = sc.nextDouble();

        if (peso <= 0) {
            System.out.println("Erro: peso deve ser maior que zero.");
            return;
        }

        Entrega novaEntrega = new Entrega(proximoIdEntrega, nomeCliente, endereco, peso);
        entregas.add(novaEntrega);
        proximoIdEntrega++;

        System.out.println("Entrega criada com sucesso!");
        System.out.println(novaEntrega);
    }

    static void listarEntregadores() {
        System.out.println("\n===== LISTA DE ENTREGADORES =====");

        if (entregadores.isEmpty()) {
            System.out.println("Nenhum entregador cadastrado.");
            return;
        }

        for (Entregador e : entregadores) {
            System.out.println(e);
        }
    }

    static void listarEntregas() {
        System.out.println("\n===== LISTA DE ENTREGAS =====");

        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega cadastrada.");
            return;
        }

        for (Entrega e : entregas) {
            System.out.println(e.rastrearEntrega());
        }
    }

    static void atribuirEntrega(Scanner sc) {
        System.out.println("\n===== ATRIBUIR ENTREGA =====");

        if (entregadores.isEmpty() || entregas.isEmpty()) {
            System.out.println("Cadastre pelo menos um entregador e uma entrega primeiro.");
            return;
        }

        System.out.println("Entregas pendentes:");
        for (Entrega e : entregas) {
            if (e.getStatus().equals("PENDENTE")) {
                System.out.println("ID " + e.getId() + " - " + e.getNomeCliente() +
                                   " (" + e.getPeso() + "kg) -> " + e.getEnderecoDestino());
            }
        }

        System.out.print("ID da entrega: ");
        int idEntrega = sc.nextInt();

        System.out.println("\nEntregadores disponíveis:");
        for (Entregador en : entregadores) {
            if (en.isDisponivel()) {
                System.out.println("ID " + en.getId() + " - " + en);
            }
        }

        System.out.print("ID do entregador: ");
        int idEntregador = sc.nextInt();

        Entrega entregaSelecionada = null;
        Entregador entregadorSelecionado = null;

        for (Entrega e : entregas) {
            if (e.getId() == idEntrega) {
                entregaSelecionada = e;
            }
        }

        for (Entregador en : entregadores) {
            if (en.getId() == idEntregador) {
                entregadorSelecionado = en;
            }
        }

        if (entregaSelecionada == null || entregadorSelecionado == null) {
            System.out.println("Entrega ou entregador não encontrado!");
            return;
        }

        if (!entregaSelecionada.getStatus().contains("PENDENTE")) {
            System.out.println("Erro: essa entrega não está mais pendente.");
            return;
        }

        if (entregaSelecionada.getPeso() > entregadorSelecionado.getCapacidadeMaximaKg()) {
            System.out.println("Erro: pacote muito pesado para esse entregador!");
            System.out.println("Capacidade máxima: " + entregadorSelecionado.getCapacidadeMaximaKg() + "kg");
            return;
        }

        entregaSelecionada.setEntregadorResponsavel(entregadorSelecionado.getNome());
        entregadorSelecionado.setDisponivel(false);

        System.out.print("Distância estimada em km: ");
        double distancia = sc.nextDouble();

        if (distancia <= 0) {
            System.out.println("Erro: distância deve ser maior que zero.");
            return;
        }

        double custo = entregadorSelecionado.calcularCustoEntrega(distancia);

        int custoInteiro = (int) custo;

        entregaSelecionada.atualizarStatus("EM_ROTA",
            "Entregador " + entregadorSelecionado.getNome() + " saiu para entrega!");

        System.out.println("Custo estimado: R$ " + String.format("%.2f", custo));
        System.out.println("Custo aproximado (sem centavos): R$ " + custoInteiro); 
        System.out.println(entregaSelecionada.obterLocalizacaoAtual());
    }
}