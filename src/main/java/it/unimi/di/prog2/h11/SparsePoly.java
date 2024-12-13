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

package it.unimi.di.prog2.h11;

import it.unimi.di.prog2.h08.impl.NegativeExponentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * {@code SparsePoly}s are immutable polynomials with integer coefficients such that the number of
 * nonzero coefficient is small with respect to the degree.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class SparsePoly {

  /**
   * A record holding a non-zero term of the polynomial.
   *
   * @param coefficient the coefficient.
   * @param degree the degree.
   */
  public record Term(int coefficient, int degree) {

    /*-
     * AF:
     *
     *  AF(coefficient, degree) = coefficient * x^degree
     *
     * RI:
     *
     *   - degree >= 0
     *   - coefficient != 0
     *
     */

    /**
     * Builds a term.
     *
     * @param coefficient the coefficient.
     * @param degree the degree.
     * @throws IllegalArgumentException if {@code n} &lt; 0 or {@code c} is 0.
     */
    public Term { // using the compact constructor, cannot declare throws
      if (degree < 0) throw new IllegalArgumentException("A term cannot have a negative exponent.");
      if (coefficient == 0)
        throw new IllegalArgumentException("A term cannot have a zero coefficient.");
    }
  }

  /** The array of terms (in increasing degree). */
  private final List<Term> terms;

  /*
   * AF:
   *
   *  AF(terms) = \sum terms.get(i).coefficient x^terms.get(i).degree
   *
   * RI:
   *
   *  - terms != null and does not contain null
   *  - terms is in strictly increasing degree order
   */

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public SparsePoly() {
    terms = Collections.emptyList();
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public SparsePoly(final int c, final int n) throws IllegalArgumentException {
    terms = c == 0 ? Collections.emptyList() : List.of(new Term(c, n));
  }

  /**
   * Initializes this to be the polynomial from a list of terms in increasing degree order.
   *
   * @param lst the not {@code null} list, not containing {@code null}s and in increasing degree
   *     order.
   */
  private SparsePoly(final List<Term> lst) {
    terms = Collections.unmodifiableList(lst);
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(final int d) {
    if (d > degree()) return 0;
    final int i = findByDegree(terms, d);
    return i >= 0 ? terms.get(i).coefficient : 0;
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    return terms.isEmpty() ? 0 : terms.get(terms.size() - 1).degree;
  }

  /**
   * Finds the index (or insertion point) of a term of given degree in a list of terms in increasing
   * degree order.
   *
   * <p>If the list contains a term of given degree, returns its index. Otherwise, returns {@code
   * -(insertion_point) - 1} where {@code insertion_point} is the index of the first term with
   * degree greater than {@code d}; note that this implies that the return value is non-negative iff
   * the list contains a term of given degree.
   *
   * @see Collections#binarySearch(List, Object)
   * @param lst the not {@code null} list of not {@code null} terms in increasing degree order.
   * @param d the degree.
   * @return the index of the term of given degree, or {@code -insertion_point - 1} if none is
   *     present.
   */
  private static int findByDegree(final List<Term> lst, final int d) {
    int lo = 0;
    int hi = lst.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int midDegree = lst.get(mid).degree;
      if (d < midDegree) hi = mid - 1;
      else if (d > midDegree) lo = mid + 1;
      else return mid;
    }
    return -lo - 1;
  }

  /**
   * Adds a term to a list of terms in increasing degree order.
   *
   * <p>The list will remain in increasing degree order, in case a term with the same degree was
   * present, the two will be added (and removed if the coefficient will become 0).
   *
   * @param lst the not {@code null} list of not {@code null} terms in increasing degree order.
   * @param term the not {@code null} term.
   */
  private static void addTerm(final List<Term> lst, final Term term) {
    if (term.coefficient == 0) return;
    final int i = findByDegree(lst, term.degree);
    if (i >= 0) {
      final int c = lst.get(i).coefficient + term.coefficient;
      if (c != 0) lst.set(i, new Term(c, term.degree));
      else lst.remove(i);
    } else lst.add(-i - 1, term);
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
  public SparsePoly add(SparsePoly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial to add cannot be null.");
    List<Term> result = new ArrayList<>(this.terms);
    for (Term t : q.terms) addTerm(result, t);
    return new SparsePoly(result);
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
  public SparsePoly mul(SparsePoly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial to multiply cannot be null.");
    List<Term> lst = new ArrayList<>();
    for (Term tq : q.terms)
      for (Term tt : terms)
        addTerm(lst, new Term(tq.coefficient * tt.coefficient, tq.degree + tt.degree));
    return new SparsePoly(lst);
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
  public SparsePoly sub(SparsePoly q) throws NullPointerException {
    Objects.requireNonNull(q, "The polynomial to subtract cannot be null.");
    return add(q.minus());
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public SparsePoly minus() {
    final List<Term> lst = new ArrayList<>();
    for (Term t : terms) lst.add(new Term(-t.coefficient, t.degree));
    return new SparsePoly(lst);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof SparsePoly other)) return false;
    return terms.equals(other.terms);
  }

  @Override
  public int hashCode() {
    return terms.hashCode();
  }

  @Override
  public String toString() {
    if (degree() > 0) {
      StringBuilder sb = new StringBuilder("SparsePoly: ");
      int pos = terms.size() - 1;
      Term t = terms.get(pos);
      if (t.coefficient < -1) sb.append("-" + (-t.coefficient));
      else if (t.coefficient == -1) sb.append("-");
      else if (t.coefficient > 1) sb.append(t.coefficient);
      sb.append("x" + (t.degree > 1 ? "^" + t.degree : ""));
      while (--pos >= 0) {
        t = terms.get(pos);
        if (t.degree == 0) break;
        if (t.coefficient < -1) sb.append(" - " + (-t.coefficient));
        else if (t.coefficient == -1) sb.append(" - ");
        else if (t.coefficient == 1) sb.append(" + ");
        else sb.append(" + " + t.coefficient);
        sb.append("x" + (t.degree > 1 ? "^" + t.degree : ""));
      }
      if (t.degree == 0)
        if (t.coefficient > 0) sb.append(" + " + t.coefficient);
        else if (t.coefficient < 0) sb.append(" - " + (-t.coefficient));
      return sb.toString();
    } else return "SparsePoly: " + (terms.isEmpty() ? 0 : terms.get(0).coefficient);
  }
}
