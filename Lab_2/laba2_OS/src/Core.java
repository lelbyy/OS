import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class Core {

    private Queue<Process> processesQ = new LinkedList<>();
    private Process processes;
    private int maxTime = 20;
    Random random = new Random();


    private MyThread thread;
    private Queue<MyThread> threadQ = new LinkedList<>();

    public void planning() {
        thread = threadQ.poll();
        while(thread != null){
            if(thread.start()){
                threadQ.add(thread);
            }
            thread = threadQ.poll();
        }
    }

    public void createProcess(int quantityProcess) {
        for(int i = 0; i < quantityProcess; i++){
            int quantityThread = random.nextInt(10) + 1;
            processes = new Process(i, quantityThread);
            threadQ.addAll(processes.createThread());
            processesQ.add(processes);
        }

    }
}
