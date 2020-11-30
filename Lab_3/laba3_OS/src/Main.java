import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MemoryDistributor memoryManager = new MemoryDistributor();
        memoryManager.work();
    }

    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
