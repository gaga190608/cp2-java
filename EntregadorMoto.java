package com.logistica.entregador;

import com.logistica.model.Entregador;

public class EntregadorMoto extends Entregador {

    public EntregadorMoto(int id, String nome, String cpf) {
        super(id, nome, cpf); 
    }

    @Override
    public double calcularCustoEntrega(double distanciaKm) {
        return distanciaKm * 2.50; 
    }

    @Override
    public double getCapacidadeMaximaKg() {
        return 20.0;
    }

    @Override
    public String getTipoVeiculo() {
        return "Moto";
    }
}
