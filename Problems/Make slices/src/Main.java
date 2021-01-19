class Apple {

    private String appleVariety;

    public Apple(String appleVariety) {
        this.appleVariety = appleVariety;
    }

    void cutApple() {

        // create local inner class Knife
        class Knife {
            void makeSlices() {
                System.out.println("Apple " + appleVariety + " is ready to be eaten!");
            }
        }
        // create method makeSlices()


        Knife knife = new Knife();
        knife.makeSlices();
    }

    public static void main(String[] args) {
        Apple apple = new Apple("Gala");
        apple.cutApple();
    }
}