package hus.oop.statistics;

import java.util.Random;

public class TestStatistics {
    private Statistics statistics;

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        /* TODO
           - Thực hiện từng hàm test, lưu kết quả chạy chương trình và file text được đặt tên
             là <TenSinhVien_MaSinhVien_Statistics>.txt (Ví dụ, NguyenVanA_123456_Statistics.txt).
           - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
             <TenSinhVien_MaSinhVien_Statistics>.zip (Ví dụ, NguyenVanA_123456_Statistics.zip),
             nộp lên classroom.
         */
        TestStatistics test = new TestStatistics(null);
        test.testMyArrayList();
        test.testMyLinkedList();

    }

    public void testMyArrayList() {
        /* TODO
           - Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu vào biến length.
           - Tạo một list kiểu MyArrayList, có các phần tử dữ liệu kiểu double được sinh ngẫu nhiên
             nằm trong đoạn [1, 20]. Tạo Statistics có dữ liệu là list dữ liệu vừa tạo, lưu vào statistics.
           - Sử dụng Statistics để tính các đại lượng thống kê cơ bản (max, min, kỳ vọng, phương sai, rank, sắp xếp, tìm kiếm).
             In ra terminal tập dữ liệu, tập dữ liệu được sắp xếp, các đại lượng thống kê và kết quả chức năng tìm kiếm.
         */
        Random random = new Random();
        int length = random.nextInt(21) + 30;

        MyArrayList arrayList = new MyArrayList();
        for (int i = 0; i < length; i++) {
            double value = random.nextDouble() * 19 + 1;
            arrayList.add(value);
        }

        statistics = new Statistics(arrayList);

        System.out.println("Original data: " + arrayList);

        MyList sortedList = arrayList.sortIncreasing();
        System.out.println("Sorted data: " + sortedList);

        System.out.println("Maximum value: " + statistics.max());
        System.out.println("Minimum value: " + statistics.min());
        System.out.println("Mean value: " + statistics.mean());
        System.out.println("Variance: " + statistics.variance());

        double[] ranks = statistics.rank();
        System.out.println("Ranks:");
        MyIterator iterator = arrayList.iterator(0);
        for (int i = 0; i < arrayList.size(); i++) {
            double value = (double) iterator.next();
            System.out.printf("Value: %.2f, Rank: %.0f\n", value, ranks[i]);
        }

        double searchValue;
        if (arrayList.size() > 0) {
            searchValue = (double) arrayList.iterator(arrayList.size() / 2).next();
            System.out.println("Searching for value: " + searchValue);
            int result = statistics.search(searchValue);
            System.out.println("Search result: " + (result != -1 ? "Found at index " + result : "Not found"));
        }
    }

    public void testMyLinkedList() {
        /* TODO
           - Sinh ngẫu nhiên một số tự nhiên nằm trong đoạn [30, 50], lưu vào biến length.
           - Tạo một list kiểu MyLinkedList, có các phần tử lưu dữ liệu kiểu double được sinh ngẫu nhiên
             nằm trong đoạn [1, 20]. Tạo Statistics có dữ liệu là list dữ liệu vừa tạo, lưu vào statistics.
           - Sử dụng Statistics để tính các đại lượng thống kê cơ bản (max, min, kỳ vọng, phương sai, rank, sắp xếp, tìm kiếm).
             In ra terminal tập dữ liệu, tập dữ liệu được sắp xếp, các đại lượng thống kê và kết quả chức năng tìm kiếm.
         */
        Random random = new Random();
        int length = random.nextInt(21) + 30;

        MyLinkedList linkedList = new MyLinkedList();
        for (int i = 0; i < length; i++) {
            double value = random.nextDouble() * 19 + 1;
            linkedList.add(value);
        }

        statistics = new Statistics(linkedList);

        System.out.println("Original data: " + linkedList);

        MyList sortedList = linkedList.sortIncreasing();
        System.out.println("Sorted data: " + sortedList);

        System.out.println("Maximum value: " + statistics.max());
        System.out.println("Minimum value: " + statistics.min());
        System.out.println("Mean value: " + statistics.mean());
        System.out.println("Variance: " + statistics.variance());

        double[] ranks = statistics.rank();
        System.out.println("Ranks:");
        MyIterator iterator = linkedList.iterator(0);
        for (int i = 0; i < linkedList.size(); i++) {
            double value = (double) iterator.next();
            System.out.printf("Value: %.2f, Rank: %.0f\n", value, ranks[i]);
        }

        double searchValue;
        if (linkedList.size() > 0) {
            searchValue = (double) linkedList.iterator(linkedList.size() / 2).next();
            System.out.println("Searching for value: " + searchValue);
            int result = statistics.search(searchValue);
            System.out.println("Search result: " + (result != -1 ? "Found at index " + result : "Not found"));
        }
    }
}
