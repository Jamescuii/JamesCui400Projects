import java.util.List;

public interface SearchableSortedCollectionInterfaceAE<T extends Comparable<T>> extends SortedCollectionInterfaceAE<T> {
  public List<T> search (T term) throws IllegalStateException;
}
