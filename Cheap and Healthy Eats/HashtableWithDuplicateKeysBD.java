// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HashtableWithDuplicateKeysBD<KeyType,ValueType> implements HashtableWithDuplicateKeysInterface<KeyType, ValueType> {
  
  public int capacity;
  public List<ValueType>[] hashmap;

  public HashtableWithDuplicateKeysBD() {
    // TODO Auto-generated constructor stub
  }
  
  public HashtableWithDuplicateKeysBD(int capacity) {
    hashmap = (List<ValueType>[]) new ArrayList[100];
    for(int i = 0; i < capacity; i++) {
      hashmap[i] = new ArrayList<ValueType>();
    }
    this.capacity = 100;
  }

  @Override
  public void put(KeyType key, List<ValueType> value) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    return;
  }

  @Override
  public boolean containsKey(KeyType key) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<ValueType> get(KeyType key) throws NoSuchElementException {
    int index = Math.abs(key.hashCode()) % this.capacity;
    
    return hashmap[index];
  }

  @Override
  public List<ValueType> remove(KeyType key) throws NoSuchElementException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    return;
    
  }

  @Override
  public int getSize() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getCapacity() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void putOne(KeyType key, ValueType value) {
    int index = Math.abs(key.hashCode()) % 100;
    hashmap[index].add(value);
    
    return;
    
  }

  @Override
  public void removeOne(KeyType key, ValueType value) {
    // TODO Auto-generated method stub
    return;
    
  }

  @Override
  public int getNumberOfValues() {
    // TODO Auto-generated method stub
    return 0;
  }

}
