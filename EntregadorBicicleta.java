package com.logistica.entregador;

import com.logistica.model.Entregador;


public class EntregadorBicicleta extends Entregador {

    public EntregadorBicicleta(int id, String nome, String cpf) {
        super(id, nome, cpf);
    }


    @Override
    public double calcularCustoEntrega(double distanciaKm) {
        return distanciaKm * 1.50; 
    }

    @Override
    public double getCapacidadeMaximaKg() {
        return 10.0; 
    }

    @Override
    public String getTipoVeiculo() {
        return "Bicicleta";
    }
}