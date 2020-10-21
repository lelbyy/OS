
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
        System.out.print("       Поток " + number + " начинает выполнение" + '\n');

        // выполнение...

        if(workTime > time){
            System.out.print("       Поток " + number + " приостановлен" + " Времени затрачено: " + time + '\n');
            workTime -= time;
            return true;
        }
        System.out.print("       Поток " + number + " выполнен"  + " Времени затрачено: " + workTime + '\n');
        return false;
    }

    public int getWorkTime(){
        if(workTime > time){
            return 2;
        }
        return workTime;
    }
}
