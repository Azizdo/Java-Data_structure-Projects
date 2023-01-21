package Maze;

import java.util.*;
import java.util.stream.Collectors;


/** TODO
 ** Implement DFS algorithm to solve the maze.
 */
public class DFSMaze {
    private static ArrayList<ArrayList<Point>> MATRICE_POINT = new ArrayList<>();
    private static boolean isFindExit = false;
    public static Counter counter = new Counter();

    /** TODO
     * Returns the distance of the path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the path within the maze, null if not solvable
     */
    public static Integer findPath(ArrayList<ArrayList<Tile>> maze) {
        if(maze.size() == 0)
            return null;
        MATRICE_POINT.clear();
        counter.clear();
        isFindExit = false;
        createPointMatrice(maze);
        if(findStart() == null)
            return null;
        traversalToExit(findStart());
        //printPath(findExitPoint());
        counter.printTotalNodesTraversed();
        counter.printMaxStackSize();
        counter.clear();
        if (findExitPoint() == null)
            return null;
        return findExitPoint().distanceFromBegening;
    }

    /**
     * traverse recurcively the neighbors of the matrice until it reach exit point
     * set all the distanceFromBegening of each node traversed
     */
    private static void traversalToExit(Point point){
        counter.stackedNodes++;
        if(counter.stackedNodes > counter.maxStackSize)
            counter.maxStackSize = counter.stackedNodes;
        MATRICE_POINT.get(point.y).get(point.x).isVisited = true;
        Point lastPoint = point;
        point.getNeighbors(point).forEach(neighbor -> {
            if(!neighbor.isVisited){
                MATRICE_POINT.get(neighbor.y).get(neighbor.x).distanceFromBegening = point.distanceFromBegening + 1;
                counter.totalNodesTraversed++;
                if (neighbor.x == findExitPoint().x && neighbor.y == findExitPoint().y)
                    return;
                traversalToExit(neighbor);
                counter.stackedNodes--;
            }
        });
    }


    /**
     * Convert string maze to a matrix of Points
     * @param maze 2D table representing the maze
     */
    private static void createPointMatrice(ArrayList<ArrayList<Tile>> maze){
        for(int y = 0; y < maze.size(); y++){
            MATRICE_POINT.add(new ArrayList<>());
            for(int x = 0; x < maze.get(y).size(); x++){
                MATRICE_POINT.get(y).add(new Point(x, y, maze.get(y).get(x)));
            }
        }
    }

    /**
     * Returns the starting point of the maze
     * @return The starting point
     */
    private static Point findStart(){
        for(int y = 0; y < MATRICE_POINT.size(); y++){
            if(MATRICE_POINT.get(y).get(0).typeOfTile == Tile.Exit){
                MATRICE_POINT.get(y).get(0).distanceFromBegening = 0;
                MATRICE_POINT.get(y).get(0).startPoint = true;
                return MATRICE_POINT.get(y).get(0);
            }
        }
        return null;
    }

    /**
     * Returns the end point of the maze
     * @return The end point
     */
    private static Point findExitPoint(){
        for(int y = 0; y < MATRICE_POINT.size(); y++){
            if(MATRICE_POINT.get(y).get(MATRICE_POINT.get(y).size() - 1).typeOfTile == Tile.Exit){
                return MATRICE_POINT.get(y).get(MATRICE_POINT.get(y).size() - 1);
            }
        }
        return null;
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
        for (int y = 0; y < MATRICE_POINT.size(); y++){
            for (int x = 0; x < MATRICE_POINT.get(y).size(); x++){
                 if (MATRICE_POINT.get(y).get(x).isVisited == true)
                    path.add(MATRICE_POINT.get(y).get(x));
            }
        }
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

    /**
     * Print the maze
     * @param maze The maze to print
     */
    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }

    /**
     * Intern Point class implementation
     *
     */
    public static class Point{
        public int distanceFromBegening;
        private Tile typeOfTile;
        private static boolean startPoint = false;
        private Point previousPoint;
        private int x;
        private int y;
        private boolean isVisited = false;

        public Point(int x, int y, Tile typeOfTile){
            this.x = x;
            this.y = y;
            this.typeOfTile = typeOfTile;
        }

        public void setPreviousPoint(Point previousPoint){
            this.previousPoint = previousPoint;
        }

        public void setIsVisited(Point point){
            MATRICE_POINT.get(point.y).get(point.x).isVisited = true;
        }

        public void setIsNotVisited(){
            isVisited = false;
        }

        /**
         * Returns an ArrayList of the adjacent floors
         * @param point the current point in visit
         * @return An ArrayList of the adjacent floors
         */
        private ArrayList<Point> getNeighbors(Point point){
            ArrayList<Point> neighbors = new ArrayList<>();
            if(point.y != 0){
                Point upNeighbor = MATRICE_POINT.get(point.y - 1).get(point.x);
                neighbors.add(upNeighbor);
            }
            if(point.y != MATRICE_POINT.get(0).size() - 1) {
                Point downNeighbor = MATRICE_POINT.get(point.y + 1).get(point.x);
                neighbors.add(downNeighbor);
            }
            if(point.x != 0) {
                Point leftNeighbor = MATRICE_POINT.get(point.y).get(point.x - 1);
                neighbors.add(leftNeighbor);
            }
            if(point.x != MATRICE_POINT.get(point.y).size() - 1) {
                Point rightNeighbor = MATRICE_POINT.get(point.y).get(point.x + 1);
                neighbors.add(rightNeighbor);
            }

            ArrayList<Point> goodNeighbors = new ArrayList<>();
            for(Point neighbor : neighbors){
                if(neighbor.typeOfTile != Tile.Wall){
                    MATRICE_POINT.get(neighbor.y).get(neighbor.x).setPreviousPoint(MATRICE_POINT.get(point.y).get(point.x));
                    goodNeighbors.add(MATRICE_POINT.get(neighbor.y).get(neighbor.x));
                }
            }
            return goodNeighbors;
        }
    }
}

