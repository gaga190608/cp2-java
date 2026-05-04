# 📦 Sistema de Logística para Entregas — CP2

## 👥 Integrantes do Grupo

|Nome              |RM    |
|------------------|------|
|Jéssica Domingues |562973|
|Kauã              |566371|
|João Vitor Piccolo|565127|
|Leonardo Lopes    |561349|
|Gabrielle Calazans|564460|

-----

## 📌 Explicação do Sistema

O sistema simula uma operação de logística de entregas para e-commerce. Ele permite cadastrar entregadores de diferentes tipos (moto, bicicleta e carro), criar pedidos de entrega, atribuí-los a um entregador disponível com cálculo de custo proporcional à distância, acompanhar o status de cada entrega em tempo real e liberar o entregador quando a entrega é concluída ou cancelada.

O projeto foi desenvolvido em Java, com execução via terminal e menu interativo utilizando `Scanner`.

-----

## 🗂️ Decisões de Modelagem

### Por que `Entregador` é uma classe abstrata?

Entregador representa um conceito genérico que não faz sentido instanciar diretamente — não existe um “entregador genérico”, sempre será de moto, bicicleta ou carro. Por isso, ela é abstrata e define os métodos que cada subtipo obrigatoriamente deve implementar: `calcularCustoEntrega`, `getCapacidadeMaximaKg` e `getTipoVeiculo`.

### Por que criar a interface `Rastreavel`?

A operação de rastreamento é um comportamento que pode ser aplicado a diferentes entidades do domínio (não só entregas). Usar uma interface garante um contrato claro: qualquer classe que implemente `Rastreavel` deve fornecer `rastrearEntrega()` e `obterLocalizacaoAtual()`. Isso torna o sistema mais extensível.

### Como foi feita a sobrecarga?

O método `atualizarStatus` existe em duas versões na classe `Entrega`:

- `atualizarStatus(String novoStatus)` — atualiza o status sem mensagem adicional
- `atualizarStatus(String novoStatus, String mensagem)` — atualiza o status e exibe uma mensagem ao operador

### Como foi feita a sobrescrita?

Cada subclasse de `Entregador` sobrescreve os três métodos abstratos com sua própria lógica (custo por km, capacidade e nome do veículo). A classe `Entrega` sobrescreve `rastrearEntrega()` e `obterLocalizacaoAtual()` definidos pela interface `Rastreavel`.

-----

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Terminal / Prompt de Comando

### Passos

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/cp2-java.git
cd cp2-java
```

1. Compile todos os arquivos Java a partir da raiz do projeto:

```bash
javac com/logistica/interfaces/Rastreavel.java
javac com/logistica/model/Entregador.java
javac com/logistica/model/Entrega.java
javac com/logistica/entregador/EntregadorMoto.java
javac com/logistica/entregador/EntregadorBicicleta.java
javac com/logistica/entregador/EntregadorCarro.java
javac com/logistica/logistica/Main.java
```

Ou compile tudo de uma vez:

```bash
javac com/logistica/**/*.java com/logistica/logistica/Main.java
```

1. Execute a classe Main:

```bash
java com.logistica.logistica.Main
```

### Menu disponível

```
1 - Cadastrar entregador
2 - Criar entrega
3 - Listar entregadores
4 - Listar entregas
5 - Atribuir entrega a entregador
6 - Atualizar status de entrega
7 - Sair
```

-----

## 🗺️ Diagrama de Classes UML

## 📝 Perguntas Discursivas

### 1. Herança

**Explique como a herança foi utilizada no seu sistema. Qual problema ela resolveu e quais classes estão envolvidas?**

A herança foi aplicada por meio da classe abstrata `Entregador`, que concentra os atributos e comportamentos comuns a todos os tipos de entregador: `id`, `nome`, `cpf`, `disponivel`, além dos métodos `exibirInformacoes()` e `toString()`. As subclasses `EntregadorMoto`, `EntregadorBicicleta` e `EntregadorCarro` herdam essa base e implementam os métodos abstratos de acordo com as características de cada veículo.

O principal problema resolvido foi a eliminação de repetição de código: sem herança, cada tipo de entregador teria de redefinir os mesmos atributos e métodos comuns. Com a herança, essas definições existem em um único lugar, facilitando manutenção e extensão. Se futuramente surgir um novo tipo de entregador (por exemplo, drone), basta criar uma nova subclasse sem alterar as já existentes.

A hierarquia envolvida é: `Entregador` (abstrata) ← `EntregadorMoto`, `EntregadorBicicleta`, `EntregadorCarro`.

-----

### 2. Interfaces

**Qual interface foi criada no sistema? Por que você decidiu utilizá-la e qual vantagem ela trouxe?**

A interface criada foi `Rastreavel`, localizada no pacote `com.logistica.interfaces`. Ela define dois métodos: `rastrearEntrega()`, que retorna um resumo completo do estado da entrega, e `obterLocalizacaoAtual()`, que informa a situação geográfica/logística do pacote com base no status atual.

A decisão de criar essa interface veio da percepção de que o comportamento de rastreamento é um contrato independente da estrutura de herança. Não é algo exclusivo de um tipo específico de objeto — outras entidades do sistema (como um veículo ou um ponto de coleta) poderiam, futuramente, implementar o mesmo comportamento sem precisar herdar de `Entrega`.

A vantagem principal foi a separação de responsabilidades: a interface define **o que** deve ser feito, e a classe `Entrega` define **como** é feito. Isso torna o sistema mais flexível, já que o código que utiliza `Rastreavel` não precisa conhecer a implementação concreta, apenas o contrato da interface.

-----

### 3. Classe Abstrata

**Explique o papel da classe abstrata no seu sistema. Por que ela não poderia ser uma classe comum?**

A classe abstrata `Entregador` serve como base estrutural para todos os tipos de entregador do sistema. Ela define os atributos que todos compartilham (`id`, `nome`, `cpf`, `disponivel`) e os métodos comuns (`exibirInformacoes`, `toString`), mas declara como abstratos os métodos que cada tipo precisa implementar de forma própria: `calcularCustoEntrega(double distanciaKm)`, `getCapacidadeMaximaKg()` e `getTipoVeiculo()`.

Ela não poderia ser uma classe comum por dois motivos principais. Primeiro, não faz sentido no domínio do problema instanciar um “Entregador” genérico — todo entregador real pertence a um tipo específico. A abstração impede que esse erro seja cometido em tempo de compilação. Segundo, os métodos abstratos funcionam como um contrato obrigatório: qualquer nova subclasse é forçada a implementar `calcularCustoEntrega`, `getCapacidadeMaximaKg` e `getTipoVeiculo`, garantindo que o comportamento esperado esteja sempre presente. Em uma classe comum, esses métodos precisariam ter alguma implementação genérica (possivelmente incorreta ou vazia), o que abriria espaço para subclasses incompletas passarem despercebidas.
