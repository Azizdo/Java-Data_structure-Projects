package main;

public interface Tree<T extends Comparable<T>> {

    public void add(T data);

    public boolean contains(T data);

    public void remove(T data);

    public int getAddCounter();
    public int getSearchCounter();
}
