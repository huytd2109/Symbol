package hus.oop.integration;

import java.util.Random;

public class TestIntegrationCalculator {
    private MyPolynomial polynomial;

    public TestIntegrationCalculator(MyPolynomial polynomial) {
        this.polynomial = polynomial;
    }

    public static void main(String[] args) {
        /* TODO
         - Thực hiện các yêu cầu trong từng hàm test.
         - Lưu kết quả chạy chương trình vào file text có tên <TenSinhVien_MaSinhVien_Integration>.txt
           (ví dụ, NguyenVanA_123456_Integration.txt)
         - Nộp file kết quả chạy chương trình (file text trên) cùng với các file source code.
         */
        TestIntegrationCalculator test = new TestIntegrationCalculator(null);
        test.testArrayPolynomial();
        test.testListPolynomial();
    }

    public void testArrayPolynomial() {
        /* TODO
         - Sinh ngẫu nhiên một số nguyên, lưu vào biến size. Sinh ngẫu nhiên size số thực. Tạo đa thức kiểu MyArrayPolynomial
           với các hệ số là các số thực vừa sinh ra, lưu vào biến polynomial.
         - Viết chương trình test các chức năng đa thức (thêm phần tử vào đa thức, xóa phần tử trong đa thức,
           sửa hệ số tại một phần tử, cộng 2 đa thức, trừ 2 đa thức, nhân 2 đa thức, tính giá trị của đa thức khi biết
           giá trị của x).
         - Tính tích phân xác định của đa thức được tạo ban đầu với các cận tích phân là 1.0 và 5.0.
         */
        Random random = new Random();
        int size = random.nextInt(5) + 3;
        System.out.println("Generated polynomial size: " + size);

        MyArrayPolynomial poly1 = new MyArrayPolynomial();
        System.out.println("Generated coefficients:");

        for (int i = 0; i < size; i++) {
            double coef = random.nextDouble() * 10 - 5;
            poly1.append(coef);
            System.out.println("  Coefficient " + i + ": " + coef);
        }

        this.polynomial = poly1;
        System.out.println("\nCreated polynomial: " + poly1);

        int testIndex = random.nextInt(size);
        System.out.println("\nCoefficient at index " + testIndex + ": " + poly1.coefficient(testIndex));

        double newCoef = random.nextDouble() * 10 - 5;
        poly1.append(newCoef);
        System.out.println("\nAfter appending " + newCoef + ": " + poly1);

        int setIndex = random.nextInt(size);
        double setValue = random.nextDouble() * 10 - 5;
        poly1.set(setValue, setIndex);
        System.out.println("\nAfter setting " + setValue + " at index " + setIndex + ": " + poly1);

        int addIndex = random.nextInt(size + 1);
        double addValue = random.nextDouble() * 10 - 5;
        poly1.add(addValue, addIndex);
        System.out.println("\nAfter adding " + addValue + " at index " + addIndex + ": " + poly1);

        double x = random.nextDouble() * 5;
        System.out.println("\nEvaluating at x = " + x + ": " + poly1.evaluate(x));

        MyPolynomial derivative = poly1.derivative();
        System.out.println("\nDerivative: " + derivative);

        MyArrayPolynomial poly2 = new MyArrayPolynomial();
        int size2 = random.nextInt(4) + 2;
        System.out.println("\nCreating second polynomial of size: " + size2);

        for (int i = 0; i < size2; i++) {
            double coef = random.nextDouble() * 10 - 5;
            poly2.append(coef);
            System.out.println("  Coefficient " + i + ": " + coef);
        }
        System.out.println("Second polynomial: " + poly2);

        MyPolynomial sum = poly1.plus(poly2);
        System.out.println("\nSum: " + sum);

        MyPolynomial difference = poly1.minus(poly2);
        System.out.println("\nDifference: " + difference);

        MyPolynomial product = poly1.multiply(poly2);
        System.out.println("\nProduct: " + product);

        double lower = 1.0;
        double upper = 5.0;

        MyIntegrator trapezoid = new TrapezoidRule(0.0001, 10);
        MyIntegrator simpson = new SimpsonRule(0.0001, 10);
        MyIntegrator midpoint = new MidpointRule(0.0001, 10);

        double trapezoidResult = trapezoid.integrate(poly1, lower, upper);
        double simpsonResult = simpson.integrate(poly1, lower, upper);
        double midpointResult = midpoint.integrate(poly1, lower, upper);

        System.out.println("\nIntegration results from " + lower + " to " + upper + ":");
        System.out.println("  Trapezoid Rule: " + trapezoidResult);
        System.out.println("  Simpson Rule: " + simpsonResult);
        System.out.println("  Midpoint Rule: " + midpointResult);

        IntegrationCalculator calculator = new IntegrationCalculator(trapezoid, poly1);
        double calculatorResult = calculator.integrate(lower, upper);
        System.out.println("\nUsing IntegrationCalculator: " + calculatorResult);

        System.out.println("\n");
    }

