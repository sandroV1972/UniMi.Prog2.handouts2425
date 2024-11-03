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

package it.unimi.di.prog2.e09;

import it.unimi.di.prog2.h08.impl.NegativeExponentException;

/**
 * {@code Poly}s are immutable polynomials with integer coefficients.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class Poly { // we don't extend Cloneable, see EJ 3.13

  // Fields

  /** The array of coefficients, the {@code terms[i]} is the coefficient of \( x^i \). */
  private final int[] terms;

  /** The degree of the polynomial. */
  private final int deg;

  // Constructors

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public Poly() {
    terms = new int[1];
    deg = 0;
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
    if (c == 0) deg = 0;
    else deg = n;
    terms = new int[deg + 1];
    terms[deg] = c;
  }

  /**
   * Initializes a polynomial of given degree (with all coefficients equal to 0).
   *
   * @param n the degree, must be non negative.
   */
  private Poly(int n) {
    deg = n;
    terms = new int[deg + 1];
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
    return deg;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    if (d < 0 || d > deg) return 0;
    else return terms[d];
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
    if (q == null) throw new NullPointerException();
    if ((q.deg == 0 && q.terms[0] == 0)) 
      return clone(this);
    if (deg == 0 && terms[0] == 0) 
      return clone(q);
    
    Poly r = new Poly(Math.max(deg, q.deg));
    if (deg >= q.deg) {
      for (int i = 0; i <= deg; i++) {
        r.terms[i] = terms[i] + (i>q.terms.length-1?0:q.terms[i]);
      }
    } else {
      for (int i = 0; i <= deg; i++) {
        r.terms[i] = terms[i] + q.terms[i];
      }
      for (int i = deg + 1; i <= q.deg; i++) {
        r.terms[i] = q.terms[i];
      }
    }
    return r;
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
    if (q == null) throw new NullPointerException();
    if ((q.deg == 0 && q.terms[0] == 0) || (deg == 0 && terms[0] == 0)) return new Poly();
    Poly r = new Poly(deg + q.deg);
    for (int i = 0; i <= deg; i++)
      for (int j = 0; j <= q.deg; j++) r.terms[i + j] = r.terms[i + j] + terms[i] * q.terms[j];
    return r;
  }

  /**
   * Clona un oggetto Poly.
   *
   * @param p il polinomio da clonare.
   * @return una copia del polinomio dato.
   */
  public static Poly clone(Poly p) {
    if (p == null) {
      throw new NullPointerException("Il polinomio da clonare non può essere null.");
    }
    Poly clone = new Poly(p.deg);
    for (int i = 0; i <= p.deg; i++) {
      clone.terms[i] = p.terms[i];
    }
    return clone;
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
    if (q == null) throw new NullPointerException();
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
    Poly r = new Poly(deg);
    for (int i = 0; i <= deg; i++) r.terms[i] = -terms[i];
    return r;
  }
}
