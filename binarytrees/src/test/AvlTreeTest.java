package test;

import main.AvlTree;
import main.BinarySearchTree;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

class AVLTreeTester {
    public static void main(String[] args) {

        AvlTree<Integer> avl = new AvlTree<Integer>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        ArrayList<Integer> toInsert = new ArrayList<Integer>();
        for(int i = 0; i < 200; i++){   //changer le 200 pour d'autre valeur pour augmenter le nombre de données dans l'arbre
            toInsert.add((int)(Math.random()*10000));
        }

        //Array pour le pire cas
        /*ArrayList<Integer> toInsert = new ArrayList<Integer>();
        for(int i = 0; i < 100; i++){
            toInsert.add(i);
        }*/


        for(int i = 0; i < toInsert.size(); i++) {
            avl.add(toInsert.get(i));
            bst.add(toInsert.get(i));
        }

        for (int i = 0; i < toInsert.size(); i++)
        {
            bst.contains(toInsert.get(i));
            avl.contains(toInsert.get(i));
        }

        System.out.println("Bst node number: "); System.out.println(bst.getNodeNumber());
        System.out.println("Bst add count: "); System.out.println(bst.getAddCounter());
        System.out.println("Bst search counter: "); System.out.println(bst.getSearchCounter()); System.out.println("");
        System.out.println("");
        System.out.println("Avl node number: "); System.out.println(avl.getNodeNumber());
        System.out.println("Avl add counter: "); System.out.println(avl.getAddCounter());
        System.out.println("Avl search counter: "); System.out.println(avl.getSearchCounter()); System.out.println("");


        //System.out.println("Parcours préordre de BST: "); bst.printPreOrder(); System.out.println("");
        //System.out.println("Parcours en ordre de BST: "); bst.printInOrder(); System.out.println("");
        //System.out.println("Parcours postordre de BST: "); bst.printPostOrder(); System.out.println("");
        //System.out.println("Bst contains 6: "); System.out.println(bst.contains(6)); System.out.println("");
        //System.out.println("Bst search counter: "); System.out.println(bst.getSearchCounter()); System.out.println("");
        //System.out.println("Bst contains 10: "); System.out.println(bst.contains(10)); System.out.println("");
        //System.out.println("Bst search counter: "); System.out.println(bst.getSearchCounter()); System.out.println("");

        //System.out.println("Parcours préordre de AVL: "); avl.printPreOrder(); System.out.println("");
        //System.out.println("Parcours en ordre de AVL: "); avl.printInOrder(); System.out.println("");
        //System.out.println("Parcours postordre de AVL: "); avl.printPostOrder(); System.out.println("");
        //System.out.println("Avl contains 5: "); System.out.println(avl.contains(5)); System.out.println("");
        //System.out.println("Avl search counter: "); System.out.println(avl.getSearchCounter()); System.out.println("");
        //System.out.println("Avl contains 10: "); System.out.println(avl.contains(10)); System.out.println("");
        //System.out.println("Avl search counter: "); System.out.println(avl.getSearchCounter()); System.out.println("");
    }
}
