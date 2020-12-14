import java.io.Serializable;

public class FileSegment implements Serializable {
    private int indexInMemory;
    private int nextIndexMemory = -1;
    private int segmentNumber;
    private boolean select = false;
    public FileSegment(int indexInMemory, int segmentNumber){
        this.indexInMemory = indexInMemory;
        this.segmentNumber = segmentNumber;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getIndexInMemory(){
        return indexInMemory;
    }

    public void setIndexInMemory(int indexInMemory) {
        this.indexInMemory = indexInMemory;
    }

    public int getNextIndexMemory() {
        return nextIndexMemory;
    }

    public void setNextIndexMemory(int nextIndexMemory) {
        this.nextIndexMemory = nextIndexMemory;
    }
    @Override
    public String toString(){
        return "Сегмент " + segmentNumber;
    }
}