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

import it.unimi.di.prog2.h08.impl.EmptyException;
import it.unimi.di.prog2.h14.IntSet;

/**
 * Example of {@code MaxIntSet} taken from section 7.4 of the textbook by Liskov <em>et al.</em>.
 *
 * <p><b>Note</b>: this class extends {@link it.unimi.di.prog2.h14.IntSet} which corresponds to the
 * implementation of {@link it.unimi.di.prog2.h11.IntSet} with an added iterator.
 */
public class MaxIntSet extends IntSet {

  /** The biggest element, if set is not empty */
  private int biggest;

  // RI: size() == 0 or isIn(biggest) and for every x isIn(x) implies biggest >= x.
  // AF: coincides with that of IntSet

  /** Construct an empty {@code MaxIntSet}. */
  public MaxIntSet() {
    super();
  }

  @Override
  public void insert(final int x) {
    if (size() == 0 || x > biggest) biggest = x;
    super.insert(x);
  }

  @Override
  public void remove(final int x) {
    super.remove(x);
    if (size() == 0 || x != biggest)
      return; // observe that if x > biggest it was not actually in this, so we don't need to
    // update biggest
    biggest = Integer.MIN_VALUE;
    for (int z : this) if (z > biggest) biggest = z;
  }

  /**
   * Returns the maximum value in the set, or raises {@link EmptyException} otherwise.
   *
   * @return the maximum value in the set.
   * @throws EmptyException if the set is empty.
   */
  public int max() throws EmptyException {
    if (size() == 0) throw new EmptyException();
    return biggest;
  }

  /**
   * Checks the Representation Invariant.
   *
   * @return true if and only if the Representation Invariant holds.
   */
  public boolean repOk() {
    // no need to check super.repOk() because there is no state sharing
    if (size() == 0) return true;
    boolean found = false;
    for (int z : this) {
      if (z > biggest) return false;
      if (z == biggest) found = true;
    }
    return found;
  }

  @Override
  public String toString() {
    if (size() == 0) return "Max" + super.toString();
    else return "Max" + super.toString() + (size() > 0 ? ", max = " + biggest : "");
  }
}
