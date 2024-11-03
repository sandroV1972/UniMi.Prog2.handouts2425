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

package it.unimi.di.prog2.h09;

import java.util.Scanner;

/** A class to test some methods of {@link Poly}. */
public class PolyClient {

  /** . */
  private PolyClient() {}

  /**
   * Tests the {@link Poly#mul(Poly)} method.
   *
   * <p>Starting from term \( t_0 = 1 \) reads a list \( t_i \) of terms from the standard input,
   * given as a "coefficient degree" pairs, and computes the polynomial \( t_0 \cdot t_1 \cdot
   * \cdots \), emitting in the standard output the pairs "coefficient degree" for every term in the
   * result (in increasing order of degree).
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    Poly result = new Poly(1, 0);
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) {
        Poly term = Poly.monomialWithCoeffAndDegree(s.nextInt(), s.nextInt());
        result = result.mul(term);
      }
      for (int d = 0; d <= result.degree(); d++) System.out.println(result.coeff(d) + " " + d);
    }
  }
}
