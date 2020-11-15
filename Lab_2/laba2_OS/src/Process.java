import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class Process {
    private int quantityThread;
    private int number;
    Process(int number, int quantityThread){
        this.quantityThread = quantityThread;
        this.number = number;
    }


    public Queue createThread() {
        Queue<MyThread> threadQ = new LinkedList<>();
        MyThread thread;
        for(int i = 0; i < quantityThread; i++){
            thread = new MyThread(i, 2, number);
            threadQ.add(thread);
        }
        return threadQ;
    }

}
