package com.mycompany.monopolio;

/**
 *
 * @author marcos
 */
import java.util.Scanner;

public class Jogo {

    Jogador[] jogadores;
    Tabuleiro tabuleiro;

    int jogadorAtual;
    int totalJogadores;

    Scanner scanner;

    public Jogo(int totalJogadores, Tabuleiro tabuleiro, Scanner scanner) {
        this.totalJogadores = totalJogadores;
        this.tabuleiro = tabuleiro;
        this.scanner = scanner;

    }

    public void iniciarJogo() {
        jogadores = new Jogador[totalJogadores];
        criarJogadores();
        jogar();
    }

    public void criarJogadores() {
        for (int i = 0; i < totalJogadores; i++) {
            System.out.println("Jogador " + (i + 1));
            System.out.println("Digite seu nome: ");
            String nome = scanner.nextLine();

            System.out.println("Escolhar uma cor: [preto, branco, vermelho, verde, azul, amarelo, laranja, rosa]: ");
            String cor = scanner.nextLine();

            jogadores[i] = new Jogador(nome, 1500, cor, 0);
        }
    }

    public void jogar() {
        while (totalJogadores > 1) {
            jogadaJogadorAtual();
            proximoJogador();
        }
    }

    public void jogadaJogadorAtual() {

        System.out.println("\n>>>>>>>>>>>>>>");
        System.out.println("Vez do jogador: " + jogadorAtual);
        System.out.print("Digite 1 para lancar os dados: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            int dado1 = (int) (Math.random() * 6 + 1);
            int dado2 = (int) (Math.random() * 6 + 1);

            int soma = dado1 + dado2;
            int saldo = jogadores[jogadorAtual].getSaldo();
            System.out.println("Dados: " + dado1 + " e " + dado2);
            System.out.println("Somatorio: " + soma);
            System.out.println("Saldo: " + saldo);

            jogadores[jogadorAtual].setPosicao(tabuleiro.getCasaSeguinte(jogadores[jogadorAtual].getPosicao() + soma));

            int novaPosicao = jogadores[jogadorAtual].getPosicao();

            if (novaPosicao <= 40) {
                System.out.println("Nova posicao: " + novaPosicao);
                Propriedade propriedade = tabuleiro.getPropriedade(novaPosicao);
                procedimentoPropriedade(novaPosicao, propriedade);

            } else if (novaPosicao >= 40) {
                jogadores[jogadorAtual].setPosicao(0);

            } 
        }

    }

    public void procedimentoPropriedade(int posicao, Propriedade propriedade) {
        if (propriedade != null) {
            System.out.println("Caiu em " + propriedade.getNome());
            boolean venda = false;
            
            if (propriedade.getPosicao().equals("31")) {
                System.out.println("e foi levado para a prisão. Além disso, fica uma rodada sem jogar. ");
                jogadores[jogadorAtual].setPosicao(10);
            }
           
            
            if (propriedade.getPosicao().equals("1")){
                System.out.println("e ganhou 200,00 ");
                jogadores[jogadorAtual].ganharDinheiro(200);

                
            } else {
                for (int i = 0; i < jogadores.length; i++) {
                if (propriedade.getPosicao().equals(jogadores[i].getCor())) {
                    System.out.println("Essa propriedade ja foi vendida para o jogador " + jogadores[jogadorAtual].getCor());
                    venda = true;
                    break;
                }
            }

            if (venda == true) {
                System.out.println("Você pagou o aluguel de  " + propriedade.getAluguel());
                jogadores[jogadorAtual].fazerPagamento(propriedade.getAluguel());
            }

            if (venda == false) {
                System.out.println("Você deseja comprar essa propriedade pelo preço de " + propriedade.getPreco() + "? (s/n) ");
                String comprar = scanner.nextLine();

                if (comprar.equals("s")) {
                    propriedade.setPosicao(jogadores[jogadorAtual].getCor());
                    jogadores[jogadorAtual].fazerPagamento(propriedade.getPreco());
                    System.out.println("Você comprou a propriedade " + propriedade.getNome() + " por " + propriedade.getPreco());
                }
            }
                
                
            }

            
        }
        
        
    }

    public void proximoJogador() {
        if (jogadorAtual == totalJogadores - 1) {
            jogadorAtual = 0;

        } else {
            jogadorAtual++;
        }

        for (int i = 0; i < totalJogadores; i++) {
            if (jogadores[i].getSaldo() <= 0) {
                System.out.println("O jogador " + i + " faliu.");
                totalJogadores--;

                if (jogadorAtual == totalJogadores) {
                    jogadorAtual = 0;
                }
            }
        }
    }

}
