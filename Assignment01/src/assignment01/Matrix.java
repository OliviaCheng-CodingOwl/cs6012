package assignment01;

public class Matrix {
    int numRows;
    int numColumns;
    int data[][];

    // Constructor with data for new matrix (automatically determines dimensions)
    public Matrix(int data[][]) {
        numRows = data.length; // d.length is the number of 1D arrays in the 2D array
        if (numRows == 0) {
            numColumns = 0;
        } else {
            numColumns = data[0].length; // d[0] is the first 1D array
        }
        this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
        // copy the data over
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    @Override // instruct the compiler that we do indeed intend for this method to override
    // the superclass' (Object) version
    public boolean equals(Object other) {
        if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
            return false;
        }
        Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix

        if (matrix.numColumns == this.numColumns && matrix.numRows == this.numRows) {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    if (matrix.data[i][j] != data[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        } else return false;
    }

    @Override // instruct the compiler that we do indeed intend for this method to override
    // the superclass' (Object) version
    public String toString() {
        String strMatrix = "";
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                strMatrix += (data[i][j] + " ");
                if (j == numColumns - 1) {
                    strMatrix += "\n";
                }
            }
        }
        return strMatrix;
    }

    public Matrix times(Matrix matrix) {
        if (matrix.numRows != numColumns) {
            return null;
        }

        Matrix result = new Matrix(new int[numRows][matrix.numColumns]);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < matrix.numColumns; j++) {
                for (int n = 0; n < numColumns; n++) {
                    result.data[i][j] += data[i][n] * matrix.data[n][j];
                }
            }
        }
        return result;
    }

    public Matrix plus(Matrix matrix) {
        //check if the two matrices are compatible for addition
        if (matrix.numColumns != numColumns || matrix.numRows != numRows) {
            return null;
        }

        Matrix result = new Matrix(new int[numRows][numColumns]);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                result.data[i][j] = data[i][j] + matrix.data[i][j];
            }
        }
        return result;
    }

}