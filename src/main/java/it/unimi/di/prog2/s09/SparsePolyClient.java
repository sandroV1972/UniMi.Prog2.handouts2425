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

package it.unimi.di.prog2.s09;

import java.util.Scanner;

/** A class to test some methods of {@link SparsePoly}. */
public class SparsePolyClient {

  /** . */
  private SparsePolyClient() {}

  /**
   * Tests some methods of {@link SparsePoly}.
   *
   * <p>Starting from term \( t_0 = x + 1 \) reads a list of \( t_i \) of terms from the standard
   * input, given as a (coefficient, degree) pairs, and computes the polynomials \( p_{i+1} = p_i
   * \codt t_i + t_i \) emitting in the standard output the pairs "coefficient degree" for every
   * term (in increasing order of degree) of the last computed polynomial.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    SparsePoly result = new SparsePoly(1, 1).add(new SparsePoly(-1, 0));
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextInt()) {
        SparsePoly term = new SparsePoly(s.nextInt(), s.nextInt());
        result = result.mul(term).add(term);
      }
      for (int d = 0; d <= result.degree(); d++) System.out.println(result.coeff(d) + " " + d);
    }
  }
}
