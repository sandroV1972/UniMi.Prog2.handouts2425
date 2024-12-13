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

/** Utility class with some string iterators. */
public class StringIterators {

  /** . */
  private StringIterators() {}

  /**
   * Filters even-length strings.
   *
   * @param it an iterator of strings.
   * @return an iterator that returns the strings of even length of {@code it}.
   */
  public static Iterator<String> evenIterator(final Iterator<String> it) {
    return new Iterator<>() {
      private String next = null;

      @Override
      public boolean hasNext() {
        if (next != null) return true;
        while (it.hasNext()) {
          next = it.next();
          if (next.length() % 2 == 0) return true;
        }
        return false;
      }

      @Override
      public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        String result = next;
        next = null;
        return result;
      }
    };
  }

  /**
   * Converts strings to uppercase.
   *
   * @param it an iterator of strings.
   * @return an iterator that returns the strings of {@code it} in uppercase.
   */
  public static Iterator<String> uppercase(final Iterator<String> it) {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        return it.hasNext();
      }

      @Override
      public String next() {
        return it.next().toUpperCase();
      }
    };
  }
}
