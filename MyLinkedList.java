package hus.oop.statistics;

public class MyLinkedList extends MyAbstractList {
    private MyNode top;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        top = null;
    }

    @Override
    public int size() {
        int count = 0;
        MyNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public void add(double data) {
        if (top == null) {
            top = new MyNode(data);
            return;
        }

        MyNode current = top;
        while (current.next != null) {
            current = current.next;
        }

        MyNode newNode = new MyNode(data);
        current.next = newNode;
        newNode.previous = current;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        if (index == 0) {
            MyNode newNode = new MyNode(data);
            if (top != null) {
                newNode.next = top;
                top.previous = newNode;
            }
            top = newNode;
            return;
        }

        MyNode current = getNodeByIndex(index - 1);
        MyNode newNode = new MyNode(data);
        newNode.next = current.next;
        newNode.previous = current;

        if (current.next != null) {
            current.next.previous = newNode;
        }
        current.next = newNode;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        if (index == 0) {
            top = top.next;
            if (top != null) {
                top.previous = null;
            }
            return;
        }

        MyNode nodeToRemove = getNodeByIndex(index);
        nodeToRemove.previous.next = nodeToRemove.next;

        if (nodeToRemove.next != null) {
            nodeToRemove.next.previous = nodeToRemove.previous;
        }
    }

    @Override
    public MyLinkedList sortIncreasing() {
        MyLinkedList result = new MyLinkedList();

        if (top == null || top.next == null) {
            MyNode current = top;
            while (current != null) {
                result.add(current.data);
                current = current.next;
            }
            return result;
        }

        MyNode current = top;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }

        int n = result.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                double value1 = result.getNodeByIndex(j).data;
                double value2 = result.getNodeByIndex(j + 1).data;

                if (value1 > value2) {
                    result.getNodeByIndex(j).data = value2;
                    result.getNodeByIndex(j + 1).data = value1;
                }
            }
        }

        return result;
    }

    @Override
    public int binarySearch(double data) {
        MyNode current = top;
        int index = 0;

        while (current != null) {
            if (current.data == data) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    /**
     * Tạo iterator để cho phép duyệt qua các phần tử của list.
     * @return
     */
    @Override
    public MyIterator iterator(int start) {
        return new MyLinkedListIterator(start);
    }

    /**
     * Lấy node ở vị trí index.
     * @param index
     * @return
     */
    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private class MyLinkedListIterator implements MyIterator {
        /*
         * Vị trí hiện tại của iterator trong list.
         */
        private int currentPosition;
        private MyNode currentNode;

        /**
         * Khởi tạo cho iterator ở vị trí position trong MyLinkedList.
         * @param position
         */
        public MyLinkedListIterator(int position) {
            if (position < 0 || position > size()) {
                throw new IndexOutOfBoundsException("Invalid iterator position: " + position);
            }

            this.currentPosition = position;
            if (position == size()) {
                currentNode = null;
            } else {
                currentNode = getNodeByIndex(position);
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }

            double data = currentNode.data;
            currentNode = currentNode.next;
            currentPosition++;
            return data;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(--currentPosition);
            currentNode = currentPosition < size() ? getNodeByIndex(currentPosition) : null;
        }
    }
}
