package com.logistica.model;

public abstract class Entregador {


    private int id;
    private String nome;
    private String cpf;
    private boolean disponivel;

    public Entregador(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.disponivel = true;
    }

    public abstract double calcularCustoEntrega(double distanciaKm);

    public abstract double getCapacidadeMaximaKg();

    public abstract String getTipoVeiculo();

    public void exibirInformacoes() {
        System.out.println("==============================");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipoVeiculo());
        System.out.println("Capacidade máxima: " + getCapacidadeMaximaKg() + "kg");
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
        System.out.println("==============================");
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        return "[" + getTipoVeiculo() + "] " + nome + " (ID: " + id + ") - " +
               (disponivel ? "Disponível" : "Ocupado");
    }
}
