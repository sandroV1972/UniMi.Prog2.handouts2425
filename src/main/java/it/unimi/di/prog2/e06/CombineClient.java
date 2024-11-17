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

package it.unimi.di.prog2.e06;

import java.util.Scanner;

/**
 * La classe CombineClient fornisce un metodo per combinare due array di interi
 * e una funzione principale per leggere gli input e stampare il risultato.
 */
public class CombineClient {

  /**
   * Metodo principale che legge due linee di input, le converte in array di interi,
   * combina gli array e stampa il risultato.
   *
   * @param args Argomenti della riga di comando (non utilizzati).
   */
  public static void main(String[] args) {
    //REQUIRES nessun argomento richede due linee sysytem.in
    //MODIFIES non modifica dati
    //EFFECTS prende le righe in e le divide in array di int
    //        ritorna la combinazione riga per riga
    Scanner scanner = new Scanner(System.in);
    try {
      int[] n1 = parseInts(scanner.nextLine());
      int[] n2 = parseInts(scanner.nextLine());
      int[] c = combine(n1, n2);
      for (int num : c) {
        System.out.println(num);
      }
    } catch (IllegalArgumentException e) {
      System.out.println("Errore: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }

  /**
   * Combina due array di interi sommando gli elementi del secondo array.
   *
   * @param a Il primo array di interi.
   * @param b Il secondo array di interi.
   * @return Un nuovo array di interi risultante dalla combinazione.
   * @throws IllegalArgumentException se uno degli array è null o vuoto.
   */
  public static int[] combine(int[] a, int[] b) {
    //REQUIRES due array di interi
    //MODIFIES non modifica dati
    //EFFECTS prende le array e ritorna la combinazione riga per riga
    if (a == null || b == null) {
      throw new IllegalArgumentException("Gli array non possono essere null.");
    }
    if (a.length == 0 || b.length == 0) {
      throw new IllegalArgumentException("Gli array non possono essere vuoti.");
    }

    int sum = 0;
    for (int n : b) {
      sum += n;
    }
    int i = 0;
    int[] c = new int[a.length];
    for (int n : a) {
      c[i] = n*sum;
      i++;
    }
    return c;
  }

  /**
   * Converte una stringa di numeri separati da spazi in un array di interi.
   *
   * @param line La stringa di input contenente numeri separati da spazi.
   * @return Un array di interi.
   */
  public static int[] parseInts(String line) {
    //REQUIRES una stringa
    //MODIFIES non modifica dati
    //EFFECTS prende la riga e ritorna una array di interi
    String[] parts = line.split(" ");
    int[] numbers = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
      try {
        numbers[i] = Integer.parseInt(parts[i]);
      } catch (NumberFormatException e) {
        System.out.println("Solo interi in input " + e.getMessage());
      }
    }
    return numbers;
  }
}
