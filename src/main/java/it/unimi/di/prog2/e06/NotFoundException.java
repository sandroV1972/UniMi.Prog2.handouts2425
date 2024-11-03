package it.unimi.di.prog2.e06;

/**
 * Eccezione personalizzata per indicare che un valore non è stato trovato nell'array.
 */
public class NotFoundException extends Exception {
  public NotFoundException(String message) {
    super(message);
  }
  public NotFoundException(int i) {
  }
}