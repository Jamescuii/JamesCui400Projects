// --== CS400 Project One File Header ==--
// Name: James Cui
// CSL Username: jcui
// Email: jcui57@wisc.edu
// Lecture #: 002
// Notes to Grader: None

import java.util.NoSuchElementException;

public class HashtableMap<KeyType,ValueType> implements MapADT<KeyType,ValueType> {
  
  private static class Bundle<KeyType,ValueType> {
    private KeyType key;
    private ValueType value;
    
    public Bundle(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }
  }

  private int capacity;
  private int size = 0;
  private Bundle<KeyType,ValueType>[] map;
  //public Object[] map;
  
  public HashtableMap() {
    this.capacity = 8;
    
    //map = new Bundle[capacity];
    //@SuppressWarnings("unchecked")
    map = (Bundle<KeyType, ValueType>[]) new Bundle[capacity]; 
    /* IS THIS CORRECT You cannot
    //instantiate an array with an associated generic type in Java */
  }
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    map = (Bundle<KeyType, ValueType>[]) new Bundle[capacity]; // Are default value set to null?
    //map = new Object[capacity];
  }
  
  public void put(KeyType key, ValueType value) throws IllegalArgumentException {
    if(key == null || containsKey(key)) {
      throw new IllegalArgumentException("key invalid");
    }
    int index = Math.abs(key.hashCode()) % this.capacity;
    Bundle<KeyType, ValueType> toAdd = new Bundle<>(key, value);
    //int tracker = 0;
    for(int i = index; i <= map.length; i++) {
      if(i == map.length) {
        i = 0;
      }
      if(map[i] == null) {
        map[i] = toAdd;
        size++;
        break;
      }
      if(map[i].key == null) {
        map[i] = toAdd;
        size++;
        break;
      }
    }
    
    double load = ((double)size) / ((double)capacity);
    if(load >= 0.7) {
      resize();
    }
    return;
    // if size reaches 70% resize;
    
  }
  
  protected void resize() {
    int tempCap = this.capacity*2;
    @SuppressWarnings("unchecked")
    Bundle<KeyType,ValueType>[] newArray = (Bundle<KeyType, ValueType>[]) new Bundle[tempCap];
    for(int i = 0; i < map.length; i++) {
      if(map[i] != null && map[i].key != null) {
        int index = Math.abs((map[i].key).hashCode()) % tempCap;
        //int tracker = 0;
        for(int j = index; j <= tempCap; j++) {
          if(j == tempCap) {
            j = 0;
          }
          if(newArray[j] == null) {
            newArray[j] = map[i];
            break;
          }
        }
          // BUG
      }
    }
    this.map = newArray;
    this.capacity = tempCap;
  }
  
  public boolean containsKey(KeyType key) { // works?
    int index = Math.abs((key).hashCode()) % this.capacity;
    int tracker = 0;
    while(tracker < this.capacity) {
      if(map[index] == null) {
        return false;
      }
      if(map[index].key != null && (map[index].key).equals(key)) {
        return true;
      }
      tracker++;
      index = (index+1) % this.capacity;
    }
    
    return false;
    /*
    for(int i = 0; i < map.length; i++) {
      if(map[i] != null && (map[i].key).equals(key)) {
        return true;
      }
    }
    return false;
    */
  }
  
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = Math.abs((key).hashCode()) % this.capacity;
    int tracker = 0;
    while(tracker < this.capacity) {
      if(map[index] == null) {
        throw new NoSuchElementException("no value with key");
      }
      if(map[index].key != null && (map[index].key).equals(key)) {
        return map[index].value;
      }
      tracker++;
      index = (index+1) % this.capacity;
    }
    throw new NoSuchElementException("no value with key");
    
    /*
    for(int i = 0; i < map.length; i++) {
      if(map[i] != null && (map[i].key).equals(key)) {
        return map[i].value;
      }
    }
    throw new NoSuchElementException("no value with key");
    */
  }
  
  public ValueType remove(KeyType key) throws NoSuchElementException {
    int index = Math.abs((key).hashCode()) % this.capacity;
    int tracker = 0;
    while(tracker < this.capacity) {
      if((map[index] != null && map[index].key != null) && (map[index].key).equals(key)) {
        size--;
        ValueType temp = map[index].value;
        map[index].value = null;
        map[index].key = null;
        return temp;
      }
      tracker++;
      index = (index+1) % this.capacity;
    }
    throw new NoSuchElementException("no value with key");
    /*
    for(int i = 0; i < map.length; i++) {
      if(map[i] != null && (map[i].key).equals(key)) {
        size--;
        ValueType temp = map[i].value;
        map[i] = null;
        return temp;
      }
    }
    throw new NoSuchElementException("no value with key");
    */
  }
  
  public void clear() {
    this.map = (Bundle<KeyType, ValueType>[]) new Bundle[this.capacity]; // or keep removing
    size = 0;
  }
  
  public int getSize() {
    return size;
  }
  
  public int getCapacity() {
    return capacity;
  }

}
