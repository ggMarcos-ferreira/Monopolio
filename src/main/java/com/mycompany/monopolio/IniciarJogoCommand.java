package com.mycompany.monopolio;

import java.util.Scanner;

//Abdeel

/**
 * Comando para iniciar o jogo.
 */
public class IniciarJogoCommand implements Command {
  private Scanner scanner;
  private Tabuleiro tabuleiro;

  public IniciarJogoCommand(Scanner scanner, Tabuleiro tabuleiro) {
    this.scanner = scanner;
    this.tabuleiro = tabuleiro;
  }

  @Override
  public void execute() {
    System.out.print("Digite o numero de jogadores (2-8): ");
    int numeroJogadores = scanner.nextInt();
    scanner.nextLine();
    Jogo jogo = new Jogo(numeroJogadores, tabuleiro, scanner);
    jogo.iniciarJogo();
  }
}
