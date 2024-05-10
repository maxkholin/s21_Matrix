public class Main {
    public static void main(String[] args) {
        // Create Object Matrix
        Matrix M1 = new Matrix();
        Matrix M2 = new Matrix(2,2,0);
        // Go
        System.out.printf("M1 rows: %d cols: %d\n",M1.getRows(),M1.getCols());
        System.out.printf("M2 rows: %d cols: %d\n",M2.getRows(),M2.getCols());
        System.out.println();
        // Print
        System.out.println("Print Matrix");
        M1.printMatrix();
        M2.printMatrix();
        // Equals
        System.out.println("Equals Matrix");
        System.out.println(M1.eqMatrix(M2));
        System.out.println();
        // Sum Matrix
        System.out.println("Sum Matrix");
        M1.addMatrix(M2);
        M1.printMatrix();
        // Sub Matrix
        System.out.println("Sub Matrix");
        M1.subMatrix(M2);
        M1.printMatrix();
        // Multiplication Number
        System.out.println("Mul Number");
        M1.mulNumber(3);
        M1.printMatrix();
        // Set Matrix
        System.out.println("Set Matrix M1: 4 rows, \tM2: 6 cols");
        M1.setRows(4);
        M2.setCols(6);
        M1.printMatrix();
        M2.printMatrix();
        System.out.println("Set to default");
        M1.setMatrix(2,2);
        M2.setMatrix(2,2);
        M1.printMatrix();
        M2.printMatrix();
        // Multiplication Matrix
        System.out.println("Multiplication Matrix");
        M1.fillMatrix(1,1);
        M1.mulMatrix(M2);
        M1.printMatrix();
        // Transpose Matrix
        System.out.println("Transpose Matrix");
        M1.setMatrix(2,4);
        M1.printMatrix();
        Matrix M3 = M1.transpose();
        M1.printMatrix();
        M3.printMatrix();
        M1.setMatrixAsDefault();
        M1.printMatrix();
        // Calc complements
        System.out.println("Calc complements");
        Matrix M4 = new Matrix();
        M4.printMatrix();
        Matrix M5 = M4.CalcComplements();
        M5.printMatrix();
        // Determinant
        System.out.println("Determinant");
        M4.printMatrix();
        System.out.println(M1.determinant());
        // Inverse Matrix
        System.out.println("Inverse Matrix");
        Matrix M6 = new Matrix();
        M6.printMatrix();
        Matrix M7 = M6.inverseMatrix();
        M7.printMatrix();
    }
}
