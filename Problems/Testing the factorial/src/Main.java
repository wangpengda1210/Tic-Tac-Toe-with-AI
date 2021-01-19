public long get(int x) {
    if (x < 0) {
        return -1;
    }

    if (x == 0) {
        return 1;
    }

    return x * get(x - 1);
}