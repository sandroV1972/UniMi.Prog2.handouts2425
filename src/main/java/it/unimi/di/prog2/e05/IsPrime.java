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

public class IsPrime {

  /** Costruttore privato per evitare l'istanza della classe. */
  private IsPrime() {}

  /**
   * Verifica se un numero intero è primo.
   *
   * @param n Il numero intero da verificare.
   * @return true se il numero è primo, false altrimenti.
   */
  // REQUIRES un numero intero
  // MODIFIES niente
  // EFFECTS calcola se il numero fornito è un numero primo
  public static boolean isPrime(int n) {
    if (n <= 1) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
