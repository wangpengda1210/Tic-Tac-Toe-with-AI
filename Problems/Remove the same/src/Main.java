class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        int i = 0;
        while (i < arrayList.size()) {
            if (linkedList.get(i).equals(arrayList.get(i))) {
                linkedList.remove(i);
                arrayList.remove(i);
                i--;
            }
            i++;
        }
    }
}