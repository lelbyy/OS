public class Main {
    public static void main(String[] args) {
        Core core = new Core();
        core.createProcess();
        core.showProcesses();
        core.withBlock();
        core.withoutBlock();
    }
}
