package Point;

import java.util.*;

public final class PointOperator {

    /** TODO
     * Translates a vector of size N by another vector of size N
     * Similar to adding two vector of size N together
     * @param vector Vector to translate
     * @param translateVector Translation to apply
     */
    public static void translate(Double[] vector, Double[] translateVector) {
        for (int n = 0; n < vector.length; n++) {
            vector[n] += translateVector[n];
        }
    }

    /** TODO
     * Rotates a vector of size N by a matrix of size N x N
     * Similar to a matrix vector multiplication
     * @param vector Vector to rotate
     * @param rotationMatrix Matrix by which to rotate
     */
    public static void rotate(Double[] vector, Double[][] rotationMatrix) {
        Double[] baseVector = new Double[vector.length];
        System.arraycopy(vector, 0, baseVector, 0, vector.length);

        for(int i = 0; i < baseVector.length; i++)
        {
            double count = 0.0;
            for(int j = 0; j < baseVector.length; j++)
            {
                count += baseVector[j]*rotationMatrix[i][j];
            }
            vector[i] = count;
        }
    }

    /** TODO
     * Divide a vector of size N by a scalar
     * @param vector Vector to divide
     * @param divider Scalar by which to divide
     */
    public static void divide(Double[] vector, Double divider) {
        for (int n = 0; n < vector.length; n++) {
            vector[n] = vector[n] / divider;
        }
    }

    /** TODO
     * Multiply a vector of size N by a scalar
     * @param vector Vector to multiply
     * @param multiplier Scalar by which to multiply
     */
    public static void multiply(Double[] vector, Double multiplier) {
        for (int n = 0; n < vector.length; n++) {
            vector[n] = vector[n] * multiplier;
        }
    }

    /** TODO
     * Add a scalar to a vector of size N
     * @param vector Vector that we have to add to
     * @param adder Scalar to add to vector
     */
    public static void add(Double[] vector, Double adder) {
        for (int n = 0; n < vector.length; n++) {
            vector[n] = vector[n] + adder;
        }
    }
}
