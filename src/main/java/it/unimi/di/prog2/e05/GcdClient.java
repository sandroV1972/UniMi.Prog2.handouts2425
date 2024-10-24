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

import java.util.Scanner;

/** Esercizio 3.1 di PDJ. */
public class GcdClient {

  /** Costruttore privato per evitare l'istanza della classe. */
  private GcdClient() {}

  /**
   * Metodo principale che legge coppie di numeri dal flusso di ingresso e stampa il loro GCD.
   *
   * @param args Argomenti della riga di comando (non utilizzati).
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextInt()) {
      int n1 = scanner.nextInt();
      int n2 = scanner.nextInt();
      int result = gcd(n1, n2);
      System.out.println(result);
    }

    scanner.close();
  }

  /**
   * Calcola il massimo comune divisore (GCD) di due numeri interi utilizzando l'algoritmo di
   * Euclide.
   *
   * @param a Il primo numero intero.
   * @param b Il secondo numero intero.
   * @return Il massimo comune divisore di a e b.
   */
  //  REQUIRES due interi
  //  MODIFIES niente
  //  EFFECTS calcola il massimo comun divisore dei due numeri e restituisce il valore
  public static int gcd(int a, int b) {
    int temp;
    while (b != 0) {
      temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
}
