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

package it.unimi.di.prog2.h09;

import it.unimi.di.prog2.h08.impl.NegativeExponentException;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly { // we don't extend Cloneable, see EJ 3.13

  // Fields

  /** The array of coefficients, the {@code coefficient[i]} is the coefficient of \( x^i \). */
  private final int[] coefficient;

  /** The degree of the polynomial. */
  private final int degree;

  // Constructors

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public Poly() {
    coefficient = new int[1];
    degree = 0;
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
    if (c == 0) degree = 0;
    else degree = n;
    coefficient = new int[degree + 1];
    coefficient[degree] = c;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param degree the degree, must be non negative.
   */
  private Poly(int degree) {
    this.degree = degree;
    coefficient = new int[degree + 1];
  }

  // Methods

  /**
   * A factory method returning a monomial. (see EJ 2.1)
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   * @return the monomial, if {@code n} &gt;= 0.
   */
  public static Poly monomialWithCoeffAndDegree(int c, int n) {
    return new Poly(c, n);
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return degree;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param degree the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int degree) {
    if (degree < 0 || degree > this.degree) return 0;
    else return coefficient[degree];
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
    throw new UnsupportedOperationException("Not yet implemented");
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
    if (q == null) throw new NullPointerException("The polynomial must not be null.");
    if ((q.degree == 0 && q.coefficient[0] == 0) || (degree == 0 && coefficient[0] == 0))
      return new Poly();
    Poly result = new Poly(degree + q.degree);
    for (int i = 0; i <= degree; i++)
      for (int j = 0; j <= q.degree; j++)
        result.coefficient[i + j] = result.coefficient[i + j] + coefficient[i] * q.coefficient[j];
    return result;
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
    if (q == null) throw new NullPointerException("The polynomial must not be null.");
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
    Poly r = new Poly(degree);
    for (int i = 0; i <= degree; i++) r.coefficient[i] = -coefficient[i];
    return r;
  }
}
