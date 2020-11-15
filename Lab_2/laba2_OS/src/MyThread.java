
import java.util.Random;
public class MyThread {
    Random random = new Random();
    private int number;
    private int time;
    private int workTime = random.nextInt(4) + 1;
    private int numProcess;
    public MyThread(int number, int time, int numProcess){
        this.number = number;
        this.time = time;
        this.numProcess = numProcess;
    }
    public boolean start(){
        System.out.print("       Thread " + number + " process "+ numProcess + " started" + '\n');

        // выполнение...

        if(workTime > time){
            System.out.print("       Thread " + number + " stopped" + " Time spent: " + time + '\n');
            workTime -= time;
            return true;
        }
        System.out.print("       Thread " + number + " done"  + " Time spent: " + workTime + '\n');
        return false;
    }
}
