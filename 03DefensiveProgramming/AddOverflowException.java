/**
 * A class for signaling exceptions in additions.
 * 
 * @version  2.0
 * @author   Eric Steegmans
 */
public class AddOverflowException extends OverflowException {
    
    /**
     * Initialize this new add overflow exception with given operands.
     * 
     * @param  left
     *         The left operand involved in the new add overflow exception.
     * @param  right
     *         The right operand involved in the new add overflow exception.
     * @effect This new add overflow exception is initialized as an overflow
     *         exception with the given left operand as its left operand and
     *         the given right operand as its right operand.
     *       | super(left,right)
     */
    public AddOverflowException(long left, long right) {
        super(left,right);
    }

    /**
     * The Java API strongly recommends to explicitly define a version
     * number for classes that implement the interface Serializable.
     * At this stage, that aspect is of no concern to us. 
     */
    private static final long serialVersionUID = 2003001L;    

}