    public void testListPolynomial() {
        /* TODO
         - Sinh ngẫu nhiên một số nguyên, lưu vào biến size. Sinh ngẫu nhiên size số thực. Tạo đa thức kiểu MyListPolynomial
           với các hệ số là các số thực vừa sinh ra, lưu vào biến polynomial.
         - Viết chương trình test các chức năng đa thức (thêm phần tử vào đa thức, xóa phần tử trong đa thức,
           sửa hệ số tại một phần tử, cộng 2 đa thức, trừ 2 đa thức, nhân 2 đa thức, tính giá trị của đa thức khi biết
           giá trị của x).
         - Tính tích phân xác định của đa thức được tạo ban đầu với các cận tích phân là 2.0 và 6.0.
         */
        Random random = new Random();
        int size = random.nextInt(5) + 3; // Between 3 and 7 terms
        System.out.println("Generated polynomial size: " + size);

        MyListPolynomial poly1 = new MyListPolynomial();
        System.out.println("Generated coefficients:");

        for (int i = 0; i < size; i++) {
            double coef = random.nextDouble() * 10 - 5; // Between -5 and 5
            poly1.append(coef);
            System.out.println("  Coefficient " + i + ": " + coef);
        }

        this.polynomial = poly1;
        System.out.println("\nCreated polynomial: " + poly1);

        int testIndex = random.nextInt(size);
        System.out.println("\nCoefficient at index " + testIndex + ": " + poly1.coefficient(testIndex));

        double newCoef = random.nextDouble() * 10 - 5;
        poly1.append(newCoef);
        System.out.println("\nAfter appending " + newCoef + ": " + poly1);

        int setIndex = random.nextInt(size);
        double setValue = random.nextDouble() * 10 - 5;
        poly1.set(setValue, setIndex);
        System.out.println("\nAfter setting " + setValue + " at index " + setIndex + ": " + poly1);

        int addIndex = random.nextInt(size + 1);
        double addValue = random.nextDouble() * 10 - 5;
        poly1.add(addValue, addIndex);
        System.out.println("\nAfter adding " + addValue + " at index " + addIndex + ": " + poly1);

        double x = random.nextDouble() * 5;
        System.out.println("\nEvaluating at x = " + x + ": " + poly1.evaluate(x));

        MyPolynomial derivative = poly1.derivative();
        System.out.println("\nDerivative: " + derivative);

        MyListPolynomial poly2 = new MyListPolynomial();
        int size2 = random.nextInt(4) + 2;
        System.out.println("\nCreating second polynomial of size: " + size2);

        for (int i = 0; i < size2; i++) {
            double coef = random.nextDouble() * 10 - 5;
            poly2.append(coef);
            System.out.println("  Coefficient " + i + ": " + coef);
        }
        System.out.println("Second polynomial: " + poly2);

        MyPolynomial sum = poly1.plus(poly2);
        System.out.println("\nSum: " + sum);

        MyPolynomial difference = poly1.minus(poly2);
        System.out.println("\nDifference: " + difference);

        MyPolynomial product = poly1.multiply(poly2);
        System.out.println("\nProduct: " + product);

        double lower = 2.0;
        double upper = 6.0;

        MyIntegrator trapezoid = new TrapezoidRule(0.0001, 10);
        MyIntegrator simpson = new SimpsonRule(0.0001, 10);
        MyIntegrator midpoint = new MidpointRule(0.0001, 10);

        double trapezoidResult = trapezoid.integrate(poly1, lower, upper);
        double simpsonResult = simpson.integrate(poly1, lower, upper);
        double midpointResult = midpoint.integrate(poly1, lower, upper);

        System.out.println("\nIntegration results from " + lower + " to " + upper + ":");
        System.out.println("  Trapezoid Rule: " + trapezoidResult);
        System.out.println("  Simpson Rule: " + simpsonResult);
        System.out.println("  Midpoint Rule: " + midpointResult);

        IntegrationCalculator calculator = new IntegrationCalculator(simpson, poly1);
        double calculatorResult = calculator.integrate(lower, upper);
        System.out.println("\nUsing IntegrationCalculator: " + calculatorResult);
    }
}
