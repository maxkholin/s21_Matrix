import java.util.Scanner;

public class s21_Matrix {

    private final double PRECISION = 1E-7;
    private int rows;
    private int cols;
    private int currentRows;
    private int currentCols;
    private double[][] Matrix;
    private String[] exceptions = {
            "Incorrect initialization: the number of rows or columns cannot be less than one",
            "Different dimensions of matrices",
            "The number of columns of the first matrix is not equal to the number of rows of the second matrix",
            "The matrix is not square",
            "The determinant of the matrix is 0"
    };


    // Constructors
    public s21_Matrix() {
        rows = 2;
        cols = 2;
        currentRows = rows;
        currentCols = cols;
        initMatrix(rows,cols);
    }
    public s21_Matrix(int rows, int cols, int value) {
        if (rows<1 || cols<1) {
            try {
                throw new MatrixExceptions(exceptions[0]);
            } catch (MatrixExceptions e) {
                throw new RuntimeException(e);
            }
            System.out.println("Exception!!! rows<1 || cols<1");
            this.rows = 2;
            this.cols = 2;
        }
        else {
            this.rows = rows;
            this.cols = cols;
        }
        currentRows = this.rows;
        currentCols = this.cols;
        initMatrix(currentRows,currentCols, value);
    }
    public s21_Matrix(int rows, int cols) {
        if (rows<1 || cols<1) {
            System.out.println("Exception!!! rows<1 || cols<1");
            this.rows = 2;
            this.cols = 2;
        }
        else {
            this.rows = rows;
            this.cols = cols;
        }
        currentRows = this.rows;
        currentCols = this.cols;
        int value = 0;
        initMatrix(currentRows,currentCols, value);
    }
    public s21_Matrix(Scanner scan) {
        rows = scan.nextInt();
        cols = rows;
        currentRows = rows;
        currentCols = rows;
        Matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Matrix[i][j]= scan.nextInt();
            }
        }
    }
    
    // Print Matrix
    public void printMatrix() {
        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
                System.out.printf("%4.0f",Matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Initialization Matrix and Fill Matrix
    private void initMatrix(int rows, int cols) {
        Matrix = new double[rows][cols];
        int value = 1;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                Matrix[i][j] = value;
                value++;
            }
        }
    }
    private void initMatrix(int rows, int cols, int startValue) {
        Matrix = new double[rows][cols];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                Matrix[i][j] = startValue;
                startValue++;
            }
        }
    }
    public void fillMatrix(int startValue,int step) {
        for (int i = 0; i<currentRows; i++) {
            for (int j = 0; j<currentCols; j++) {
                this.Matrix[i][j]=startValue;
                startValue+=step;
            }
        }
    }

    // Set Matrix
    public void setMatrix(int rows, int cols) {
        if (rows<1 || cols<1) {
            System.out.println("Exception!!! rows<1 || cols<1");
            return;
        }
        var MatrixCopy = new double[rows][cols];
        for (int i = 0; i<rows && i<currentRows; i++) {
            for (int j = 0; j<cols && j<currentCols; j++) {
                MatrixCopy[i][j]=Matrix[i][j];
            }
        }
        currentRows = rows;
        currentCols = cols;
        this.Matrix = MatrixCopy;
    }
    public void setMatrixAsDefault() {
        setMatrix(2,2);
        fillMatrix(1,1);
    }

    // Getters and Setters
    public int getRows(){
        return currentRows;
    }
    public void setRows(int rows) {
        if (rows<1) {
            System.out.println("Exception!!! rows<1");
            return;
        }
        setMatrix(currentRows, currentCols);
    }
    public int getCols() {
        return currentCols;
    }
    public void setCols(int cols) {
        if (cols<1) {
            System.out.println("Exception!!! cols<1");
            return;
        }
        setMatrix(currentRows, currentCols);
    }

    // Methods
    public boolean EqMatrix(s21_Matrix MatrixForEq){
        if (MatrixForEq == null) {
            return false; // исключение
        }
        int rows1 = currentRows;
        int cols1 = currentCols;
        int rows2 = MatrixForEq.getRows();
        int cols2 = MatrixForEq.getCols();
        if (rows1!=rows2 || cols1!=cols2) {
            return false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.Matrix[i][j]!=MatrixForEq.Matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public void sumMatrix(s21_Matrix MatrixForSum) { // !!! Тут будет исключение
        int rows1 = currentRows;
        int cols1 = currentCols;
        int rows2 = MatrixForSum.getRows();
        int cols2 = MatrixForSum.getCols();
        if (rows1!=rows2 || cols1!=cols2) {
            System.out.println("Do EXCEPTION !!!");
            return; // !!! Тут будет исключение
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.Matrix[i][j]+=MatrixForSum.Matrix[i][j];
            }
        }
    }
    public void subMatrix(s21_Matrix MatrixForSub) { // !!! Тут будет исключение
        if (MatrixForSub == null) {
            System.out.println("Do EXCEPTION !!!");
            return; // !!! Тут будет исключение
        }
        int rows1 = currentRows;
        int cols1 = currentCols;
        int rows2 = MatrixForSub.getRows();
        int cols2 = MatrixForSub.getCols();
        if (rows1!=rows2 || cols1!=cols2) {
            System.out.println("Do EXCEPTION !!!");
            return; // !!! Тут будет исключение
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.Matrix[i][j]-=MatrixForSub.Matrix[i][j];
            }
        }
    }
    public void mulNumber(int number) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.Matrix[i][j]*=number;
            }
        }
    }
    public void mulMatrix(s21_Matrix MatrixForMul) {
        if (MatrixForMul == null) {
            System.out.println("Do EXCEPTION-NULL !!!");
            return; // !!! Тут будет исключение
        }
        int rows1 = currentRows;
        int cols2 = MatrixForMul.getCols();
        if (rows1!=cols2) {
            System.out.println("Do EXCEPTION-rows1!=cols2 !!! " +
                    "ROWS count Matrix1 not equals COLS count Matrix2");
            System.out.printf("%d %d", rows1, cols2);
            return; // !!! Тут будет исключение
        }
        var MatrixCopy = new double[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols2; k++) {
                   MatrixCopy[i][j] += this.Matrix[i][k] * MatrixForMul.Matrix[k][j];
                }
            }
        }
        currentRows = rows1;
        currentCols = cols2;
        this.Matrix = MatrixCopy;
    }
    public s21_Matrix transpose() {
        s21_Matrix Matrix2 = new s21_Matrix(currentCols, currentRows);
        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
                Matrix2.Matrix[j][i] = this.Matrix[i][j];
            }
        }
        return Matrix2;
    }
    public s21_Matrix CalcComplements() {
        if (currentRows!=currentCols) {
            System.out.println("Exception!!! not square Matrix");
            return null;
        }
        s21_Matrix MatrixRes = new s21_Matrix(currentRows,currentCols);
        if (currentRows == 1) {
           MatrixRes.Matrix[0][0] = this.Matrix[0][0];
        } else if (currentRows == 2) {
            s21_Matrix forMinor = new s21_Matrix(1,1);
            for (int i = 0; i < currentRows; i++) {
                for (int j = 0; j < currentCols; j++) {
                    minor(i, j, forMinor);
                    MatrixRes.Matrix[i][j] = forMinor.Matrix[0][0] * Math.pow(-1, (i + j));
                }
            }
        } else {
            s21_Matrix forMinor = new s21_Matrix(currentRows-1,currentCols-1);
            double result;
            for (int i = 0; i < currentRows; i++) {
                for (int j = 0; j < currentCols; j++) {
                    minor(i, j, forMinor);
                    result = forMinor.determinant();
                    MatrixRes.Matrix[i][j] = result * Math.pow(-1, (i + j));
                }
            }
        }
        return MatrixRes;
    }
    private void minor(int I, int J, s21_Matrix MatrixRes) {
        int resI = 0;
        int resJ = 0;
        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
                if (i != I && j != J) {
                    MatrixRes.Matrix[resI][resJ] = Matrix[i][j];
                    if (resJ < (MatrixRes.getCols()) - 1) {
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
        double result = 0;
        if (currentCols == currentRows) {
            if (currentRows== 1) {
                result = Matrix[0][0];
            } else if (currentRows == 2) {
                result = (Matrix[0][0] * Matrix[1][1]) -
                            (Matrix[0][1] * Matrix[1][0]);
            } else if (currentRows >= 3) {
                if (zeroDetermChecker()) {
                    result = 0;
                } else {
                    int swapCnt = 0;
                    while (zeroCnt() != 0) {
                        swapCnt = swap(swapCnt);
                    }
                    for (int i = 0; i <currentRows; i++) {
                        for (int j = 0; j < currentCols; j++) {
                            if (i > j) {
                                if (Math.abs(Matrix[i][j] - 0.000000) > PRECISION) {
                                    double coef;
                                    if (j == 0) {
                                        coef = (Matrix[i][0] / Matrix[0][0]) * (-1);
                                        for (int elem = 0; elem < currentCols; elem++) {
                                            Matrix[i][elem] += (Matrix[0][elem] * coef);
                                        }
                                    } else {
                                        coef = (Matrix[i][j] / Matrix[i - 1][j]) * (-1);
                                        for (int elem = j; elem < currentCols; elem++) {
                                            Matrix[i][elem] += (Matrix[i - 1][elem] * coef);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    result = 1;
                    for (int a = 0; a < currentRows; a++) {
                        for (int b = 0; b < currentCols; b++) {
                            if (a == b) {
                                result *= Matrix[a][b];
                            }
                        }
                    }
                    if (swapCnt % 2 != 0) {
                        result *= (-1);
                    }
                }
            }
        } else {
            System.out.println("DO Exception!!!!");
        }
        return result;
    }
    private boolean zeroDetermChecker() {
        int ZeroColumnCounter = 0;
        int ZeroRowCounter = 0;
        for (int i = 0; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
                if (Matrix[j][i] == 0) {
                    ZeroColumnCounter++;
                }
                if (Matrix[i][j] == 0) {
                    ZeroRowCounter++;
                }
            }
            if (ZeroColumnCounter == currentRows || ZeroRowCounter == currentCols) {
                break;
            } else {
                ZeroColumnCounter = 0;
                ZeroRowCounter = 0;
            }
        }
        return (ZeroColumnCounter == currentRows || ZeroRowCounter == currentCols);
    }
    private int zeroCnt() {
        int zero_count = 0;
        for (int i = 0; i < currentRows; i++)
            if (Matrix[i][i] == 0) {
                zero_count++;
            }
        return zero_count;
    }
    private int swap(int swapCnt) {
        double[] buffer = new double[currentCols];
        int RowToSwap;
        for (int i = 0; i <currentRows; i++) {
            if (Matrix[i][i] == 0) {
                RowToSwap = findRowToSwap();
                for (int j = 0; j < currentCols; j++) {
                    buffer[j] = Matrix[RowToSwap][j];
                    Matrix[RowToSwap][j] = Matrix[i][j];
                    Matrix[i][j] = buffer[j];
                }
                swapCnt++;
            }
        }
        return swapCnt;
    }
    private int findRowToSwap() {
        int NoZeroCnt = 0;
        int i = 0;
        for (; i < currentRows; i++) {
            for (int j = 0; j < currentCols; j++) {
                if (Matrix[i][j] != 0) {
                    NoZeroCnt++;
                }
            }
            if (NoZeroCnt == currentCols) {
                break;
            }
            else {
                NoZeroCnt = 0;
            }
        }
        return i;
    }
    public s21_Matrix inverseMatrix() {
        s21_Matrix MatrixResult = null;
        if (currentRows == currentCols) {
            double res = 0;
            s21_Matrix MatrixCopy = new s21_Matrix(currentRows,currentCols);
            for (int i = 0; i < currentRows; i++) {
                for (int j = 0; j < currentCols; j++) {
                    MatrixCopy.Matrix[i][j] = Matrix[i][j];
                }
            }
            res = MatrixCopy.determinant();
            if (Math.abs(res - 0.000000) > PRECISION) {
                s21_Matrix MatrixTranspose = transpose();
                s21_Matrix MatrixInverse = MatrixTranspose.CalcComplements();
                for (int i = 0; i < currentRows; i++) {
                    for (int j = 0; j < currentCols; j++) {
                        MatrixInverse.Matrix[i][j] *= (1 / res);
                    }
                }
                MatrixResult = MatrixInverse;
            }
            else {
                System.out.println("EXCEPTION!!!! DETERM = 0!!!!");
            }
        }
        else {
            System.out.println("EXCEPTION!!! NOT SQUARE - DETERM NOT EXIST");
        }
        return MatrixResult;
    }
}

