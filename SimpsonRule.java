package hus.oop.integration;

public class SimpsonRule implements MyIntegrator {
    private double precision;
    private int maxIterations;

    public SimpsonRule(double precision, int maxIterations) {
        this.precision = precision;
        this.maxIterations = maxIterations;
    }

    /**
     * Tính xấp xỉ giá trị tích phân. Giá trị xấp xỉ được chấp nhận nếu phép tính đạt độ chính xác đã cho,
     * hoặc có số vòng vượt quá ngưỡng quy định.
     * Độ chính xác được xác định như sau, chọn n0 tùy ý, sau đó tính I_n với n = n0, 2n0, 4n0, ...
     * Việc tính toán dừng lại khi |I_2n - In|/3 < eps (precision), hoặc số lần chia đôi vượt quá ngưỡng quy định (maxIterations).
     * @param polynomial
     * @param lower
     * @param upper
     * @return
     */
    @Override
    public double integrate(MyPolynomial polynomial, double lower, double upper) {
        int n = 2;
        double currentApprox = integrate(polynomial, lower, upper, n);

        int iterations = 0;
        while (iterations < maxIterations) {
            n *= 2;
            double nextApprox = integrate(polynomial, lower, upper, n);

            if (Math.abs(nextApprox - currentApprox) / 15.0 < precision) {
                return nextApprox;
            }

            currentApprox = nextApprox;
            iterations++;
        }

        return currentApprox;
    }

    /**
     * Tính xấp xỉ giá trị tích phân với numOfSubIntervals (số chẵn) khoảng phân hoạch đều.
     * @param polynomial
     * @param lower
     * @param upper
     * @param numOfSubIntervals
     * @return giá trị xấp xỉ giá trị tích phân.
     */
    private double integrate(MyPolynomial polynomial, double lower, double upper, int numOfSubIntervals) {
        if (numOfSubIntervals % 2 != 0) {
            numOfSubIntervals++;
        }

        double h = (upper - lower) / numOfSubIntervals;
        double sum = polynomial.evaluate(lower) + polynomial.evaluate(upper);

        for (int i = 1; i < numOfSubIntervals; i += 2) {
            double x = lower + i * h;
            sum += 4 * polynomial.evaluate(x);
        }

        for (int i = 2; i < numOfSubIntervals; i += 2) {
            double x = lower + i * h;
            sum += 2 * polynomial.evaluate(x);
        }

        return (h / 3.0) * sum;
    }
}
