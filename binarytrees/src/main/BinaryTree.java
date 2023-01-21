package main;

public class BinaryTree<T> {
    protected BinaryNode<T> root = null;

    protected int addCounter = 0;
    protected int searchCounter = 0;

    protected int nodeNumber = 0;

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(BinaryNode<T> node) {
        if (node != null)
        {
            printPostOrder( node.left );
            printPostOrder( node.right );
            System.out.println( node.value );
        }
    }

    public void printPreOrder() {
        printPreOrder(root);
    }

    private void printPreOrder(BinaryNode<T> node) {
        if ( node != null )
        {
            System.out.println( node.value );
            printPreOrder( node.left );
            printPreOrder( node.right );
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BinaryNode<T> node) {
        if ( node != null )
        {
            printInOrder(node.left);
            System.out.println(node.value);
            printInOrder(node.right);
        }
    }

    public int getNodeNumber(){
        this.nodeNumber = 0;
        return getNodeNumber(this.root);
    }

    protected int getNodeNumber(BinaryNode<T> node){
        nodeNumber++;
        if (node.left != null)
            getNodeNumber(node.left);
        if (node.right != null)
            getNodeNumber(node.right);
        return nodeNumber;
    }
}

