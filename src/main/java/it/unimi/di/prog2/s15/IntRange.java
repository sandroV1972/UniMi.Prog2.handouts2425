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
import java.util.NoSuchElementException;

/**
 * A mutable class representing a range of integers, with configurable initial from, to, and step
 * values.
 *
 * <p>The class is iterable; when the iterator is created, if the step is positive, the initial
 * value must be less than the final value, and conversely if the step is negative; once created,
 * the iterator is not affected by successive modification in the range.
 */
public class IntRange implements Iterable<Integer> {

  /** The first number of the iteration. */
  private int from;

  /** The last number of the iteration. */
  private int to;

  /** The iteration step. */
  private int step;

  /**
   * Constructs the range from {@link Integer#MIN_VALUE} to {@link Integer#MAX_VALUE} (exclusive)
   * with step 1.
   */
  public IntRange() {
    this.from = Integer.MIN_VALUE;
    this.to = Integer.MAX_VALUE;
    this.step = 1;
  }

  /**
   * Sets the initial value.
   *
   * @param from the initial value.
   */
  public void from(int from) {
    this.from = from;
  }

  /**
   * Sets the end value.
   *
   * @param to the end value.
   */
  public void to(int to) {
    this.to = to;
  }

  /**
   * Sets the step.
   *
   * @param step the step value.
   * @throws IllegalArgumentException if the step is 0.
   */
  public void step(int step) {
    if (step == 0) throw new IllegalArgumentException();
    this.step = step;
  }

  @Override
  public String toString() {
    return "IntRange{" + "from=" + from + ", to=" + to + ", step=" + step + '}';
  }

  /**
   * Returns an iterator for this range.
   *
   * <p>If the range is modified after the iterator is returned, the modifications will not be
   * reflected in the iterator.
   *
   * @return the iterator.
   * @throws IllegalArgumentException if the initial value is less than the final, but the step is
   *     negative, or conversely if the initial value is greater than the final, but the step is
   *     positive.
   */
  @Override
  public Iterator<Integer> iterator() {
    if (step > 0 && from > to || step < 0 && from < to) throw new IllegalArgumentException();
    return new Iterator<Integer>() {

      /** The next candidate. */
      private int next = from;

      /** The unmodifiable copy of the to. */
      private final int to = IntRange.this.to;

      /** The unmodifiable copy of the step. */
      private final int step = IntRange.this.step;

      @Override
      public boolean hasNext() {
        return step > 0 ? next < to : next > to;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        final int result = next;
        if (step > 0 && next >= Integer.MAX_VALUE - step) next = Integer.MAX_VALUE;
        else if (step < 0 && next <= Integer.MIN_VALUE - step) next = Integer.MIN_VALUE;
        else next += step;
        return result;
      }
    };
  }
}
