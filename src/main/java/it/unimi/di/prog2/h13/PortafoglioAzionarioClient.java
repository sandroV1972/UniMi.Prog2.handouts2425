/*

Copyright 2024 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.h13;

import java.util.Scanner;

/** A class to test some methods of {@link PortafoglioAzionario}. */
public class PortafoglioAzionarioClient {

  /** . */
  private PortafoglioAzionarioClient() {}

  /**
   * A method testing some methods of {@link PortafoglioAzionario}.
   *
   * <p>This method reads a sequence of commands from the standard input and executes them on a
   * {@link PortafoglioAzionario} object. The commands are:
   *
   * <ul>
   *   <li>{@code + società prezzo quantità} to add a new action to the portfolio;
   *   <li>{@code - società quantità} to remove the given number of actions of a given company;
   *   <li>{@code ?} to print the total value of the portfolio.
   * </ul>
   *
   * and executes them, emitting the result of the {@code ?} command in the standard output.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    final PortafoglioAzionario portafoglioAzionario = new PortafoglioAzionario();
    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNext()) {
        final String command = sc.next();
        switch (command) {
          case "+":
            portafoglioAzionario.aggiungi(
                new PortafoglioAzionario.Azione(sc.next(), sc.nextInt()), sc.nextInt());
            break;
          case "-":
            portafoglioAzionario.rimuovi(sc.next(), sc.nextInt());
            break;
          case "?":
            System.out.println(portafoglioAzionario.valore());
            break;
          default:
            throw new IllegalArgumentException("Comando non riconosciuto: " + command);
        }
      }
    }
  }
}
