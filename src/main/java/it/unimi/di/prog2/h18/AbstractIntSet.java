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

import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * An {@code AbstractIntSet} is a mutable, unbounded set of integers.
 *
 * <p>A typical {@code AbstractIntSet} is \( S = \{x_1, \ldots, x_n \} \).
 */
public abstract class AbstractIntSet implements Iterable<Integer> {

  /** The elements of the set. */
  protected int size;

  /*-
   * AF(size) -> a set with size elements.
   * RI: size >= 0
   */

  /** Creates an empty set. */
  protected AbstractIntSet() {
    size = 0;
  }

  /**
   * Adds the given element to this set.
   *
   * <p>This method modifies the object, that is: \( S' = S \cup \{ x \} \).
   *
   * @param x the element to be added.
   */
  public abstract void insert(int x);

  /**
   * Removes the given element from this set.
   *
   * <p>This method modifies the object, that is: \( S' = S \setminus \{ x \} \).
   *
   * @param x the element to be removed.
   */
  public abstract void remove(int x);

  /**
   * Tells if the given element is in this set.
   *
   * <p>Answers the question \( x\in S \).
   *
   * @param x the element to look for.
   * @return whether the given element belongs to this set, or not.
   */
  public boolean isIn(int x) {
    for (int e : this) if (e == x) return true;
    return false;
  }

  /**
   * Returns the cardinality of this set.
   *
   * <p>Responds with \( |S| \).
   *
   * @return the size of this set.
   */
  public int size() {
    return size;
  }

  /**
   * Returns an element from this set.
   *
   * @return an arbitrary element from this set.
   * @throws NoSuchElementException if this set is empty.
   */
  public int choose() throws NoSuchElementException {
    if (size == 0) throw new NoSuchElementException("Can't choose from an empty set");
    return iterator().next();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof AbstractIntSet other)) return false;
    if (size != other.size) return false;
    for (int e : this) if (!other.isIn(e)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    int result = 0;
    for (int e : this) result += e; // This is a very bad hash function!
    return result;
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(", ", "{", "}");
    for (Integer e : this) sj.add(e.toString());
    return sj.toString();
  }
}
