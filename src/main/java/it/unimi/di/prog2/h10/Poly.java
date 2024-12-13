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

package it.unimi.di.prog2.h10;

import it.unimi.di.prog2.h08.impl.NegativeExponentException;
import java.util.Arrays;
import java.util.Objects;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly { // we don't extend Cloneable, see EJ 3.13

  /** The array of coefficients, the {@code coeff[i]} is the coefficient of \( x^i \). */
  private final int[] coefficient;

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public Poly() {
    coefficient = new int[1];
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public Poly(int c, int n) throws NegativeExponentException {
    if (n < 0)
      throw new NegativeExponentException("Can't create a monomial with negative exponent");
    if (c == 0) n = 0;
    coefficient = new int[n + 1];
    coefficient[n] = c;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param n the degree, must be non-negative.
   */
  private Poly(int n) {
    coefficient = new int[n + 1];
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return coefficient.length - 1;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    if (d < 0 || d >= coefficient.length) return 0;
    else return coefficient[d];
  }

  /**
   * Performs polynomial addition.
   *
   * <p>If \( p \) is this polynomial, returns \( p + q \).
   *
   * @param q the polynomial to add to this one.
   * @return the sum among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly add(Poly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial must not be null.");
    final Poly larger, smaller;
    if (degree() > q.degree()) {
      larger = this;
      smaller = q;
    } else {
      larger = q;
      smaller = this;
    }
    int resultDegree = larger.degree();
    if (degree() == q.degree()) {
      for (int k = degree(); k > 0; k--)
        if (coefficient[k] + q.coefficient[k] != 0) break;
        else resultDegree--;
    }
    Poly result = new Poly(resultDegree); // get a new Poly
    int i;
    for (i = 0; i <= smaller.degree() && i <= resultDegree; i++)
      result.coefficient[i] = smaller.coefficient[i] + larger.coefficient[i];
    for (int j = i; j <= resultDegree; j++) result.coefficient[j] = larger.coefficient[j];
    return result;
  }

  /**
   * Performs polynomial multiplication.
   *
   * <p>If \( p \) is this polynomial, returns \( p q \).
   *
   * @param q the polynomial to multiply by this one.
   * @return the product among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly mul(Poly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial must not be null.");
    if ((q.degree() == 0 && q.coefficient[0] == 0) || (degree() == 0 && coefficient[0] == 0))
      return new Poly();
    Poly r = new Poly(degree() + q.degree());
    for (int i = 0; i <= degree(); i++)
      for (int j = 0; j <= q.degree(); j++)
        r.coefficient[i + j] = r.coefficient[i + j] + coefficient[i] * q.coefficient[j];
    return r;
  }

  /**
   * Performs polynomial subtraction.
   *
   * <p>If \( p \) is this polynomial, returns \( p - q \).
   *
   * @param q the polynomial to subtract from this one.
   * @return the subtraction among this and the given polynomial.
   * @throws NullPointerException if {@code q} is {@code null}.
   */
  public Poly sub(Poly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial must not be null.");
    return add(q.minus());
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public Poly minus() {
    Poly r = new Poly(degree());
    for (int i = 0; i <= degree(); i++) r.coefficient[i] = -coefficient[i];
    return r;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Poly)) return false;
    Poly q = (Poly) o;
    if (degree() != q.degree()) return false;
    return Arrays.equals(coefficient, q.coefficient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(degree(), coefficient);
  }

  @Override
  public String toString() {
    if (degree() > 0) {
      StringBuilder sb = new StringBuilder("Poly: ");
      int c = coefficient[degree()];
      if (c < -1) sb.append("-" + (-c));
      else if (c == -1) sb.append("-");
      else if (c > 1) sb.append(c);
      sb.append("x" + (degree() > 1 ? "^" + degree() : ""));
      for (int d = degree() - 1; d > 0; d--) {
        c = coefficient[d];
        if (c == 0) continue;
        if (c < -1) sb.append(" - " + (-c));
        else if (c == -1) sb.append(" - ");
        else if (c == 1) sb.append(" + ");
        else sb.append(" + " + c);
        sb.append("x" + (d > 1 ? "^" + d : ""));
      }
      c = coefficient[0];
      if (c > 0) sb.append(" + " + c);
      else if (c < 0) sb.append(" - " + (-c));
      return sb.toString();
    } else return "Poly: " + coefficient[0];
  }
}
