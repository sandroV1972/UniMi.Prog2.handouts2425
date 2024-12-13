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

package it.unimi.di.prog2.s10;

import java.util.Scanner;

/** A class to exercise a {@link RationalNumber}. */
public class RationalNumberClient {

  /** . */
  private RationalNumberClient() {}

  /**
   * Tests some methods of {@link BoundedIntQueue}.
   *
   * <p>Reads a list of pairs of integers from the standard input, corresponding to the numerator
   * and the denominator of a sequence of rational numbers \( q_i \). Computes the sequence of
   * rational numbers given by \( r_0 = 0 \) and \( r_{i+1} = q_i + r_i \cdot q_i \). Then compares
   * the last computed rational number with the rational number given by the pair of integers given
   * as command line arguments emitting <samp>true</samp> in the standard output if they are equal,
   * <samp>false</samp> otherwise.
   *
   * @param args the numerator and denominator of the resulting fraction.
   */
  public static void main(String[] args) {
    RationalNumber expected =
        new RationalNumber(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    RationalNumber result = new RationalNumber(0);
    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNextInt()) {
        RationalNumber q = new RationalNumber(sc.nextInt(), sc.nextInt());
        result = q.add(result.mul(q));
      }
    }
    System.out.println(result.equals(expected));
  }
}
