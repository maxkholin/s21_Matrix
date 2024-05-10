import java.util.Arrays;

public class Matrix {

    private final static double PRECISION = 1E-7;
    private final static double DEFAULT_START_VALUE = 0.0;
    private final static double STEP = 1.0;
    private final static int DEFAULT_ROWS = 2;
    private final static int DEFAULT_COLS = 2;
    private final static int CORRECT_AMOUNT = 1;

    private int rows;
    private int cols;
    private double[][] matrix;

    // Constructors
    public Matrix() {
        this(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_START_VALUE);
    }

    public Matrix(int rows, int cols) {
        this(rows, cols, DEFAULT_START_VALUE);
    }

    public Matrix(int rows, int cols, double startValue) {
        if (rows < CORRECT_AMOUNT || cols < CORRECT_AMOUNT) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.INCORRECT_INITIALIZATION));
        } else {
            this.rows = rows;
            this.cols = cols;
        }

        initMatrix(this.rows, this.cols, startValue);
    }

    private void initMatrix(int rows, int cols, double startValue) {
        this.matrix = new double[rows][cols];
        fillMatrix(startValue, Matrix.STEP);
    }

    public void fillMatrix(double startValue, double step) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = startValue;
                startValue += step;
            }
        }
    }

    // Print Matrix
    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.printf("%4.0f", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Set Matrix
    public void setMatrix(int rows, int cols) {
        if (rows < CORRECT_AMOUNT || cols < CORRECT_AMOUNT) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.INCORRECT_INITIALIZATION));
        }

        double[][] set = new double[rows][cols];
        for (int i = 0; i < rows && i < this.rows; i++) {
            for (int j = 0; j < cols && j < this.cols; j++) {
                set[i][j] = matrix[i][j];
            }
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = set;
    }

    public void setMatrixAsDefault() {
        setMatrix(DEFAULT_ROWS, DEFAULT_COLS);
        fillMatrix(DEFAULT_START_VALUE, STEP);
    }

    // Getters and Setters
    public int getRows() {
        return this.rows;
    }

    public void setRows(int rows) {
        if (rows < CORRECT_AMOUNT) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.INCORRECT_INITIALIZATION));
        }
        setMatrix(rows, this.cols);
    }

    public int getCols() {
        return this.cols;
    }

    public void setCols(int cols) {
        if (cols < CORRECT_AMOUNT) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.INCORRECT_INITIALIZATION));
        }
        setMatrix(this.rows, cols);
    }

    // Methods
    public boolean eqMatrix(Matrix other) {
        return Arrays.deepEquals(this.matrix, other.matrix);
    }

    public void addMatrix(Matrix other) {
        int rows1 = this.rows;
        int cols1 = this.cols;
        int rows2 = other.getRows();
        int cols2 = other.getCols();

        if (rows1 != rows2 || cols1 != cols2) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.DIFFERENT_DIMENSIONS));
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] += other.matrix[i][j];
            }
        }
    }

    public void subMatrix(Matrix other) {
        int rows1 = this.rows;
        int cols1 = this.cols;
        int rows2 = other.getRows();
        int cols2 = other.getCols();

        if (rows1 != rows2 || cols1 != cols2) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.DIFFERENT_DIMENSIONS));
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] -= other.matrix[i][j];
            }
        }
    }

    public void mulNumber(int number) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] *= number;
            }
        }
    }

    public void mulMatrix(Matrix other) {
        int rows1 = this.rows;
        int cols2 = other.getCols();

        if (rows1 != cols2) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.MISMATCHED_DIMENSIONS));
        }

        double[][] result = new double[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols2; k++) {
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }

        this.rows = rows1;
        this.cols = cols2;
        this.matrix = result;
    }

    public Matrix transpose() {
        Matrix transpose = new Matrix(this.cols, this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                transpose.matrix[j][i] = this.matrix[i][j];
            }
        }

        return transpose;
    }

    public Matrix CalcComplements() {
        if (this.rows != this.cols) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.NOT_SQUARE));
        }

        Matrix result = new Matrix(this.rows, this.cols);
        boolean isSingleRow = this.rows == 1;
        boolean isSquareRows = this.cols == 2;
        // you can move each part of if/else if/else to separate methods for better readability
        if (isSingleRow) {
            copySingleRow(this, result);
        } else if (isSquareRows) {
            calculateSquareRows(this, result);
        } else {
            calculateNonSquareRows(this, result);
        }

        return result;
    }

    private void copySingleRow(Matrix source, Matrix destination) {
        destination.matrix[0][0] = source.matrix[0][0];
    }

    private void calculateSquareRows(Matrix source, Matrix destination) {
        Matrix minor = new Matrix(1, 1);
        for (int i = 0; i < source.rows; i++) {
            for (int j = 0; j < source.cols; j++) {
                calculateMinor(source, i, j, minor);
                destination.matrix[i][j] = minor.matrix[0][0] * Math.pow(-1, (i + j));
            }
        }
    }

    private void calculateNonSquareRows(Matrix source, Matrix destination) {
        Matrix minor = new Matrix(source.rows - 1, source.cols - 1);
        double result;
        for (int i = 0; i < source.rows; i++) {
            for (int j = 0; j < source.cols; j++) {
                calculateMinor(source, i, j, minor);
                result = minor.determinant();
                destination.matrix[i][j] = result * Math.pow(-1, (i + j));
            }
        }
    }

    private void calculateMinor(Matrix source, int I, int J, Matrix destination) {
        int resI = 0;
        int resJ = 0;
        for (int i = 0; i < source.rows; i++) {
            for (int j = 0; j < source.cols; j++) {
                if (i != I && j != J) {
                    destination.matrix[resI][resJ] = source.matrix[i][j];
                    int previousColumn = destination.getCols() - 1;
                    if (resJ < previousColumn) {
                        resJ++;
                    } else {
                        resJ = 0;
                        resI++;
                    }
                }
            }
        }
    }

    public double determinant() {
        if (!isSquare()) {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.NOT_SQUARE));
        }

        if (this.rows == 1) {
            return matrix[0][0];
        } else if (this.rows == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            return calculateDeterminant();
        }
    }

    private double calculateDeterminant() {
        double result;
        if (isZeroDeterminant()) {
            result = 0;
        } else {
            int swapCnt = 0;
            while (zeroCounter() != 0) {
                swapCnt = swap(swapCnt);
            }
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    if (i > j) {
                        if (Math.abs(this.matrix[i][j] - 0.000000) > PRECISION) {
                            double coef;
                            if (j == 0) {
                                coef = (this.matrix[i][0] / this.matrix[0][0]) * (-1);
                                for (int elem = 0; elem < this.cols; elem++) {
                                    this.matrix[i][elem] += (this.matrix[0][elem] * coef);
                                }
                            } else {
                                coef = (this.matrix[i][j] / this.matrix[i - 1][j]) * (-1);
                                for (int elem = j; elem < this.cols; elem++) {
                                    this.matrix[i][elem] += (this.matrix[i - 1][elem] * coef);
                                }
                            }
                        }
                    }
                }
            }
            result = 1;
            for (int a = 0; a < this.rows; a++) {
                for (int b = 0; b < this.cols; b++) {
                    if (a == b) {
                        result *= this.matrix[a][b];
                    }
                }
            }
            if (swapCnt % 2 != 0) {
                result *= (-1);
            }
        }

        return result;
    }

    private boolean isSquare() {
        return rows == cols;
    }

    private boolean isZeroDeterminant() {
        int zeroColumnCounter = 0;
        int zeroRowCounter = 0;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.matrix[j][i] == 0) {
                    zeroColumnCounter++;
                }
                if (this.matrix[i][j] == 0) {
                    zeroRowCounter++;
                }
            }

            if (zeroColumnCounter == this.rows || zeroRowCounter == this.cols) {
                break;
            } else {
                zeroColumnCounter = 0;
                zeroRowCounter = 0;
            }
        }

        return (zeroColumnCounter == this.rows || zeroRowCounter == this.cols);
    }

    private int zeroCounter() {
        int zeroCounter = 0;
        for (int i = 0; i < this.rows; i++)
            if (this.matrix[i][i] == 0) {
                zeroCounter++;
            }

        return zeroCounter;
    }

    private int swap(int swapCounter) {
        double[] buffer = new double[this.cols];
        int rowToSwap;
        for (int i = 0; i < this.rows; i++) {
            if (this.matrix[i][i] == 0) {
                rowToSwap = findRowToSwap();
                for (int j = 0; j < this.cols; j++) {
                    buffer[j] = this.matrix[rowToSwap][j];
                    this.matrix[rowToSwap][j] = this.matrix[i][j];
                    this.matrix[i][j] = buffer[j];
                }
                swapCounter++;
            }
        }

        return swapCounter;
    }

    private int findRowToSwap() {
        int noZeroCounter = 0;
        int i = 0;
        for (; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.matrix[i][j] != 0) {
                    noZeroCounter++;
                }
            }
            if (noZeroCounter == this.cols) {
                break;
            } else {
                noZeroCounter = 0;
            }
        }
        return i;
    }

    public Matrix inverseMatrix() {
        Matrix matrixResult;
        if (this.rows == this.cols) {
            double result;
            Matrix other = new Matrix(this.rows, this.cols);

            result = other.determinant();
            if (Math.abs(result - 0.000000) > PRECISION) {
                Matrix matrixTranspose = transpose();
                Matrix matrixInverse = matrixTranspose.CalcComplements();
                for (int i = 0; i < this.rows; i++) {
                    for (int j = 0; j < this.cols; j++) {
                        matrixInverse.matrix[i][j] *= (1 / result);
                    }
                }
                matrixResult = matrixInverse;
            } else {
                throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.ZERO_DETERMINANT));
            }
        } else {
            throw new MatrixExceptions(exceptionsMessages.getMessage(exceptionsMessages.NOT_SQUARE));
        }
        return matrixResult;
    }

}

