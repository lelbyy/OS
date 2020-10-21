import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class Process {
    private int quantityThread;
    private int number;
    private int time;
    private int allTime = 0;
    Process(int number, int time, int quantityThread){
        this.quantityThread = quantityThread;
        this.number = number;
        this.time = time;
    }

    private MyThread thread;
    private Queue<MyThread> threadQ = new LinkedList<>();

    public void createThread() {

        for(int i = 0; i < quantityThread; i++){
            thread = new MyThread(i, 2);
            threadQ.add(thread);
        }
    }

    public boolean start(){
        System.out.print("Процесс " + number + " начинает выполнение" + '\n');
        thread = threadQ.poll();
        while(allTime + thread.getWorkTime() <= time && thread != null){
            allTime += thread.getWorkTime();
            if(thread.start()){
                threadQ.add(thread);
            }

            thread = threadQ.poll();
            if(thread == null){
                break;
            }
        }
        if(thread != null) {
            threadQ.add(thread);
            if (allTime + thread.getWorkTime() > time) {

                System.out.print("Процесс " + number + " приостановлен." + " Времени затрачено: " + allTime + '\n' + '\n');
                allTime = 0;
                return true;
            }
        }
        System.out.print("Процесс " + number + " выполнен" + " Времени затрачено: " + allTime + '\n' + '\n');
        return false;
    }
}
