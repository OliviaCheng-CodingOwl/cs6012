package assignment01;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixJUnitTester {

    int arr[][] = {{1, 2, 3}, {4, 5, 6}};
    Matrix matrix = new Matrix(arr);


    @org.junit.jupiter.api.Test
    void testEquals() {
        Matrix matrix = new Matrix(arr);
        assertEquals(false, matrix.equals(arr));//arr1 not an instance of Matrix return false
        assertEquals(true, matrix.equals(this.matrix));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("1 2 3 " + "\n" + "4 5 6 " + "\n", matrix.toString());
    }

    @org.junit.jupiter.api.Test
    void times() {
        int multi1[][] = {{0, 0}, {0, 0}, {0, 0}};//multiplier all 0
        Matrix matrix_multi1 = new Matrix(multi1);
        int prod1[][] = {{0, 0}, {0, 0}};
        Matrix matrix_prod1 = new Matrix(prod1);
        assertEquals(matrix_prod1, matrix.times(matrix_multi1));

        int multi2[][] = {{-1, 6}, {-4, 0}, {7, 3}};//multiplier contains both positive and negative
        Matrix matrix_multi2 = new Matrix(multi2);
        int prod2[][] = {{12, 15}, {18, 42}};
        Matrix matrix_prod2 = new Matrix(prod2);
        assertEquals(matrix_prod2, matrix.times(matrix_multi2));

        int multi3[][] = {{-1, 6, 15}, {-4, 0, 33}};// don't have compatible dimensions
        Matrix matrix_multi3 = new Matrix(multi3);
        assertEquals(null, matrix.times(matrix_multi3));

        //test overflow
//        int multi4[][] ={ {2147483647,2147483647},{2147483647,2147483647},{2147483647,2147483647}};
//        Matrix matrix_multi4 = new Matrix(multi4);
//        int prod4[][] = {{12884901882, 12884901882}, {32212254705, 32212254705}};
//        Matrix matrix_prod2 = new Matrix(prod2);
//        assertEquals(matrix_prod2,matrix.times(matrix_multi2));

    }

    @org.junit.jupiter.api.Test
    void plus() {
        int additive1[][] = {{-1, 6}, {-4, 0}, {7, 3}};//don't have compatible dimensions
        Matrix matrix_additive1 = new Matrix(additive1);
        assertEquals(null, matrix.plus(matrix_additive1));

        int additive2[][] = {{-5, 7, 9}, {8, -19, 33}};//test negative
        Matrix matrix_additive2 = new Matrix(additive2);
        int sum2[][] = {{-4, 9, 12}, {12, -14, 39}};
        Matrix matrix_sum2 = new Matrix(sum2);
        assertEquals(matrix_sum2, matrix.plus(matrix_additive2));
    }
}