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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class to collect a sequence of strings and format them.
 *
 * <p>This class collects strings and {@code null}s and can return the sequence both via {@link
 * #toString()} that concatenates non-null strings with a space, inserting a newline for each {@code
 * null}, and via an iterator that returns just the non-null strings.
 */
public class StringFormatter implements Iterable<String> {

  /** The list of added strings. */
  private final List<String> string;

  /*-
   * AF:
   *   AF(string) =
   *    the non-null elements of string, where null indicates
   *    where the new-lines shoud be introduced in the concatenation
   *
   * RI:
   *  - string is not null (but may contain nulls).
   */

  /** Creates an empty formatter. */
  public StringFormatter() {
    string = new ArrayList<>();
  }

  /**
   * Adds a string, or null, to the formatter.
   *
   * @param s the string to add, or null.
   */
  public void add(String s) {
    string.add(s);
  }

  /**
   * Returns a string version of this formatter, non-null strings will be concatenated with a space,
   * and a newline will be inserted for each null.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Iterator<String> it = string.iterator();
    while (it.hasNext()) {
      String s = it.next();
      sb.append(s == null ? "\n" : s + " ");
    }
    return sb.toString();
  }

  /** Returns an iterator over the non-null strings in this formatter. */
  @Override
  public Iterator<String> iterator() {
    return new Iterator<>() {

      /** The next string to be returned, or null. */
      private String next = null;

      /** The iterator over the strings and nulls in the formatter. */
      private Iterator<String> it = string.iterator();

      /*-
       * AF(next, null) =
       *  if next is not null, next contains the next string to be returned,
       *  otherwise if it is null, if !it.hasNext() then the iteration is over,
       *  otherwise the candidate next string is the next non-null string in it.
       *
       * * RI:
       *   - it is not null.
       */

      @Override
      public boolean hasNext() {
        if (next != null) return true;
        while (it.hasNext()) {
          next = it.next();
          if (next != null) return true;
        }
        return false;
      }

      @Override
      public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        String ret = next;
        next = null;
        return ret;
      }
    };
  }
}
