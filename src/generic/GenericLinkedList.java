package generic;

public class GenericLinkedList<T> {

    private Node<T> firstNode = null;
    private Node<T> current = null;
    private int size = 0;

    public GenericLinkedList() {
        current = new Node<>(null);
    }

    public void add(T element) {
        if (firstNode == null) {
            firstNode = new Node<>(element);
            current = firstNode;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node(element));
        }
        size++;
    }

    public T next() {
        if (hasNext()) {
            current = current.getNext();
            return current.getValue();
        } else {
            System.out.println("There is no next");
            return null;
        }
    }

    public boolean hasNext() {
        return current.getNext() != null;
    }

    public T get(int index) {
        if (index < size) {
            current = firstNode;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return (T) current.getValue();
        }else {
            throw new IndexOutOfBoundsException("index bigger than size");
        }
    }

    public int getSize() {
        return size;
    }
}