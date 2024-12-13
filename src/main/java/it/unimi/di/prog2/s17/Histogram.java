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

package it.unimi.di.prog2.s17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class to handle a list of rectangles and organize them as in histogram.
 *
 * <p>The rectangles are organized in decreasing height order, more specifically the iterator
 * returns the rectangles in this order.
 */
public class Histogram implements Iterable<Rectangle> {

  /** The list of rectangles in the histogram. */
  private final List<Rectangle> rectangles;

  /*-
    Decide what fields to use to represent a rectangle and
    provide the AF and IR.

    Check the specification, possibly adding missing exceptions.

    Finish the implementation of the class.
  */

  /** Creates an empty histogram. */
  public Histogram() {
    rectangles = new ArrayList<>();
  }

  /**
   * Adds a {@link Rectangle} to this histogram.
   *
   * @param rectangle the rectangle to add.
   */
  public void add(Rectangle rectangle) {
    int i;
    for (i = 0; i < rectangles.size(); i++)
      if (rectangle.height() > rectangles.get(i).height()) break;
    rectangles.add(i, rectangle);
  }

  /**
   * Changes the base of the given rectangle
   *
   * @param rectangle the rectangle.
   * @param newBase the new base.
   * @throws NoSuchElementException if the rectangle is not in the histogram.
   * @throws IllegalArgumentException if newBase is negative.
   */
  public void changeBase(Rectangle rectangle, int newBase) {
    if (newBase < 0) throw new IllegalArgumentException("Base must be non-negative");
    final int index = rectangles.indexOf(rectangle);
    if (index < 0) throw new NoSuchElementException("Rectangle not found");
    rectangles.get(index).base(newBase);
  }

  /**
   * Returns an iterator that produces the rectangles in this histogram in decreasing height order.
   *
   * @return the iterator over the rectangles in this histogram.
   */
  @Override
  public Iterator<Rectangle> iterator() {
    return Collections.unmodifiableList(rectangles).iterator();
  }
}
