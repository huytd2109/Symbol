package hus.oop.integration;

public abstract class MyAbstractPolynomial implements MyPolynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        double[] coeffs = coefficients();
        StringBuilder sb = new StringBuilder("[");

        boolean isFirst = true;
        for (int i = 0; i < coeffs.length; i++) {
            if (coeffs[i] == 0) {
                continue; // Skip zero coefficients
            }

            if (!isFirst) {
                sb.append(coeffs[i] > 0 ? " + " : " - ");
            } else if (coeffs[i] < 0) {
                sb.append("-");
            }

            double absCoeff = Math.abs(coeffs[i]);
            if (absCoeff != 1 || i == 0) {
                sb.append(absCoeff);
            }

            if (i > 0) {
                sb.append("x");
                if (i > 1) {
                    sb.append("^").append(i);
                }
            }

            isFirst = false;
        }

        if (isFirst) {
            sb.append("0");
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
    public double[] differentiate() {
            double[] coeffs = coefficients();

            if (coeffs.length <= 1) {
                return new double[]{0};
            }

            double[] derivativeCoeffs = new double[coeffs.length - 1];
            for (int i = 1; i < coeffs.length; i++) {
                derivativeCoeffs[i - 1] = coeffs[i] * i;
            }

            return derivativeCoeffs;
    }
}
