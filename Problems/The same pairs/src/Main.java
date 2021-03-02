class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        int count = 0;

        for (String key : map1.keySet()) {
            if (map2.containsKey(key) && map1.get(key).equals(map2.get(key))) {
                count++;
            }
        }

        System.out.println(count);
    }

}