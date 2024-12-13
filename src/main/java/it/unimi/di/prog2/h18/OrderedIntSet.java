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

package it.unimi.di.prog2.h18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A concrete sorted {@code IntSet}.
 *
 * <p>The iterator of this set returns the elements in ascending order.
 */
public class OrderedIntSet extends AbstractIntSet {

  /** The set elements. */
  private final List<Integer> elements;

  /*-
   * AF(elements, size) = { elements.get(0), elements.get(1), ..., elements.get(size - 1) }
   * RI:
   *   - super.RI
   *   - elements != null and does not contain nulls,
   *   - elements is sorted in ascending order.
   */

  /** Creates an empty set. */
  public OrderedIntSet() {
    this.elements = new ArrayList<>();
  }

  /**
   * Returns the maximum element of this set.
   *
   * @return the maximum element of this set.
   * @throws NoSuchElementException if this set is empty.
   */
  public int max() throws NoSuchElementException {
    if (size == 0) throw new NoSuchElementException("An empty set has no maximum element.");
    return elements.getLast();
  }

  /**
   * Returns the minimum element of this set.
   *
   * @return the maximum element of this set.
   * @throws NoSuchElementException if this set is empty.
   */
  public int min() throws NoSuchElementException {
    if (size == 0) throw new NoSuchElementException("An empty set has no minimum element.");
    return elements.get(0);
  }

  @Override
  public Iterator<Integer> iterator() {
    return Collections.unmodifiableList(elements).iterator();
  }

  @Override
  public void insert(int x) {
    final int index = Collections.binarySearch(elements, x);
    if (index < 0) {
      elements.add(-index - 1, x);
      size++;
    }
  }

  @Override
  public void remove(int x) {
    if (elements.remove(Integer.valueOf(x))) size--;
  }
}
