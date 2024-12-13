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

package it.unimi.di.prog2.s10;

import java.util.Objects;

/**
 * A rational number is an immutable number that can be expressed as the quotient or fraction \( p/q
 * \) of two {@code int}s, a numerator \( p \) and a non-zero denominator \( q \).
 */
public class RationalNumber {

  /** The numerator. */
  public final int numerator;

  /** The denominator. */
  public final int denominator;

  /*-
   * AF:
   *
   *   AF(numerator, denominator) = numerator/denominator
   *
   * RI:
   *
   *  - denominator > 0
   *  - gcd(numerator, denominator) == 1
   *
   */

  /**
   * Computes the greatest common divisor of two numbers.
   *
   * @param a the first number.
   * @param b the second number.
   * @return the greatest common divisor of {@code a} and {@code b}.
   */
  private long gcd(long a, long b) {
    while (b > 0) {
      long r = a % b;
      a = b;
      b = r;
    }
    return a;
  }

  /**
   * Creates a new rational number.
   *
   * <p>The rational number is reduced to minimum terms, so the arguments of this methods are
   * allowed to be {@code long}s and the fraction will be created if and only if the numerator and
   * the denominator, once reduced to minimum terms, are not too large to be represented as {@code
   * int}s.
   *
   * @param numerator the numerator.
   * @param denominator the denominator.
   * @throws IllegalArgumentException if {@code denominator} is zero.
   * @throws IllegalArgumentException if the numerator or the denominator reduced to minimum terms
   *     are too large to be represented as {@code int}s.
   */
  public RationalNumber(long numerator, long denominator) {
    if (denominator == 0) throw new IllegalArgumentException("denominator cannot be zero");
    if (denominator < 0) {
      numerator = -numerator;
      denominator = -denominator;
    }
    long gcd =
        gcd(numerator > 0 ? numerator : -numerator, denominator > 0 ? denominator : -denominator);
    long reducedNumerator = numerator / gcd;
    long reducedDenominator = denominator / gcd;
    if (reducedNumerator < Integer.MIN_VALUE || reducedNumerator > Integer.MAX_VALUE)
      throw new IllegalArgumentException(
          "numerator (reduced to minimum terms) " + reducedNumerator + " does not fit into an int");
    if (reducedDenominator > Integer.MAX_VALUE)
      throw new IllegalArgumentException(
          "denominator (reduced to minimum terms) "
              + reducedDenominator
              + " does not fit into an int");
    this.numerator = (int) reducedNumerator;
    this.denominator = (int) reducedDenominator;
  }

  /**
   * Creates a new integer number.
   *
   * @param value the value.
   */
  public RationalNumber(int value) {
    this(value, 1);
  }

  /**
   * Returns the sum of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the sum of this rational number and {@code other}.
   */
  public RationalNumber add(RationalNumber other) {
    return new RationalNumber(
        (long) denominator * other.numerator + (long) other.denominator * numerator,
        (long) denominator * other.denominator);
  }

  /**
   * Returns the product of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the product of this rational number and {@code other}.
   */
  public RationalNumber mul(RationalNumber other) {
    return new RationalNumber(
        (long) numerator * other.numerator, (long) denominator * other.denominator);
  }

  /**
   * Tells whether this rational number is an integer.
   *
   * @return {@code true} if this rational number is an integer, {@code false} otherwise.
   */
  public boolean isInteger() {
    return denominator == 1;
  }

  /**
   * Tells whether this rational number is positive.
   *
   * @return {@code true} if this rational number is positive, {@code false} otherwise.
   */
  public boolean isPositive() {
    return numerator > 0;
  }

  /**
   * Tells whether this rational number is equal to zero.
   *
   * @return {@code true} if this rational number is zero, {@code false} otherwise.
   */
  public boolean isZero() {
    return numerator == 0;
  }

  @Override
  public String toString() {
    if (denominator == 1) return Integer.toString(numerator);
    return numerator + "/" + denominator;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof RationalNumber other)) return false;
    return numerator == other.numerator && denominator == other.denominator;
    // Why the following is not correct?
    // return (double) numerator / denominator == (double) other.numerator / other.denominator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }
}
