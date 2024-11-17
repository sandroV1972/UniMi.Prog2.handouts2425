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

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SparsePoly}s are immutable polynomials with integer coefficients such that the number of
 * nonzero coefficient is small with respect to the degree.
 *
 * <p>A typical {@code Poly} is \( p = c_0 + c_1 x + c_2 x^2 + \cdots + c_n x^n \).
 */
public class SparsePoly {

  /**
   * A record holding a term of the polynomial.
   *
   * @param coeff the coefficient.
   * @param degree the degree.
   */
  public record Term(int coeff, int degree) {
    /**
     * Builds a term.
     *
     * @throws NegativeExponentException if if {@code degree} &lt; 0.
     */
    public Term { // using the compact constructors
      if (degree < 0)
        throw new NegativeExponentException("A term cannot have a negative exponent.");
    }
  }

  /** The array of terms (in increasing non-zero degree). */
  private final List<Term> terms;

  /** Initializes this to be the zero polynomial, that is \( p = 0 \). */
  public SparsePoly() {
    this.terms = new ArrayList<>();
  }

  /**
   * Initializes this to be the polynomial \(p = cx^n\).
   *
   * @param c the coefficient.
   * @param n the degree.
   * @throws NegativeExponentException if {@code n} &lt; 0.
   */
  public SparsePoly(int c, int n) throws NegativeExponentException {
    this.terms = new ArrayList<>();
    this.terms.add(new Term(c, n));; // replace this with the actual implementation
  }

  /**
   * Clona un oggetto SparsePoly.
   *
   * @param p il polinomio da clonare.
   * @return una copia del polinomio dato.
   */
  public SparsePoly clone() {
    SparsePoly clone = new SparsePoly();
    for (Term term : this.terms) {
      clone.terms.add(new Term(term.coeff(), term.degree()));
    }
    return clone;
  }


  /**
   * Retrieve the index of a term with specified degree.
   *
   * <p>return the index of the term with a specific degree.
   *
   * @param d the degree of the term we are serching.
   * @return the index in the List<> of Terms.
   *         -1 if no term with that degree is found
   */
  public int getIndex(int d) {
    for (int i = 0; i < terms.size(); i++) {
      if (terms.get(i).degree() == d) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the coefficient of the term of given exponent.
   *
   * @param d the exponent of the term to consider.
   * @return the coefficient of the considered term.
   */
  public int coeff(int d) {
    for (Term term : terms) {
      if (term.degree() == d) {
        return term.coeff();
      }
    }
    return 0; // replace this with the actual implementation
  }

  /**
   * Returns the degree of this polynomial.
   *
   * @return the largest exponent with a non-zero coefficient; returns 0 if this is the zero {@code
   *     Poly}.
   */
  public int degree() {
    int max_d = terms.get(0).degree;
    for (Term term : terms) {
      if (term.degree() > max_d) {
        max_d = term.degree();
      }
    }
    return max_d;
    //return 0; // replace this with the actual implementation
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
    if (q == null) throw new NullPointerException();
    SparsePoly r = new SparsePoly();
    
    for (Term t : this.terms) {
      r.terms.add(new Term(t.coeff(), t.degree()));
    }
    
    for (Term t : q.terms) {
      int index = r.getIndex(t.degree());
      if (index != -1) {
        Term existingTerm = r.terms.get(index);
        int newCoeff = existingTerm.coeff() + t.coeff();
        if (newCoeff != 0) {
          r.terms.set(index, new Term(newCoeff, t.degree()));
        } else {
          r.terms.remove(index);
        }
      } else {
        r.terms.add(new Term(t.coeff(), t.degree()));
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
  public SparsePoly mul(SparsePoly q) throws NullPointerException {
    if (q == null) throw new NullPointerException();
    if ((q.terms.size() == 0 || this.terms.size() == 0) || ((q.degree() == 0 && q.coeff(0) == 0) || (this.degree() == 0 && this.coeff(0) == 0))) return new SparsePoly();
    SparsePoly r = new SparsePoly();
    int c, d;
    for (Term t : this.terms) {
      for (Term tt : q.terms) {
        d = t.degree() + tt.degree();
        c = t.coeff() * tt.coeff();
        int index = r.getIndex(d);
        if (index != -1) {
          Term existingTerm = r.terms.get(index);
          int newCoeff = existingTerm.coeff() + c;
          if (newCoeff != 0) {
            r.terms.set(index, new Term(newCoeff, d));
          } else {
            r.terms.remove(index);
          }
        } else {
          r.terms.add(new Term(c, d));
        }
      }
    }
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
  public SparsePoly sub(SparsePoly q) throws NullPointerException {
    if (q == null) throw new NullPointerException();
    return add(q.minus()); // replace this with the actual implementation
  }

  /**
   * Returns the negate polynomial.
   *
   * <p>If \( p \) is this polynomial, returns \( -p \).
   *
   * @return this polynomial multiplied by \( -1 \).
   */
  public SparsePoly minus() {
    SparsePoly r = new SparsePoly();
    for (Term term: terms) 
      r.terms.add(new Term(-term.coeff(), term.degree()));
    return r; // replace this with the actual implementation
  }
}
