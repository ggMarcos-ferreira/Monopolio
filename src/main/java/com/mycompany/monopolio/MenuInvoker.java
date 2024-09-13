package com.mycompany.monopolio;

import java.util.HashMap;
import java.util.Map;

//Abdeel

/**
 * Invocador que gerencia e executa comandos.
 */
public class MenuInvoker {
  private Map<Integer, Command> commands = new HashMap<>();

  /**
   * Define um comando para uma opção específica.
   *  opcao   A opção do menu.
   *  command O comando a ser executado.
   */
  public void setCommand(int opcao, Command command) {
    commands.put(opcao, command);
  }

  /**
   * Executa o comando associado a uma opção.
   * opcao A opção do menu.
   */
  public void executarComando(int opcao) {
    Command command = commands.get(opcao);
    if (command != null) {
      command.execute();
    } else {
      System.out.println("Opção inválida!");
    }
  }
}
