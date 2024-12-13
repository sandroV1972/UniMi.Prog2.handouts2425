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

package it.unimi.di.prog2.s15;

import java.util.Iterator;
import java.util.Scanner;

/** A class to test {@link IntRange}s. */
public class IntRangeClient {

  /** . */
  private IntRangeClient() {}

  /**
   * A method to test the {@link IntRange} class.
   *
   * <p>This methods reads the parameters of an {@link IntRange} from the lines in the standard
   * input in the form {@code command value}, where the command is:
   *
   * <ul>
   *   <li>{@code F} to set the from value of the range.
   *   <li>{@code T} to set the to value of the range.
   *   <li>{@code S} to set the step of the range.
   * </ul>
   *
   * commands can be repeated, the last value is the one that is considered. Once the input is
   * exhausted, the method emits the integers in the range in the standard output.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    IntRange range = new IntRange();
    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNext()) {
        char command = sc.next().charAt(0);
        int value = sc.nextInt();
        switch (command) {
          case 'F':
            range.from(value);
            break;
          case 'T':
            range.to(value);
            break;
          case 'S':
            range.step(value);
            break;
          default:
            throw new IllegalArgumentException("Unknown command: " + command);
        }
      }
    }
    Iterator<Integer> it = range.iterator();
    int iterations = 0, first = 0, last = 0;
    if (it.hasNext()) {
      first = it.next();
      iterations++;
      while (it.hasNext()) {
        last = it.next();
        iterations++;
      }
    }
    System.out.println(
        iterations + (iterations > 0 ? " " + first : "") + (iterations > 1 ? " " + last : ""));
  }
}
