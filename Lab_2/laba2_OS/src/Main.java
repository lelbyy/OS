import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Core core = new Core();
        Random random = new Random();
        int Processes = random.nextInt(10) + 1;

        core.createProcess(Processes);
        core.planning();


    }
}
