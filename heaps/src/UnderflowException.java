
/**
 * Class to make an exception when we try to delete from an empty array.
 *
 * extends java.lang.RuntimeException.
 */
public class UnderflowException extends java.lang.RuntimeException {
    public UnderflowException(){
        super("Empty Heap!!!!");
    }
}
