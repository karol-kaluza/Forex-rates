package generic;

public class Main {
    public static void main(String[] args) {
        GenericLinkedList<String> listStrings = new GenericLinkedList();
        listStrings.add("aaa");
        listStrings.add("bbb");
        listStrings.add("ccc");
        listStrings.add("ddd");
        listStrings.add("eee");
        System.out.println("listStrings.get(0): " + listStrings.get(0));
        System.out.println("listStrings.hasNext: " + listStrings.hasNext());
        System.out.println("listStrings.next(): " + listStrings.next());
        System.out.println("listStrings.next(): " + listStrings.next());
        System.out.println("listStrings.get(3): " + listStrings.get(3));
        System.out.println("size: " + listStrings.getSize());

        System.out.println("------------------------------------------");

        GenericLinkedList<Integer> listIntegers = new GenericLinkedList();
        listIntegers.add(0);
        listIntegers.add(1);
        listIntegers.add(2);
        listIntegers.add(3);
        listIntegers.add(4);
        System.out.println("listIntegers.get(0): " + listIntegers.get(0));
        System.out.println("listIntegers.hasNext: " + listIntegers.hasNext());
        System.out.println("listIntegers.next(): " + listIntegers.next());
        System.out.println("listIntegers.next(): " + listIntegers.next());
        System.out.println("listIntegers.get(3): " + listIntegers.get(3));
        System.out.println("size: " + listIntegers.getSize());

    }
}