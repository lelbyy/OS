import java.util.ArrayList;
import java.util.Arrays;

public class MemoryDistributor {
    MemoryDistributor() {
        physicalMemory = new PageTable(memoryCapacity / pageCapacity);
        for (int i = 0; i < Main.getRandomNumber(5, 7); i++) {
            processList.add(new Process(i));
        }
    }
    public void swapping(Process process) {
        System.out.println("\n" + "Implementation of swapping");
        Page[] pm = physicalMemory.getPageTable();
        for (int i = 0; i < process.getVirtualMemory().size(); i++) {
            Page trashPage = pm[i];
            if (trashPage != null) {
                System.out.println("\n" + "Recording on disk" + trashPage.getID() + " process " + trashPage.getProcessID());
            }
            pm[i] = process.getVirtualMemory().get(i);
            System.out.println("Adding page " + pm[i].getID() + " process " + pm[i].getProcessID());
        }
    }

    public final PageTable physicalMemory;
    private static final int memoryCapacity = 256;
    private static final int pageCapacity = 32;
    private final ArrayList<Process> processList = new ArrayList<>();

    public void work() {
        for (int loop = 1; loop < 20; loop++) {
            for (Process process : processList) {
                if(loop%2 == 1){
                    swapping(process);
                    continue;
                }
                int index = Main.getRandomNumber(0, process.getVirtualMemory().size() - 1);
                Page usefulPage = process.getVirtualMemory().get(index);
                int actionType = Main.getRandomNumber(0, 1);
                Page[] pm = physicalMemory.getPageTable();

                if (usefulPage.isInPhysicalMemory()) {
                    if (actionType == 0) {
                        usefulPage.setR(1);
                        System.out.println("Page: " + usefulPage.getID() + " Process: " + process.getID() +
                                " PhysicalMemory: " + usefulPage.getPhysicalPageID() + " \n" + "Appeal");
                    } else {
                        usefulPage.setM(1);
                        System.out.println("Page: " + usefulPage.getID() + " Process: " + process.getID() +
                                " PhysicalMemory: " + usefulPage.getPhysicalPageID() + " Modification");
                    }
                    //если страницы нет, но в физической памяти есть место
                } else if (pm[physicalMemory.getMaxPages() - 1] == null) {
                    for (int i = 0; i < physicalMemory.getMaxPages(); i++) {
                        if (pm[i] == null) {
                            usefulPage.setInPhysicalMemory(true);
                            usefulPage.setPhysicalPageID(i);
                            pm[i] = usefulPage;
                            System.out.println("Page " + usefulPage.getID() + " Process: " + process.getID() + " Now in the physical memory: " + i);
                            break;
                        }
                    }
                } else {
                    System.out.println("\n\n" + "Page break in progress ...");
                    Arrays.sort(pm);
                    for (Page page : pm) {
                        System.out.println("PhysicalMemory: " + page.getPhysicalPageID() + " Process: " + page.getProcessID() + " Page: " + usefulPage.getID() + " Class: " + (page.getR() * 2 + page.getM()));
                    }
                    System.out.println(" " + pm[0].getPhysicalPageID());
                    usefulPage.setInPhysicalMemory(true);
                    usefulPage.setR(1);
                    usefulPage.setPhysicalPageID(pm[0].getPhysicalPageID());
                    pm[0].setInPhysicalMemory(false);
                    pm[0] = usefulPage;
                    System.out.println("Unloading the page " + usefulPage.getID() + " process " + process.getID());
                    System.out.println("Page break in progress ...");
                }
            }
            System.out.println("\n\n" + "Lower priority of all pages\n");
            for (Page page : physicalMemory.getPageTable()) {
                if (page != null) {
                    page.setR(0);
                    page.setM(0);
                }
            }
        }
    }
}
