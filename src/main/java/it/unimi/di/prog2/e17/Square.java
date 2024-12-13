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

/** A mutable class representing a square with integer valued base. */
public class Square extends Rectangle {

  /*-
    RI: base > 0
    AF: base == height
  */

  /**
   * Creates a square of given base.
   *
   * @param base the base of the square.
   */
  public Square(final int base) {
    super(base, base);
  }

  /**
   * Changes the base of a square.
   *
   * @param base the new base of the square.
   */
  public void base(int base) {
    super.base(base);
    super.height(base);
  }

  /**
   * Changes the height of a square.
   *
   * @param height the new height of the square.
   */
  public void height(int height) {
    super.height(height);
    super.base(height);
  }
  /**
   * Overrides the toString method.
   *
   * @return the string representation of the square.
   */
  @Override
  public String toString() {
    return String.format("Square[base=%d]", base());
  }
}
