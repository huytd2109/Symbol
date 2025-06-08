package hus.oop.integration;

import java.util.ArrayList;
import java.util.List;

public class MyListPolynomial extends MyAbstractPolynomial {
    private List<Double> coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyListPolynomial() {
        this.coefficients = new ArrayList<>();
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= coefficients.size()) {
            return 0;
        }
        return coefficients.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[coefficients.size()];
        for (int i = 0; i < coefficients.size(); i++) {
            result[i] = coefficients.get(i);
        }
        return result;
    }

    @Override
    public MyListPolynomial append(double coefficient) {
        coefficients.add(coefficient);
        return this;
    }

    @Override
    public MyListPolynomial add(double coefficient, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }

        while (coefficients.size() < index) {
            coefficients.add(0.0);
        }

        if (index == coefficients.size()) {
            coefficients.add(coefficient);
        } else {
            coefficients.add(index, coefficient);
        }
        return this;
    }

    @Override
    public MyListPolynomial set(double coefficient, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }

        while (coefficients.size() <= index) {
            coefficients.add(0.0);
        }

        coefficients.set(index, coefficient);
        return this;
    }

    @Override
    public int degree() {
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            if (coefficients.get(i) != 0) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        double power = 1;

        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * power;
            power *= x;
        }

        return result;
    }

    @Override
    public MyListPolynomial derivative() {
        if (coefficients.size() <= 1) {
            return new MyListPolynomial();
        }

        MyListPolynomial result = new MyListPolynomial();
        for (int i = 1; i < coefficients.size(); i++) {
            result.append(coefficients.get(i) * i);
        }

        return result;
    }

    @Override
    public MyListPolynomial plus(MyPolynomial right) {
        double[] rightCoeffs = right.coefficients();
        MyListPolynomial result = new MyListPolynomial();

        int maxSize = Math.max(coefficients.size(), rightCoeffs.length);
        for (int i = 0; i < maxSize; i++) {
            double sum = 0;
            if (i < coefficients.size()) {
                sum += coefficients.get(i);
            }
            if (i < rightCoeffs.length) {
                sum += rightCoeffs[i];
            }
            result.append(sum);
        }

        return result;
    }

    @Override
    public MyListPolynomial minus(MyPolynomial right) {
        double[] rightCoeffs = right.coefficients();
        MyListPolynomial result = new MyListPolynomial();

        int maxSize = Math.max(coefficients.size(), rightCoeffs.length);
        for (int i = 0; i < maxSize; i++) {
            double diff = 0;
            if (i < coefficients.size()) {
                diff += coefficients.get(i);
            }
            if (i < rightCoeffs.length) {
                diff -= rightCoeffs[i];
            }
            result.append(diff);
        }

        return result;
    }

    @Override
    public MyListPolynomial multiply(MyPolynomial right) {
        double[] rightCoeffs = right.coefficients();
        MyListPolynomial result = new MyListPolynomial();

        for (int i = 0; i < coefficients.size() + rightCoeffs.length - 1; i++) {
            result.append(0.0);
        }

        for (int i = 0; i < coefficients.size(); i++) {
            for (int j = 0; j < rightCoeffs.length; j++) {
                double product = coefficients.get(i) * rightCoeffs[j];
                double currentValue = result.coefficient(i + j);
                result.set(currentValue + product, i + j);
            }
        }

        return result;
    }
}
