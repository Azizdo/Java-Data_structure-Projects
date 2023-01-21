package Maze;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

public class JoinabilityAlgorithmMaze {
    private static final ArrayList<ArrayList<Point>> MATRICE_POINT = new ArrayList<>();
    private static final Stack<Point> STACK = new Stack<>();
    public static Counter counter = new Counter();

    /** TODO
     * Returns the distance of the path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the path within the maze, null if not solvable
     */
    public static Integer findPath(ArrayList<ArrayList<Tile>> maze) {
        if(maze.size() == 0)
            return null;
        createPointMatrice(maze);
        Point start = findStart();
        if (start == null) {
            MATRICE_POINT.clear();
            STACK.clear();
            counter.clear();
            return null;
        }
        STACK.addAll(getNeighbors(start));
        Point end = findEnd();
        if (end == null)
            return null;
        printPath(end);
        MATRICE_POINT.clear();
        STACK.clear();
        counter.printTotalNodesTraversed();
        counter.printMaxStackSize();
        counter.clear();
        return end.distanceFromBegining;
    }

    /**
     * Convert string maze to a matrix of Points
     * @param maze 2D table representing the maze
     */
    private static void createPointMatrice(ArrayList<ArrayList<Tile>> maze){
        for(int y = 0; y < maze.size(); y++){
            MATRICE_POINT.add(new ArrayList<>());
            for(int x = 0; x < maze.get(y).size(); x++){
                MATRICE_POINT.get(y).add(new Point(y, x, maze.get(y).get(x)));
            }
        }
    }

    /**
     * Returns the starting point of the maze
     * @return The starting point
     */
    private static Point findStart(){
        for(ArrayList<Point> column : MATRICE_POINT){
            if(column.get(0).typeOfTile == Tile.Exit){
                column.get(0).distanceFromBegining = 0;
                column.get(0).setIsVisited();
                counter.totalNodesTraversed++;
                return column.get(0);
            }
        }
        return null;
    }

    /**
     * Returns an ArrayList of the adjacent floors
     * @param point the current point in visit
     * @return An ArrayList of the adjacent floors
     */
    private static ArrayList<Point> getNeighbors(Point point){
        ArrayList<Point> neighbors = new ArrayList<>();
        ArrayList<Point> goodNeighbors = new ArrayList<>();
        if(point.x != MATRICE_POINT.get(point.y).size() - 1) {
            neighbors.add(MATRICE_POINT.get(point.y).get(point.x + 1)); //right
        }
        if(point.x != 0) {
            neighbors.add(MATRICE_POINT.get(point.y).get(point.x - 1)); //left
        }
        if(point.y != 0){
            neighbors.add(MATRICE_POINT.get(point.y - 1).get(point.x)); //up
        }
        if(point.y != MATRICE_POINT.size() - 1) {
            neighbors.add(MATRICE_POINT.get(point.y + 1).get(point.x)); //down
        }


        for(Point neighbor : neighbors){
            if(neighbor.typeOfTile != Tile.Wall && !neighbor.isVisited){
                MATRICE_POINT.get(neighbor.y).get(neighbor.x).distanceFromBegining = MATRICE_POINT.get(point.y).get(point.x).distanceFromBegining + 1;
                MATRICE_POINT.get(neighbor.y).get(neighbor.x).setPreviousPoint(MATRICE_POINT.get(point.y).get(point.x));
                MATRICE_POINT.get(neighbor.y).get(neighbor.x).setIsVisited();
                counter.totalNodesTraversed++;
                goodNeighbors.add(MATRICE_POINT.get(neighbor.y).get(neighbor.x));
            }
        }
        return goodNeighbors;
    }

    /**
     * Returns the end point of the maze
     * @return The end point
     */
    private static Point findEnd(){
        if(STACK.peek() == null || STACK.size() == 0)
            return null;
        if(STACK.peek().typeOfTile != Tile.Exit){
            Point pointInVisit = STACK.peek();
            STACK.pop();
            STACK.addAll(getNeighbors(pointInVisit));

            counter.stackedNodes = STACK.size();
            if (STACK.size() > counter.maxStackSize)
                counter.maxStackSize = STACK.size();

            findEnd();
        }
        return STACK.peek();
    }

    /**
     * Print the maze with the path in green
     * @param end The end point
     */
    private static void printPath(Point end){
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";

        ArrayList<Point> path = new ArrayList<>();
        path.add(end);
        for (int i = 0; i < end.distanceFromBegining; i++)
            path.add(path.get(path.size() - 1).previousPoint);

        for (ArrayList<Point> row : MATRICE_POINT){
            System.out.print("\n");
            for (Point tile : row){
                if (path.contains(tile)){
                    System.out.print(ANSI_GREEN + tile.typeOfTile.toString() + ANSI_RESET);
                }else {
                    System.out.print(tile.typeOfTile.toString());
                }
            }
        }
        System.out.print("\n");
    }

    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }

    public static class Point{
        public int distanceFromBegining;
        public final Tile typeOfTile;
        public Point previousPoint;
        public final int x;
        public final int y;
        public boolean isVisited = false;

        public Point(int y, int x, Tile typeOfTile){
            this.y = y;
            this.x = x;
            this.typeOfTile = typeOfTile;
        }

        public void setPreviousPoint(Point point){
            previousPoint = point;
        }

        public void setIsVisited(){
            isVisited = true;
        }
    }
}
