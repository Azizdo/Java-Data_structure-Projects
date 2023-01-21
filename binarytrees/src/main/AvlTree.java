package main;

public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T>{
    @Override
    public void add(T value) {
        this.root = add(value, this.root);
    }

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        addCounter++;
        if( curNode == null){
            return new BinaryNode<T>( value );
        }

        int compareResult= value.compareTo( curNode.value);

        if( compareResult < 0 )
            curNode.left = add( value, curNode.left);
        else if( compareResult > 0 )
            curNode.right = add( value, curNode.right);
        else
            ; // Pas de doublons

        return balance( curNode );
    }

    @Override
    public void remove(T value) {
        this.root = remove(value, this.root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        if( curNode == null)
            return curNode; // Item not found; do nothing

        int compareResult = value.compareTo( curNode.value );

        if( compareResult < 0 )
            curNode.left = remove( value, curNode.left );
        else if( compareResult > 0 )
            curNode.right = remove( value, curNode.right );
        else if( curNode.left != null&& curNode.right != null ) // Twochildren
        {
            curNode.value= findMin( curNode.right ).value;
            curNode.right= remove( curNode.value, curNode.right );
        }
        else
            curNode = ( curNode.left != null ) ? curNode.left : curNode.right;
        return balance( curNode );
    }

   //TODO Ajouter les méthodes manquantes
   private BinaryNode<T> balance( BinaryNode<T> t )
   {
       if( t == null)
           return t;
       if( height( t.left) - height( t.right) > 1 )
       {
           if( height( t.left.left) >= height( t.left.right) )
               t = rotateWithLeftChild( t );
           else
               t = doubleWithLeftChild( t );
       }
       else if( height( t.right) - height( t.left) > 1 )
       {
           if( height( t.right.right) >= height( t.right.left) )
               t = rotateWithRightChild( t );
           else
               t = doubleWithRightChild( t );
       }
       t.height= Math.max( height( t.left), height( t.right) ) + 1;
       return t;
   }

   private static class AvlNode<T>
    {
        // Constructors
        AvlNode( T theElement)
        {
            this( theElement, null, null);
        }
        AvlNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt)
        {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }
        T element; // The data in the node
        BinaryNode<T> left; // Leftchild
        BinaryNode<T> right; // Right child
        int height;// Height
    }
    private int height( BinaryNode<T> t )
    {
        return t == null ? -1 : t.height;
    }

    // Gauche - Gauche
    private BinaryNode<T> rotateWithLeftChild( BinaryNode<T> k2 )
    {
        addCounter++;
        BinaryNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        // Mettre à jour les hauteurs à la rotation
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;

        return k1;
    }

    private BinaryNode<T> doubleWithLeftChild( BinaryNode<T> k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    private BinaryNode<T> rotateWithRightChild( BinaryNode<T> k1 )
    {
        addCounter++;
        BinaryNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        // Mettre à jour les hauteurs à la rotation
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;

        return k2;
    }

    private BinaryNode<T> doubleWithRightChild( BinaryNode<T> k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
}
