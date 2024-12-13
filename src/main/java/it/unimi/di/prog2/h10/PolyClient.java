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

package it.unimi.di.prog2.h10;

import java.util.Scanner;

/** A class to test some methods of {@link Poly}. */
public class PolyClient {

  /** . */
  private PolyClient() {}

  /**
   * Tests some methods of {@link Poly}.
   *
   * <p>Starting from term \( t_0 = 0 \) reads a list of \( t_i \) of terms from the standard input,
   * given as a (coefficient, degree) pairs, and computes the polynomial \( t_0 + t_1 + t_2 \cdots
   * \), emitting the resulting polynomial in the standard output.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    Poly result = new Poly();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) {
        Poly term = new Poly(s.nextInt(), s.nextInt());
        result = result.add(term);
      }
    }
    System.out.println(result);
  }
}
