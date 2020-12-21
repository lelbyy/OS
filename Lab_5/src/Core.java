import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Core {
    Random random = new Random();

    private Queue<Process> queueWithBlock = new LinkedList<Process>();
    private Queue<Process> queueWithoutBlock = new LinkedList<Process>();

    public void createProcess() {
        for (int i = 1; i <= random.nextInt(10) + 1; i++) {
            boolean workWithDevices = random.nextBoolean();
            int timeBeforeWorkWithDevices = random.nextInt(1001);
            int timeWorkWithDevices = random.nextInt(401);
            int timeAfterWorkWithDevices = random.nextInt(1001);
            Process processToQueueWithBlock = new Process(i, workWithDevices, timeBeforeWorkWithDevices, timeWorkWithDevices, timeAfterWorkWithDevices);
            queueWithBlock.add(processToQueueWithBlock);
            Process processToQueueWithoutBlock = new Process(i, workWithDevices, timeBeforeWorkWithDevices, timeWorkWithDevices, timeAfterWorkWithDevices);
            queueWithoutBlock.add(processToQueueWithoutBlock);
        }
    }

    public void ShowAllProcesses() {
        List<Process> listForPrint = (List<Process>) queueWithBlock;
        System.out.println("Мы имеем" + listForPrint.size() + " процессов:");
        for (int i = 0; i < listForPrint.size(); i++) {
            System.out.println("Процесс " + listForPrint.get(i).getID() + " требует " + listForPrint.get(i).getTotalTime() + " единиц времени на выполнение" +
                    " - данный процес " + listForPrint.get(i).getWorkWithDevices() + listForPrint.get(i).getTimeWorkWithDevicesToPrint());
        }
        System.out.println();
    }

    public void withBlock() {
        int totalWorkTime = 0;
        System.out.println("Начаало работаты с блокировками \n");
        Process process = queueWithBlock.poll();
        while (process != null) {
            String result = process.performProcessWithBlock();
            System.out.println(result + "\n");
            if (result.contains("из-за")) {
                totalWorkTime += process.getTimeWorkWithDevices();
                Process newProcess = queueWithBlock.peek();
                result = newProcess.performProcessWithBreaking(process.getTimeWorkWithDevices());
                process.setTimeWorkWithDevices();
                System.out.println(result + "\n");
                result = process.extensionPerformProcessWithBlock();
                System.out.println(result + "\n");
            }
            if (result.contains("приостановлен")) {
                queueWithBlock.add(process);
            }
            totalWorkTime += process.getWorkTime();
            process = queueWithBlock.poll();
        }
        System.out.println("Общее время затраченное на выполнение процессов " + totalWorkTime + " единиц времени \n");
    }

    public void withoutBlock() {
        int totalWorkTime = 0;
        System.out.println("Начало работы без блокировок \n");
        Process process = queueWithoutBlock.poll();
        while (process != null) {
            String result = process.performProcessWithoutBlock();
            System.out.println(result + "\n");
            if (result.contains("приостановлен")) {
                queueWithoutBlock.add(process);
            }
            totalWorkTime += process.getWorkTime();
            process = queueWithoutBlock.poll();
        }
        System.out.println("Общее время затраченное на выполнение процессов " + totalWorkTime + " единиц времени");
    }
}
