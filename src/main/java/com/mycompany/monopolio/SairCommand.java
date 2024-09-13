package com.mycompany.monopolio;

//Abdeel

/**
 * Comando para sair do menu.
 */
public class SairCommand implements Command {
  @Override
  public void execute() {
    System.out.println("Saindo do jogo...");
  }
}
