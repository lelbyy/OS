import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
public class Core {

    private Queue<Process> processesQ = new LinkedList<>();
    private Process processes;
    private int maxTime = 20;
    Random random = new Random();

    public void planning() {
        processes = processesQ.poll();
        while(processes != null){
            if(processes.start()){
                processesQ.add(processes);
            }
            processes = processesQ.poll();
        }
    }
    public void createProcess(int quantityProcess) {
        for(int i = 0; i < quantityProcess; i++){
            int quantityThread = random.nextInt(10) + 1;
            processes = new Process(i, maxTime, quantityThread);
            processes.createThread();
            processesQ.add(processes);
        }

    }
}
