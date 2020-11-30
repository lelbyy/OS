import java.util.Random;

public class Page {
    private int PageID;
    private int ProcessID;
    private boolean isInPhysicalMemory = false;

    public Page(int PageID,int ProcessID) {
        this.PageID = PageID;
        this.ProcessID = ProcessID;
        System.out.println("\tPage : " + this.PageID + " created;");
    }

    public void setApp() {
        Random random = new Random();
        this.isInPhysicalMemory = random.nextBoolean();
        System.out.println("\tPage " + this.PageID + " from process : " + ProcessID + " appeal set to : " + this.isInPhysicalMemory);
    }

    public boolean inInPhysicalMemory() {
        return this.isInPhysicalMemory;
    }

    public int getPageID() {
        return this.PageID;
    }

    public int getProcessID() {
        return this.ProcessID;
    }
}
