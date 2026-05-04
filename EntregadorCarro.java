package com.logistica.entregador;

import com.logistica.model.Entregador;


public class EntregadorCarro extends Entregador {

    public EntregadorCarro(int id, String nome, String cpf) {
        super(id, nome, cpf);
    }

    @Override
    public double calcularCustoEntrega(double distanciaKm) {
        return distanciaKm * 4.00; 
    }

    @Override
    public double getCapacidadeMaximaKg() {
        return 100.0; 
    }

    @Override
    public String getTipoVeiculo() {
        return "Carro";
    }
}