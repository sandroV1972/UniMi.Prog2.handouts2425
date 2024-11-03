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

/** Esercizio 4.2 di PDJ. */
public class SearchClient {

  /** . */
  private SearchClient() {}

  public int search(int[] a, int x) 
    throws NullPointerException, NotFoundException
    //REQUIRES is ordered
    //EFFECTS if a is null throws NullPointerException else if x is not in a throws NotFoundException
    // else returns i such that a[i] == x
    {
      if (a == null) {
        throw new NullPointerException ("Array nulla");
      } 
      int left = 0;
      int right = a.length - 1;
  
      while (left <= right) {
        int mid = left + (right - left) / 2;
  
        if (a[mid] == x) {
          return mid;
        } else if (a[mid] < x) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
  
      throw new NotFoundException("not found");
  }
    /**
   * Il main di questa classe legge dal flusso di ingresso una sequenza di
   * interi (separati da spazi) e, assumendo che sia ordinata in ordine
   * crescente, emette nel flusso d'uscita la posizione dell'intero specificato
   * sulla linea di comando (se presente nell'input), o -1 viceversa.
   */
  public static void main(String[] args) {
    SearchClient client = new SearchClient();
    Scanner scanner = new Scanner(System.in);
    //System.out.println("Inserisci una sequenza di interi ordinati (separati da spazi):");
    int[] data = parseInts(scanner.nextLine());
    //System.out.println("Inserisci il valore da cercare:");
    int value = Integer.parseInt(args[0]);
    scanner.close();
    int result;
    try {
      result = client.search(data, value);
    } catch (NotFoundException e) {
      result = -1;
    }

    System.out.println(result);
  
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
