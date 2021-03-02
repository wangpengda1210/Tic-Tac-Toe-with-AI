class ListOperations {
    public static void changeHeadsAndTails(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        String linkedFirst = linkedList.getFirst();
        String linkedLast = linkedList.getLast();
        String arrayFirst = arrayList.get(0);
        String arrayLast = arrayList.get(arrayList.size() - 1);

        arrayList.set(0, linkedFirst);
        arrayList.set(arrayList.size() - 1, linkedLast);
        linkedList.removeFirst();
        linkedList.addFirst(arrayFirst);
        linkedList.removeLast();
        linkedList.addLast(arrayLast);
    }
}