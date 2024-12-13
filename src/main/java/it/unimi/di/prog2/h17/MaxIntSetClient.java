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

package it.unimi.di.prog2.h17;

import java.util.Scanner;

/** Test class for {@link MaxIntSet}. */
public class MaxIntSetClient {

  /** . */
  private MaxIntSetClient() {}

  /**
   * Reads a series of instructions and prints the resulting set from their execution.
   *
   * <p>After instantiating an empty set, it reads a series of integers from the input stream. If
   * they are positive, it adds them to the set; if they are negative, it removes the corresponding
   * absolute value. If the integer is 0, it prints the set.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    MaxIntSet M = new MaxIntSet();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) {
        int x = s.nextInt();
        if (x > 0) M.insert(x);
        else if (x < 0) M.remove(-x);
        else System.out.println(M);
      }
    }
  }
}
