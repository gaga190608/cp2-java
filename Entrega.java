package com.logistica.model;

public class Entrega implements com.logistica.interfaces.Rastreavel {

    private int id;
    private String enderecoDestino;
    private String status; 
    private String nomeCliente;
    private double peso;
    private String entregadorResponsavel;

    public Entrega(int id, String nomeCliente, String enderecoDestino, double peso) {
        this.id = id;
        this.nomeCliente = nomeCliente.trim();           
        this.enderecoDestino = enderecoDestino.trim();   
        this.peso = peso;
        this.status = "PENDENTE";
        this.entregadorResponsavel = "Nenhum";
    }


    public void atualizarStatus(String novoStatus) {
        if (novoStatus == null || novoStatus.trim().isEmpty()) {  
            System.out.println("Erro: status não pode ser vazio.");
            return;
        }
        this.status = novoStatus.trim().toUpperCase();           
        System.out.println("Status atualizado para: " + this.status);
    }

    public void atualizarStatus(String novoStatus, String mensagem) {
        if (novoStatus == null || novoStatus.trim().isEmpty()) {  
            System.out.println("Erro: status não pode ser vazio.");
            return;
        }
        if (mensagem == null || mensagem.trim().isEmpty()) {     
            System.out.println("Erro: mensagem não pode ser vazia.");
            return;
        }
        if (mensagem.trim().length() > 100) {                     
            System.out.println("Erro: mensagem não pode ter mais de 100 caracteres.");
            return;
        }
        this.status = novoStatus.trim().toUpperCase();           
        System.out.println("Status atualizado para: " + this.status);
        System.out.println("Mensagem: " + mensagem.trim());      
    }

    @Override
    public String rastrearEntrega() {
        return "Entrega #" + id + " | Cliente: " + nomeCliente +
               " | Status: " + status + " | Entregador: " + entregadorResponsavel;
    }

    @Override
    public String obterLocalizacaoAtual() {
        if (status.equals("PENDENTE")) {
            return "Entrega ainda não saiu para entrega.";
        } else if (status.equals("EM_ROTA")) {
            return "Entrega a caminho de: " + enderecoDestino;
        } else if (status.equals("ENTREGUE")) {
            return "Entrega concluída em: " + enderecoDestino;
        } else {
            return "Entrega cancelada.";
        }
    }

    public int getId() { return id; }
    public String getStatus() { return status; }
    public String getNomeCliente() { return nomeCliente; }
    public String getEnderecoDestino() { return enderecoDestino; }
    public double getPeso() { return peso; }
    public String getEntregadorResponsavel() { return entregadorResponsavel; }
    public void setEntregadorResponsavel(String nome) { this.entregadorResponsavel = nome; }

    @Override
    public String toString() {
        return "Entrega{id=" + id + ", cliente=" + nomeCliente +
               ", destino=" + enderecoDestino + ", status=" + status +
               ", peso=" + peso + "kg, entregador=" + entregadorResponsavel + "}";
    }
}