class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        ArrayList<String> tempList = (ArrayList<String>) arrayList.clone();
        arrayList.clear();
        arrayList.addAll(linkedList);

        linkedList.clear();
        linkedList.addAll(tempList);
    }
}