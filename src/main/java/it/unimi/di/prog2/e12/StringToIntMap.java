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

package it.unimi.di.prog2.e12;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A map from {@link String} to {@link Integer}.
 *
 * <p>A <em>map</em> is a collection that associates keys to values. In this case, the keys are
 * strings and the values are integers. The map cannot contain duplicate keys, which means that each
 * key can be associated to at most one value.
 */


  /*
   * A <em>Map</em> is a collection, a data structure, that links a key to a value.
   * The keys is unique. 
   * A <em>StringToIntMap</em> is a map where the key is a string and the value is an Integer.
   * 
   */
public class StringToIntMap {

  // EXERCISE: provide a representation, together with its AF and RI
  // Note: do not use the Map in Java Collections, the point is to implement it from scratch!

  // the total number of key-value in the map
  private int size;

  // the List of Strings representing the keys of the map
  private final List<String> keys;

  // the List if integers representing the values of the map
  private final List<Integer> values;

    /*-
   * AF:
   *
   *  AF(keys, values) =
   *      [keys[0], keys[1], ..., keys[n-1]] se l'inidice index   0 <= index < n
   *      [values[0], values[1], ..., values[n-1]] se l'inidice index   0 <= index < n 
   *      l'associazione key value è basata sull'indice 
   * RI:
   *
   *  - keys or values are not null and have equal length equal to the number of items in the List
   *  - keys are String and are unique
   *  - values are integers and have the same index of the correpsonding key
   *
   */
  /** Creates a new empty map. */
  public StringToIntMap() {
    keys = new ArrayList<String>();
    values = new ArrayList<Integer>();
  }

  /**
   * Returns the size of this map.
   *
   * @return the number of key-value mappings in this map.
   */
  public int size() {
    return size;
  }

  /**
   * Returns if this map is empty.
   *
   * @return {@code true} if this map contains no key-value mappings.
   */
  public boolean isEmpty() {
    if (size != 0) return true; 
    return false;
  }

  /**
   * Returns if this map contains the specified key.
   *
   * @param key the key to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code key}.
   */
  public boolean containsKey(String key) {
    if (keys.indexOf(key) >= 0) return true;
    return false;
  }

  /**
   * Returns if this map contains the specified value.
   *
   * @param value the value to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code value}.
   */
  public boolean containsValue(int value) {
    if (values.indexOf(Integer.valueOf(value)) >= 0) return true;
    return false;
  }

  /**
   * Returns the value to which the specified key is mapped.
   *
   * @param key the key whose associated value is to be returned.
   * @return the value to which the specified key is mapped.
   * @throws NoSuchElementException if this map contains no mapping for the key.
   */
  public int get(String key) throws NoSuchElementException {
    int index = keys.indexOf(key);
    if (index == -1) {
      throw new NoSuchElementException ("Elemento di chiave key non trovato.");
    }
    return values.get(index);
  }

  /**
   * Associates the specified value with the specified key in this map.
   *
   * @param key the key with which the specified value is to be associated.
   * @param value the value to be associated with the specified key.
   * @return {@code true} iff this map did not already contain a mapping for the key, and hence is
   *     modified by this operation.
   */
  public boolean put(String key, int value) {
    if (!this.containsKey(key)) {
      keys.add(key);
      values.add(Integer.valueOf(value));
      size++;
      return true;
    } else {
      values.set(keys.indexOf(key), Integer.valueOf(value));
    }
    return false;
  }

  /**
   * Removes the mapping for a key from this map if it is present.
   *
   * @param key the key whose mapping is to be removed from the map.
   * @return {@code true} iff this map contained a mapping for the specified key, and hence is
   *     modified by this operation.
   */
  public boolean remove(String key) {
    if(this.containsKey(key)) {
      int index = keys.indexOf(key);
      keys.remove(index);
      values.remove(index);
      size--;
      return true;
    }
    return false;
  }

  /** Removes all of the mappings from this map. */
  public void clear() {
    keys.clear();
    values.clear();
    size = 0;
  }
}
