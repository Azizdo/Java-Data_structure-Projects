package Maze;

public class Counter {
    public int totalNodesTraversed = 0;
    public int stackedNodes = 0;
    public int maxStackSize = 0;

    public void clear(){
        totalNodesTraversed = 0;
        stackedNodes = 0;
        maxStackSize = 0;
    }

    public void printTotalNodesTraversed(){
        System.out.println("Total nodes traversed : " + totalNodesTraversed);
    }
    public void printMaxStackSize(){
        System.out.println("Max stack/recursion/queue size : " + maxStackSize);
    }
}
