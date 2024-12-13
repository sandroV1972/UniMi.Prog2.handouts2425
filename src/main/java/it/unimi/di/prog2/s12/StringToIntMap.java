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

package it.unimi.di.prog2.s12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A map from {@link String} to {@link Integer}.
 *
 * <p>A <em>map</em> is a collection that associates keys to values. In this case, the keys are
 * strings and the values are integers. The map cannot contain duplicate keys, which means that each
 * key can be associated to at most one value.
 */
public class StringToIntMap {

  /** A list containing the keys */
  private final List<String> keys;

  /** A list containing the values */
  private final List<Integer> values;

  /*-
   * AF:
   *
   *   AF(keys, values) = a map where keys.get(i) is associated to values.get(i) for each i in [0, keys.size()).
   *
   * RI:
   *
   *  - keys != null and does not contain nulls,
   *  - values != null and does not contain nulls,
   *  - keys.size() == values.size(),
   *  - keys is in strictly increasing lexicographyc order.
   *
   */

  /** Creates a new empty map. */
  public StringToIntMap() {
    keys = new ArrayList<>();
    values = new ArrayList<>();
  }

  /**
   * Returns the size of this map.
   *
   * @return the number of key-value mappings in this map.
   */
  public int size() {
    return keys.size();
  }

  /**
   * Returns if this map is empty.
   *
   * @return {@code true} iff this map contains no key-value mappings.
   */
  public boolean isEmpty() {
    return keys.isEmpty();
  }

  /**
   * Finds the index (or insertion point) of a string in a list of strings kept in increasing
   * lexicographyc order.
   *
   * <p>If the list contains the given string, returns its index. Otherwise, returns {@code
   * -(insertion_point) - 1} where {@code insertion_point} is the index of the first string greater
   * than {@code needle}; note that this implies that the return value is non-negative iff the list
   * contains the string.
   *
   * @see Collections#binarySearch(List, Object)
   * @param haystack the not {@code null} list of not {@code null} strings in increasing degree
   *     order.
   * @param needle the string to look for, must not be {@code null}.
   * @return the index of the given string, or {@code -insertion_point - 1} if none is present.
   */
  private static int dichotomicSearch(final List<String> haystack, final String needle) {
    int lo = 0;
    int hi = haystack.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      int cmp = needle.compareTo(haystack.get(mid));
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    return -lo - 1;
  }

  /**
   * Returns if this map contains the specified key.
   *
   * @param key the key to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code key}.
   */
  public boolean containsKey(String key) {
    return dichotomicSearch(keys, key) >= 0;
  }

  /**
   * Returns if this map contains the specified value.
   *
   * @param value the value to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code value}.
   */
  public boolean containsValue(int value) {
    return values.indexOf(value) != -1;
  }

  /**
   * Returns the value to which the specified key is mapped.
   *
   * @param key the key whose associated value is to be returned.
   * @return the value to which the specified key is mapped.
   * @throws NoSuchElementException if this map contains no mapping for the key, or the key is
   *     {@code null}.
   */
  public int get(String key) throws NoSuchElementException {
    if (key == null) throw new NoSuchElementException("The key is null.");
    int insertionPoint = dichotomicSearch(keys, key);
    if (insertionPoint < 0) throw new NoSuchElementException("The key is not present in the map.");
    return values.get(insertionPoint);
  }

  /**
   * Associates in this map the new key with the specified value.
   *
   * @param key the key with which the specified value is to be associated.
   * @param value the value to be associated with the specified key.
   * @throws IllegalArgumentException if the map already contain a mapping for the key.
   * @throws NullPointerException if the key is {@code null}.
   */
  public void put(String key, int value) {
    int insertionPoint =
        dichotomicSearch(keys, Objects.requireNonNull(key, "The key cannot be null."));
    if (insertionPoint >= 0)
      throw new IllegalArgumentException(
          "Key already present, associated value: " + values.get(insertionPoint));
    keys.add(-insertionPoint - 1, key);
    values.add(-insertionPoint - 1, value);
  }

  /**
   * Removes the mapping for a key from this map if it is present.
   *
   * @param key the key whose mapping is to be removed from the map.
   * @return {@code true} iff this map contained a mapping for the specified key, and hence is
   *     modified by this operation.
   */
  public boolean remove(String key) {
    if (key == null) return false;
    int insertionPoint = dichotomicSearch(keys, key);
    if (insertionPoint < 0) return false;
    keys.remove(insertionPoint);
    values.remove(insertionPoint);
    return true;
  }

  /** Removes all of the mappings from this map. */
  public void clear() {
    keys.clear();
    values.clear();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof StringToIntMap other)) return false;
    return keys.equals(other.keys) && values.equals(other.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(keys, values);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StringToIntMap: {");
    for (int i = 0; i < keys.size(); i++) {
      sb.append(keys.get(i) + "->" + values.get(i));
      if (i < keys.size() - 1) sb.append(", ");
    }
    sb.append("}");
    return sb.toString();
  }
}
