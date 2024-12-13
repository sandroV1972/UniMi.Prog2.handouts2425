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

package it.unimi.di.prog2.h13;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Classe che rappresenta un portafoglio azionario.
 *
 * <p>Questa specificazione (e conseguente implementazione) ha molti difetti, tra cui:
 *
 * <ul>
 *   <li>le azioni hanno prezzo immutabile;
 *   <li>si possono creare azioni della medesima società con prezzo diverso;
 *   <li>non c'è modo di controllare il numero totale di azioni in circolazione per una data
 *       società;
 * </ul>
 *
 * nelle prossime lezioni alcuni di questi difetti saranno corretti.
 */
public class PortafoglioAzionario {

  /**
   * Rappresenta una azione di una società.
   *
   * @param società il nome della società, deve essere non null e non vuoto.
   * @param prezzo il prezzo di una azione, deve essere maggiore di 0.
   */
  public record Azione(String società, int prezzo) {

    /** Costruisce una azione. */
    public Azione {
      if (società == null || società.isBlank())
        throw new IllegalArgumentException("società non può essere null o vuoto");
      if (prezzo <= 0) throw new IllegalArgumentException("prezzo deve essere maggiore di 0");
    }
  }

  /** Elenco di azioni nel portafoglio, per i vincoli vedi AF/IR. */
  private final List<Azione> azione;

  /** Elenco della quantità di azioni nel portafoglio, per i vincoli vedi AF/IR. */
  private final List<Integer> quantità;

  /*-
   * AF:
   *
   *   - AF(azione, quantità) = il portafoglio è dato dalle azioni elencate in azione e,
   *         per ciascuna di esse dalla quantità contenuta nella corrispondente posizione di quantità.
   *
   *
   * RI:
   *
   *   - azione e quantità non sono null e non contengono null;
   *   - azione e quantità hanno la stessa lunghezza;
   *   - quantità contiene solo valori maggiori di 0;
   *   - la lista non contiene più di una azione della medesima società.
   *
   */

  /** Costruisce un portafoglio vuoto. */
  public PortafoglioAzionario() {
    azione = new ArrayList<>();
    quantità = new ArrayList<>();
  }

  /**
   * Aggiunge l'assegnato numero di azioni al portafoglio.
   *
   * @param azione una azione.
   * @param numero quante azioni aggiungere.
   * @throws IllegalArgumentException se numero è minore o uguale a 0.
   * @throws NullPointerException se azione è null.
   */
  public void aggiungi(Azione azione, int numero) throws IllegalArgumentException {
    Objects.requireNonNull(azione, "l'azione non può essere null");
    if (numero <= 0) throw new IllegalArgumentException("numero deve essere maggiore di 0");
    if (this.azione.contains(azione)) {
      int index = this.azione.indexOf(azione);
      quantità.set(index, quantità.get(index) + numero);
    } else {
      this.azione.add(azione);
      quantità.add(numero);
    }
  }

  /**
   * Cerca una azione per nome della società.
   *
   * @param società il nome della società.
   * @return l'indice della azione in {@link #azione}, -1 se non presente.
   */
  private int azione(String società) {
    for (int i = 0; i < azione.size(); i++) if (azione.get(i).società().equals(società)) return i;
    return -1;
  }

  /**
   * Rimuove l'assegnato numero di azioni al portafoglio.
   *
   * @param azione una azione.
   * @param numero quante azioni rimuovere.
   * @throws IllegalArgumentException se numero è minore o uguale a 0.
   * @throws NoSuchElementException se il portafoglio non contiene abbastanza azioni.
   * @throws NullPointerException se azione è null.
   */
  public void rimuovi(Azione azione, int numero)
      throws IllegalArgumentException, NoSuchElementException {
    Objects.requireNonNull(azione, "l'azione non può essere null");
    if (numero <= 0) throw new IllegalArgumentException("numero deve essere maggiore di 0");
    if (this.azione.contains(azione)) {
      int index = this.azione.indexOf(azione);
      if (quantità.get(index) < numero)
        throw new NoSuchElementException(
            "il portafoglio contiene solo "
                + quantità.get(index)
                + " azioni della società "
                + azione.società());
      quantità.set(index, quantità.get(index) - numero);
    } else {
      throw new NoSuchElementException(
          "il portafoglio non contiene azioni della società " + azione.società());
    }
  }

  /**
   * Rimuove l'assegnato numero di azioni della data società al portafoglio.
   *
   * @param società il nome della società.
   * @param numero quante azioni rimuovere.
   * @throws IllegalArgumentException se numero è minore o uguale a 0.
   * @throws NoSuchElementException se il portafoglio non contiene abbastanza azioni.
   * @throws NullPointerException se società è null.
   */
  public void rimuovi(String società, int numero)
      throws IllegalArgumentException, NoSuchElementException {
    Objects.requireNonNull(società, "la società non può essere null");
    if (numero <= 0) throw new IllegalArgumentException("numero deve essere maggiore di 0");
    int index = azione(società);
    if (index < 0)
      throw new NoSuchElementException(
          "il portafoglio non contiene azioni della società " + società);
    if (quantità.get(index) < numero)
      throw new NoSuchElementException(
          "il portafoglio contiene solo "
              + quantità.get(index)
              + " azioni della società "
              + società);
    quantità.set(index, quantità.get(index) - numero);
  }

  /**
   * Restituisce il numero di azioni di una società nel portafoglio.
   *
   * @param società il nome della società.
   * @return il numero di azioni della società, 0 se non presenti.
   * @throws NullPointerException se società è null.
   */
  public int quante(String società) {
    Objects.requireNonNull(società, "società non può essere null");
    int index = azione(società);
    return index < 0 ? 0 : quantità.get(index);
  }

  /**
   * Restituisce il valore totale del portafoglio.
   *
   * @return il valore totale del portafoglio.
   */
  public int valore() {
    int valore = 0;
    for (int i = 0; i < azione.size(); i++) {
      valore += azione.get(i).prezzo() * quantità.get(i);
    }
    return valore;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < azione.size(); i++) {
      sb.append(azione.get(i).società()).append(": ").append(quantità.get(i)).append("\n");
    }
    return sb.toString();
  }
}
