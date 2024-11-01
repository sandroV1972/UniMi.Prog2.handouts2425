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
package it.unimi.di.prog2.e03;

import java.util.Scanner;

/**
 * Vedi <a href=
 * "https://github.com/mapio/labprog/blob/master/esercizi/lettera_piu_frequente/Testo.md">testo</a>,
 */
public class LetteraPiuFrequente {

  public static void main(String[] args) {
    int[] asciiValues = new int[26];
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNext()) {
        // Crea un array per contenere i valori ASCII delle lettere minuscole
        final String parola = s.nextLine();

        for (int i = 0; i < parola.length(); i++) {
          if ((int) parola.charAt(i) != 32) {
            asciiValues[(int) parola.charAt(i) - 'a'] += 1;
          }
        }
      }
      System.out.println(findMax(asciiValues));
    }
  }

  public static int findMax(int[] values) {
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < values.length; i++) {
      if (values[i] > max) {
        max = values[i];
      }
    }
    return max;
  }
}