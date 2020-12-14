import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Files implements Serializable{
    private String nameFile;
    private int countSegment = 0;
    private int firstSegmentIndexInDisk = -1;
    private Vector<Integer> segmentsVector = new Vector<>();

    public void setSegmentsVector(Vector<Integer> segmentsVector) {
        this.segmentsVector = segmentsVector;
    }

    public void addInSegmentVector(int index){
        segmentsVector.add(index);
    }


    public Files(String nameFile){
        this.nameFile = nameFile;
    }

    public void setFirstSegmentIndexInDisk(int firstSegmentIndexInDisk) {
        this.firstSegmentIndexInDisk = firstSegmentIndexInDisk;
    }

    public FileSegment getSegment(int indexSegment){
        if(firstSegmentIndexInDisk == -1){
            return null;
        }
        FileSegment fs;
        fs = Disk.memoryDisk[firstSegmentIndexInDisk];
        while(fs.getNextIndexMemory() != -1){
            fs = Disk.memoryDisk[fs.getNextIndexMemory()];
        }
        return fs;
    }

    public Vector<Integer> getSegmentsVector() {
        return segmentsVector;
    }

    public int getFirstSegmentIndexInDisk() {
        return firstSegmentIndexInDisk;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void createSegment(){
        FileSegment fs;

        if(Disk.memoryDisk[Disk.memoryDisk.length -1] != null){
            return;
        }
        if(firstSegmentIndexInDisk == -1){
            firstSegmentIndexInDisk = freeSegmentSearch();
            segmentsVector.add(freeSegmentSearch());
            fs = new FileSegment(freeSegmentSearch(), countSegment);
            countSegment++;
            Disk.memoryDisk[freeSegmentSearch()] = fs;
            return;
        }

        fs = Disk.memoryDisk[firstSegmentIndexInDisk];
        while(fs.getNextIndexMemory() != -1){
            fs = Disk.memoryDisk[fs.getNextIndexMemory()];
        }

        fs.setNextIndexMemory(freeSegmentSearch());


        fs = new FileSegment(freeSegmentSearch(), countSegment);
        countSegment++;
        segmentsVector.add(freeSegmentSearch());
        Disk.memoryDisk[freeSegmentSearch()] = fs;

    }

    private int freeSegmentSearch(){
        for(int i = 0; i < Disk.memoryDisk.length; i++){
            if(Disk.memoryDisk[i] == null){
                return i;
            }
        }
        return -1;
    }

    public void delAllSegments(){
        if(firstSegmentIndexInDisk == -1){
            return;
        }
        FileSegment fs;
        fs = Disk.memoryDisk[firstSegmentIndexInDisk];
        while(fs != null){
            int cash = fs.getIndexInMemory();
            System.out.println(cash);
            if(fs.getNextIndexMemory() == -1){
                Disk.memoryDisk[cash] = null;
                return;
            }
            fs = Disk.memoryDisk[fs.getNextIndexMemory()];

            Disk.memoryDisk[cash] = null;
        }
    }

    public Files clone2() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);


        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);

        Files cloneFile =(Files) ois.readObject();

        cloneFile.getSegmentsVector().clear();

        boolean firstSegmentBool = true;
        int firstSegment = -1;
        for(int i = 0; i < countSegment; i++){
            FileSegment cloneFileSegment;

            baos = new ByteArrayOutputStream();
            ous = new ObjectOutputStream(baos);


            ous.writeObject((FileSegment)getSegment(i));
            ous.close();
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);

            cloneFileSegment =(FileSegment) ois.readObject();

            cloneFileSegment.setIndexInMemory(firstSegment);
            cloneFileSegment.setNextIndexMemory(-1);

            if(firstSegmentBool){
                firstSegment = freeSegmentSearch();
                firstSegmentBool = false;
                cloneFile.setFirstSegmentIndexInDisk(firstSegment);
            }

            cloneFile.addInSegmentVector(freeSegmentSearch());
            Disk.memoryDisk[freeSegmentSearch()] = cloneFileSegment;

            if(i < countSegment-1){
                cloneFileSegment.setNextIndexMemory(freeSegmentSearch());
            }
        }

        return  cloneFile;
    }


    @Override
    public String toString(){
        return "Файл " + nameFile;
    }

}
