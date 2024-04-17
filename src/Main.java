import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Create Object Matrix
        s21_Matrix M1 = new s21_Matrix();
        s21_Matrix M2 = new s21_Matrix(2,2,5);
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
        System.out.println(M1.EqMatrix(M2));
        System.out.println();
        // Sum Matrix
        System.out.println("Sum Matrix");
        M1.sumMatrix(M2);
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
        s21_Matrix M3 = M1.transpose();
        M1.printMatrix();
        M3.printMatrix();
        M1.setMatrixAsDefault();
        M1.printMatrix();
        // Calc complements
        System.out.println("Calc complements");
        s21_Matrix M4 = new s21_Matrix(scan);
        M4.printMatrix();
        s21_Matrix M5 = M4.CalcComplements();
        M5.printMatrix();
        M4.printMatrix();
        // Determinant
        System.out.println("Determinant");
        M4.printMatrix();
        System.out.println(M1.determinant());
        // Inverse Matrix
        s21_Matrix M6 = new s21_Matrix(scan);
        M6.printMatrix();
        s21_Matrix M7 = M6.inverseMatrix();
        M7.printMatrix();
    }
}
