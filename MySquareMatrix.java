package hus.oop.matrix;
import java.util.*;

public class MySquareMatrix {
    private int[][] data;
    private int size;

    /**
     * Hàm dựng, khởi tạo một ma trận có các phần tử được sinh ngẫu nhiên trong khoảng [1, 100]
     * @param size
     */
    public MySquareMatrix(int size) {
        initRandom(size);
    }

    /**
     * Phương thức khởi tạo ma trận, các phần tử của ma trận được sinh ngẫu nhiên trong đoạn [10, 90]
     * @param size
     */
    private void initRandom(int size) {
        data = new int[size][size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = (int)(Math.random() * 81) + 10;
            }
        }
    }

    /**
     * Lấy ra các phần tử trên đường chéo chính của ma trận.
     * @return đường chéo chính của ma trận.
     */
    public int[] principalDiagonal() {
        int[] diagonal = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            diagonal[i] = data[i][i];
        }
        return diagonal;
    }

    /**
     * Lấy ra các phần tử trên đường chéo phụ của ma trận.
     * @return đường chéo phụ của ma trận.
     */
    public int[] secondaryDiagonal() {
        int[] diagonal = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            diagonal[i] = data[i][data.length - 1 - i];
        }
        return diagonal;
    }

    /**
     * Phương thức lấy ra các số là số nguyên tố trong ma trận.
     * @return các số nguyên tố trong ma trận.
     */
    public int[] primes() {
        List<Integer> primes = new ArrayList<>();
        for (int[] row : data) {
            for (int num : row) {
                if (isPrime(num)) {
                    primes.add(num);
                }
            }
        }
        return primes.stream().mapToInt(i -> i).toArray();
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Sắp xếp các phần tử của ma trận theo thứ tự giảm dần.
     * @return ma trận có các phần tử là phần tử của ma trận ban đầu được sắp xếp theo thứ tự giảm dần.
     * Các phần tử được sắp xếp theo thứ tự từ trái sang phải ở mỗi hàng, và từ trên xuống dưới.
     */
    public MySquareMatrix getSortedMatrix() {
        int size = data.length;
        int[] flat = new int[size * size];
        int idx = 0;
        for (int[] row : data) {
            for (int val : row) {
                flat[idx++] = val;
            }
        }
        Arrays.sort(flat);
        for (int i = 0; i < flat.length / 2; i++) {
            int temp = flat[i];
            flat[i] = flat[flat.length - 1 - i];
            flat[flat.length - 1 - i] = temp;
        }

        MySquareMatrix sorted = new MySquareMatrix(size);
        idx = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sorted.data[i][j] = flat[idx++];
            }
        }
        return sorted;
    }

    /**
     * Lấy giá trị phần tử ở vị trí (row, col).
     * @param row
     * @param col
     * @return
     */
    public int get(int row, int col) {
        return data[row][col];
    }

    /**
     * Sửa giá trị phần tử ở vị trí (row, col) thành value.
     * @param row
     * @param col
     * @param value
     */
    public void set(int row, int col, int value) {
        data[row][col] = value;
    }

    /**
     * Cộng ma trận hiện tại với một ma trận khác.
     * @param that
     * @return ma trận mới là ma trận tổng của 2 ma trận.
     */
    public MySquareMatrix add(MySquareMatrix that) {
        int size = data.length;
        MySquareMatrix result = new MySquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.data[i][j] = this.data[i][j] + that.data[i][j];
            }
        }
        return result;
    }

    /**
     * Trừ ma trận hiện tại cho một ma trận khác.
     * @param that
     * @return ma trận mới là ma trận hiệu của ma trận hiện tại và ma trận truyền vào.
     */
    public MySquareMatrix minus(MySquareMatrix that) {
        int size = data.length;
        MySquareMatrix result = new MySquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.data[i][j] = this.data[i][j] - that.data[i][j];
            }
        }
        return result;
    }

    /**
     * Nhân ma trận hiện tại với một ma trận khác.
     * @param that
     * @return ma trận mới là ma trận nhân của ma trận hiện tại với ma trận truyền vào.
     */
    public MySquareMatrix multiply(MySquareMatrix that) {
        int size = data.length;
        MySquareMatrix result = new MySquareMatrix(size);
        for (int i = 0; i < size; i++) {
            Arrays.fill(result.data[i], 0); // reset random values
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result.data[i][j] += this.data[i][k] * that.data[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Nhân ma trận với một số vô hướng.
     * @param value
     * @return ma trận mới là ma trận hiện tại được nhân với một số vô hướng.
     */
    public MySquareMatrix scaled(int value) {
        int size = data.length;
        MySquareMatrix result = new MySquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.data[i][j] = this.data[i][j] * value;
            }
        }
        return result;
    }

    /**
     * Phương thức lấy ma trận chuyển vị.
     * @return ma trận mới là ma trận chuyển vị của ma trận hiện tại.
     */
    public MySquareMatrix transpose() {
        int size = data.length;
        MySquareMatrix result = new MySquareMatrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.data[i][j] = this.data[j][i];
            }
        }
        return result;
    }

    /**
     * Mô tả ma trận theo định dạng biểu diễn ma trận, ví dụ
     *   1 2 3
     *   4 5 6
     *   7 8 9
     * @return một chuỗi mô tả ma trận.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : data) {
            for (int val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
