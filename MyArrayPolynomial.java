package hus.oop.integration;

public class MyArrayPolynomial extends MyAbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficents;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayPolynomial() {
        this.coefficents = new double[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public double coefficient(int index) {
        if (index < 0 || index >= size) {
            return 0;
        }
        return coefficents[index];
    }

    @Override
    public double[] coefficients() {
        double[] result = new double[size];
        System.arraycopy(coefficents, 0, result, 0, size);
        return result;
    }

    @Override
    public MyArrayPolynomial append(double coefficient) {
        if (size >= coefficents.length) {
            allocateMore();
        }
        coefficents[size++] = coefficient;
        return this;
    }

    @Override
    public MyArrayPolynomial add(double coefficient, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }

        if (index >= coefficents.length) {
            while (coefficents.length <= index) {
                allocateMore();
            }
        }

        if (index >= size) {
            for (int i = size; i < index; i++) {
                coefficents[i] = 0;
            }
            coefficents[index] = coefficient;
            size = index + 1;
        } else {
            if (size >= coefficents.length) {
                allocateMore();
            }
            for (int i = size; i > index; i--) {
                coefficents[i] = coefficents[i - 1];
            }
            coefficents[index] = coefficient;
            size++;
        }
        return this;
    }

    @Override
    public MyArrayPolynomial set(double coefficient, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }

        if (index >= coefficents.length) {
            while (coefficents.length <= index) {
                allocateMore();
            }
        }

        if (index >= size) {
            // Fill gap with zeros
            for (int i = size; i < index; i++) {
                coefficents[i] = 0;
            }
            size = index + 1;
        }

        coefficents[index] = coefficient;
        return this;
    }

    @Override
    public int degree() {
        for (int i = size - 1; i >= 0; i--) {
            if (coefficents[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        double power = 1;

        for (int i = 0; i < size; i++) {
            result += coefficents[i] * power;
            power *= x;
        }

        return result;
    }

    @Override
    public MyArrayPolynomial derivative() {
        if (size <= 1) {
            return new MyArrayPolynomial();
        }

        MyArrayPolynomial result = new MyArrayPolynomial();
        for (int i = 1; i < size; i++) {
            result.append(coefficents[i] * i);
        }

        return result;
    }

    @Override
    public MyArrayPolynomial plus(MyPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        double[] rightCoeffs = right.coefficients();

        int maxSize = Math.max(size, rightCoeffs.length);
        for (int i = 0; i < maxSize; i++) {
            double sum = 0;
            if (i < size) {
                sum += coefficents[i];
            }
            if (i < rightCoeffs.length) {
                sum += rightCoeffs[i];
            }
            result.append(sum);
        }

        return result;
    }

    @Override
    public MyArrayPolynomial minus(MyPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        double[] rightCoeffs = right.coefficients();

        int maxSize = Math.max(size, rightCoeffs.length);
        for (int i = 0; i < maxSize; i++) {
            double diff = 0;
            if (i < size) {
                diff += coefficents[i];
            }
            if (i < rightCoeffs.length) {
                diff -= rightCoeffs[i];
            }
            result.append(diff);
        }

        return result;
    }

    @Override
    public MyArrayPolynomial multiply(MyPolynomial right) {
        MyArrayPolynomial result = new MyArrayPolynomial();
        double[] rightCoeffs = right.coefficients();

        for (int i = 0; i < size + rightCoeffs.length - 1; i++) {
            result.append(0);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < rightCoeffs.length; j++) {
                double product = coefficents[i] * rightCoeffs[j];
                result.coefficents[i + j] += product;
            }
        }

        return result;
    }

    /**
     * Tăng kích thước mảng lên gấp đôi để lưu đa thức khi cần thiết.
     */
    private void allocateMore() {
        double[] newCoefficients = new double[coefficents.length * 2];
        System.arraycopy(coefficents, 0, newCoefficients, 0, size);
        coefficents = newCoefficients;
    }
}
