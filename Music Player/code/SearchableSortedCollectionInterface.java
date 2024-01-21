// --== CS400 Spring 2023 File Header Information ==--
// Name: Cornelia (Zhouzhou) Chu
// Email: zchu26@wisc.edu
// Team: BC
// TA: Naman Gupta
// Lecturer: Gary Dahl Lec002 1:00 to 2:15 pm
// Notes to Grader: <optional extra notes>
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public interface SearchableSortedCollectionInterface<T extends Comparable<T>> extends SortedCollectionInterface<T> {
    public List<T> search (T object) throws IllegalStateException;
}

