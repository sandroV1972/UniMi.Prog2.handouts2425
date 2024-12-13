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

package it.unimi.di.prog2.h15;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing decimal digits of a long, endowed with a non zero digits iterator, based on
 * an <strong>anonymous</strong> class.
 */
public class DecimalDigitsAG {

  /** The number whose digits are represented by this class. */
  private final long number;

  /**
   * Creates a new instance representing the digits of the given number.
   *
   * @param number the number.
   */
  public DecimalDigitsAG(final long number) {
    this.number = number;
  }

  /**
   * Returns a <em>generator</em> on from the least significant to the most significant non zero
   * digits.
   *
   * @return the generator.
   */
  public Iterator<Integer> nonZeroDigits() {
    // no need to pass any value, nor to define any named class
    return new Iterator<Integer>() {

      /** The remaining digits to return (except possibly for the trailing zeroes). */
      private long remaining = number;

      @Override
      public boolean hasNext() {
        while (remaining != 0 && remaining % 10 == 0) remaining /= 10;
        return remaining != 0;
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        int digit = (int) (remaining % 10);
        remaining /= 10;
        return digit;
      }
    };
  }
}
