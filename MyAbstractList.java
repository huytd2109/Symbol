package hus.oop.statistics;

public abstract class MyAbstractList implements MyList {
    /**
     * Mô tả dữ liệu của list.
     * @return mô tả list theo định dạng [a1 a2 a3 ... an]
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        MyIterator iterator = iterator(0);
        boolean first = true;

        while (iterator.hasNext()) {
            if (!first) {
                sb.append(" ");
            } else {
                first = false;
            }
            sb.append(iterator.next());
        }

        sb.append("]");
        return sb.toString();
    }
}
