package modelo;

import Utils.Utils;
import jdk.jshell.execution.Util;

public class Produto {
    // contador
    private static int count = 1;
    //
    private int id;
    private String nome;
    private Double preco;

    // sempre que adicionar um produto vai contar +1 portanto o id será ex: 1,2,3...
    public Produto(String nome, Double preco) {
        this.id = count;
        this.nome = nome;
        this.preco = preco;
        Produto.count += 1;
    }
    // get e setters
    public int getId() {
        return id;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    // declarando uma variavel toString e para retornar um id para o produto

    public String toString() {
        return "id: " + this.getId() +
                "\nNome: " + this.getNome() +
                "\nPreco: " + Utils.doubleToString(this.getPreco());
    }

}
