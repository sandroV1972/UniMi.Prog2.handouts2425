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

package it.unimi.di.prog2.e10;

import java.util.Objects;

/**
 * A rational number is an immutable number that can be expressed as the quotient or fraction \( p/q
 * \) of two {@code int}s, a numerator \( p \) and a non-zero denominator \( q \).
 */
public class RationalNumber {

  // EXERCISE: complete following the specification (with particular attention
  // to the eventual exceptions) and provide an implementation (including the
  // equals, hashCode, and toString methods); add methods that are adequate to
  // the specification.

  private int numerator;
  private int denominator;

  /**
   * Creates a new rational number.
   *
   * @param numerator the numerator.
   * @param denominator the denominator.
   */
  public RationalNumber(int numerator, int denominator) {
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator must not be ZERO");
    }
    this.numerator = numerator;
    this.denominator = denominator;
  }

  /**
   * Returns the sum of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the sum of this rational number and {@code other}.
   */
  public RationalNumber add(RationalNumber other) {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT nothing
    if (this.denominator == other.denominator) {
      RationalNumber res = new RationalNumber(this.numerator+other.numerator, denominator);
      return simplifiedRationalNumber(res);
    }
    else {
      int mcm = mcm(this.denominator, other.denominator);
      RationalNumber res = new RationalNumber((this.numerator * (mcm / this.denominator)) + (other.numerator * (mcm / other.denominator)), mcm);
      return simplifiedRationalNumber(res);
    }
  }

  /**
   * Semplifica un numero razionale.
   *
   * @param res il numero razionale da semplificare.
   * @return un nuovo numero razionale semplificato.
   */
  public RationalNumber simplifiedRationalNumber(RationalNumber res) {
    int gcd = gcd(Math.abs(res.numerator), Math.abs(res.denominator));
    int simplifiedNumerator = res.numerator / gcd;
    int simplifiedDenominator = res.denominator / gcd;
    if (simplifiedNumerator < 0 && simplifiedDenominator < 0) {
      return new RationalNumber(Math.abs(simplifiedNumerator), Math.abs(simplifiedDenominator));
    } else {
      return new RationalNumber(simplifiedNumerator, simplifiedDenominator);
    
    }
  }  

    /**
   * Calcola il minimo comune multiplo (mcm) di due numeri.
   *
   * @param a il primo numero.
   * @param b il secondo numero.
   * @return il minimo comune multiplo di {@code a} e {@code b}.
   */
  private int mcm(int a, int b) {
    return Math.abs(a * b) / gcd(a, b);
  }

  /**
   * Calcola il massimo comune divisore (GCD) di due numeri interi utilizzando l'algoritmo di
   * Euclide.
   *
   * @param a Il primo numero intero.
   * @param b Il secondo numero intero.
   * @return Il massimo comune divisore di a e b.
   */
  public static int gcd(int a, int b) {
  //  REQUIRES due interi
  //  MODIFIES niente
  //  EFFECTS calcola il massimo comun divisore dei due numeri e restituisce il valore
    int temp;
    while (b != 0) {
      temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  /**
   * Returns the product of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the product of this rational number and {@code other}.
   */
  public RationalNumber mul(RationalNumber other) {
    return simplifiedRationalNumber(new RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator));
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RationalNumber that = simplifiedRationalNumber((RationalNumber) o);
    return numerator == that.numerator && 
           denominator == that.denominator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }


}
