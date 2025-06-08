package hus.oop.statistics;

public class MyArrayList extends MyAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private double[] data;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayList() {
        data = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(double data) {
        if (size >= this.data.length) {
            allocateMore();
        }
        this.data[size] = data;
        size++;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size >= this.data.length) {
            allocateMore();
        }

        for (int i = size; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }

        this.data[index] = data;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;

    }

    @Override
    public MyArrayList sortIncreasing() {
        MyArrayList sortedList = new MyArrayList();
        for (int i = 0; i < size; i++) {
            sortedList.add(data[i]);
        }
        for (int i = 0; i < sortedList.size - 1; i++) {
            for (int j = i + 1; j < sortedList.size; j++) {
                if (sortedList.data[i] > sortedList.data[j]) {
                    double temp = sortedList.data[i];
                    sortedList.data[i] = sortedList.data[j];
                    sortedList.data[j] = temp;
                }
            }
        }
        return sortedList;
    }

    @Override
    public int binarySearch(double data) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (this.data[mid] == data) {
                return mid;
            } else if (this.data[mid] < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Tạo iterator để có thể duyệt qua các phần tử của list.
     * @return
     */
    @Override
    public MyIterator iterator(int start) {
        return new MyArrayListIterator(start);
    }

    /**
     * Cấp phát gấp đôi chỗ cho danh sách khi cần thiết.
     */
    private void allocateMore() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private class MyArrayListIterator implements MyIterator {
        /**
         * Vị trí hiện tại của iterator trong MyArrayList.
         */
        private int currentPosition;

        /**
         * Khởi tạo dữ liệu cho iterator tại vị trí position của list.
         */
        public MyArrayListIterator(int position) {
            if (position < 0 || position > size) {
                throw new IndexOutOfBoundsException("Invalid iterator position: " + position);
            }
            currentPosition = position;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements to iterate.");
            }
            return data[currentPosition++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--currentPosition);
        }
    }
}
