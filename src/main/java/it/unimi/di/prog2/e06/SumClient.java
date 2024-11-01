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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/** Esercizio 4.3 di PDJ. */
public class SumClient {

  /** . */
  private SumClient() {}

  // Il main di questa classe legge dal flusso di ingresso una sequenza di al
  // più 100 interi e ne emette la somma nel flusso d'uscita.

  public static void main(String[] args) {
    int[] data = getDataArray();
    int result = sum(data);
    System.out.println(result);

  }

  public static int[] getDataArray() {
    List<Integer> numbers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      Collections.addAll(numbers, parseInts(scanner.nextLine()));
    }
    scanner.close();
    return numbers.stream().mapToInt(i -> i).toArray();
  }

  public static int sum(int[] a) {
    int sum = 0;
    if (a.length == 0) {
      throw new IllegalArgumentException("Array non può essere vuota"); 
    } else {
      for (int n : a) {
        sum += n;
      }
    }
    return sum;
  } 


  /**
   * Converte una stringa di numeri separati da spazi in un array di interi.
   *
   * @param line La stringa di input contenente numeri separati da spazi.
   * @return Un array di interi.
   */
  public static Integer[] parseInts(String line) {
    //REQUIRES una stringa
    //MODIFIES non modifica dati
    //EFFECTS prende la riga e ritorna una array di interi
    String[] parts = line.split(" ");
    Integer[] numbers = new Integer[parts.length];
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
