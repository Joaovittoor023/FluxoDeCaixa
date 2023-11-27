package main;

import Utils.Utils;
import modelo.Produto;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    // instancia main

    public static void main(String[] Args) {

        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }
    // print do menu
    private static void menu() {

        System.out.println("Selecione uma opção abaixo: ");
        System.out.println("Opção (1) - Cadastrar ");
        System.out.println("Opção (2) - Listar ");
        System.out.println("Opção (3) - Venda ");
        System.out.println("Opção (4) - Carrinho/Histórico ");
        System.out.println("Opção (5) - Sair ");

        //
        int option = input.nextInt();

        //switch case do menu
        switch (option) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                vendasProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("você saiu");
                System.exit(0);
                break;
            default:
                System.out.println(" Opção invalida ");
                menu();
                break;
        }

    }

    //metodos para o cadastro de produtos
    private static void cadastrarProdutos() {

    // usuario digitar e salvar o nome do produto
        System.out.println("Nome do Produto: ");
        String nome = input.next();

    //usuario digitar e salvar o preço do produto
        System.out.println("Preço do produto: ");
        Double preco = input.nextDouble();

    //instanciando o produto dando o nome e preço, e chamando o produto
        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

    // dizer que o produto foi cadastrado
        System.out.println(produto.getNome() + " o produto foi cadastrado! ");
    }

    // verificar se tem produtos para mostrar e se tiver printalos
    private static void listarProdutos() {
        if (produtos.size() > 0 ) {
            System.out.println("lista dos produtos: ) \n");

            for (Produto p : produtos) {
                System.out.println(p);
            }
        } else {
            System.out.println("Não possui produtos cadastrados!");
        }

    //
        menu();
    }

    // vendas de produtos podutos
    private static void vendasProdutos() {
        if (produtos.size() > 0 ) {
            System.out.println("Código do produto: \n");

    //
            System.out.println(" Estes são os produtos cadastrados ");
            for (Produto p : produtos) {
                System.out.println(p + "\n");
            }
            // pega o id e busca ele
            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Produto p : produtos) {
                if (p.getId() == id) {
                    int quantidade = 0;
                    try {
                        quantidade = carrinho.get(p);
                        // verifica se tem o produto no carrinho e adiciona a qauntidade (+1)
                        carrinho.put(p, quantidade +1);
                    } catch (NullPointerException e) {
                        // se o produto se o produto for o primeiro no carrinho
                        carrinho.put(p, 1);
                    }

                    //quando adicionado no carrinho printar
                    System.out.println(p.getNome() + " foi adicionado no carrinho ");

                    if (isPresent) {
                        System.out.println(" quer adicionar outro produto no carrinho ? \n" +
                                "se sim digite 1, se não digite 0 para finalizar a compra. \n");
                        int option = Integer.parseInt(input.next());

                        if (option == 1) {
                            vendasProdutos();
                        } else {
                            finalizarCompra();
                        }
                    }
                } else {
                    System.out.println("produto não encontrado");
                    menu();
                }
            }
        } else {
            System.out.println("não existem produtos cadastrados");
            menu();
        }
    }

    //
    private static void verCarrinho() {
        System.out.println("Produtos do carrinho:");
        if (carrinho.size() > 0 ) {
            for ( Produto p : carrinho.keySet()) {
                System.out.println("Produto: " + p + "\n" +
                        "Quantidade: " + carrinho.get(p));
            }
        } else {
            System.out.println("Carrinho Vazio");
        }

        //
        menu();
    }

    //
    private static void finalizarCompra() {
        Double valorDaCompra = 0.0;
        System.out.println("Seus Produtos");

        for (Produto p : carrinho.keySet()) {
            int quantidade = carrinho.get(p);
            //vai pegar o valor do produto e multiplicar pela quantidade
            valorDaCompra += p.getPreco() * quantidade;
            System.out.println(p);
            System.out.println("Quantidade: " + quantidade);
        }
    System.out.println("O  valor da Venda é de: " + Utils.doubleToString(valorDaCompra));
    //
    carrinho.clear();
    System.out.println("Feito.");
    menu();
    }
}
