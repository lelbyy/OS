public class Disk {
    private  FileSegment[] memoryDisk = new FileSegment[300];

    public void setSegment(int index,FileSegment data){
        memoryDisk[index] = data;
    }
    public FileSegment getSegment(int index){
        return memoryDisk[index];
    }
    public int getLength(){
        return memoryDisk.length;
    }
}
