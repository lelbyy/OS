
import java.util.Random;
public class MyThread {
    Random random = new Random();
    private int number;
    private int time;
    private int workTime = random.nextInt(4) + 1;

    public MyThread(int number, int time){
        this.number = number;
        this.time = time;
    }

    public boolean start(){
        System.out.print("       Thread " + number + " started" + '\n');

        // выполнение...

        if(workTime > time){
            System.out.print("       Thread" + number + " stopped" + " Time spent: " + time + '\n');
            workTime -= time;
            return true;
        }
        System.out.print("       Thread " + number + " done"  + " Time spent: " + workTime + '\n');
        return false;
    }

    public int getWorkTime(){
        if(workTime > time){
            return 2;
        }
        return workTime;
    }
}
