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

package it.unimi.di.prog2.e17;

import java.util.Scanner;

/** A class to test {@link Histogram}. */
public class HistogramClient {

  /** . */
  private HistogramClient() {}

  /**
   * A method to test {@link Histogram#changeBase(Rectangle, int)}.
   *
   * <p>Performs the following list of actions:
   *
   * <ul>
   *   <li>reads a list of positive integers from the standard input, for every integer creates a
   *       rectangle with base and height given by the integer and adds it to an histogram;
   *   <li>emits the rectangle heights in the histogram in the standard output;
   *   <li>doubles the base of the first added rectangle, and
   *   <li>emits again the rectangle heights in the histogram in the standard output.
   * </ul>
   *
   * <p>Given the contract of {@link Histogram}, this method should always emit twice the same
   * sequence of non-increasing integers.
   *
   * @param args not used.
   */

  /* -

    - implemented the Rectangle class,
    - uncomment this method and run the tests,
    - implement the square class,
    - replace new Rectangle(size, size) with new Square(size) in the following code,
    - run the tests again.
  */
  public static void main(String[] args) {
    Histogram histogram = new Histogram();
    Rectangle firstRectangle = null;
    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNextInt()) {
        final int size = sc.nextInt();
        final Rectangle rectangle = new Rectangle(size, size);
        if (firstRectangle == null) firstRectangle = rectangle;
       histogram.add(rectangle);
      }
    }
    for (Rectangle rectangle : histogram) System.out.println(rectangle.height());
    histogram.changeBase(firstRectangle, firstRectangle.base() * 2);
    for (Rectangle rectangle : histogram) System.out.println(rectangle.height());
  }
  //
}
