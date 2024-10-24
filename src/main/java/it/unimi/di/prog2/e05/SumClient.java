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

public class SumClient {

  /** Costruttore privato per evitare l'istanza della classe. */
  private SumClient() {}

  /**
   * Metodo principale che riceve un elenco di interi come parametri sulla linea di comando e ne
   * emette la somma nel flusso d'uscita.
   *
   * @param args Argomenti della riga di comando, che devono essere numeri interi.
   */
  // REQUIRES args una serie di numeri interi
  // MODIFIES niente
  // EFFECTS crea una array di int con gli argomenti  e ne fa la somma tramite il metodo sum()
  public static void main(String[] args) {
    if (args.length == 0) {
      System.exit(1);
    }
    int[] arg = new int[args.length];
    // crea una array di interi con gli argomenti
    try {
      for (int i = 0; i < args.length; i++) {
        arg[i] = Integer.parseInt(args[i]);
      }
    } catch (NumberFormatException e) {
      System.exit(1);
      ;
    }
    // Stampa la somma degli argomenti
    System.out.println(sum(arg));
  }

  /**
   * Metodo sum che riceve una array di interi e ne emette la somma nel flusso d'uscita.
   *
   * @param a array di interi
   * @return sum la somma degli interi della array a
   */
  // REQUIRES in input una array di interi
  // MODIFIES niente
  // EFFECTS restituisce la somma degli interi della array
  public static int sum(int[] a) {
    int sum = 0;
    for (int arg : a) {
      sum += arg;
    }
    // restituisce la somma degli argomenti
    return sum;
  }
}
