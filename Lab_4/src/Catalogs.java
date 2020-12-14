import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Catalogs implements Serializable {
    List<Object> listFiles = new LinkedList<>();
    Catalogs parentCatalog;
    String nameCatalog;
    public Catalogs(String nameCatalog, Catalogs parentCatalog){
        this.nameCatalog = nameCatalog;
        this.parentCatalog = parentCatalog;
    }

    public void setParentCatalog(Catalogs parentCatalog) {
        this.parentCatalog = parentCatalog;
    }

    private Vector<Integer> segmentsVector = new Vector<>();

    public Vector<Integer> getSegmentsVector() {
        segmentsVector.clear();
        for(int i = 0; i < listFiles.size(); i++){
            if(listFiles.get(i).getClass().getSimpleName().equals("Catalogs")){
                Catalogs catalog = (Catalogs)(listFiles.get(i));
                segmentsVector.addAll(catalog.getSegmentsVector());
            }

            if(listFiles.get(i).getClass().getSimpleName().equals("Files")){
                Files file = (Files)(listFiles.get(i));
                segmentsVector.addAll(file.getSegmentsVector());
            }
        }
        return segmentsVector;
    }

    public Object getLast(){
        return listFiles.get(listFiles.size() - 1);
    }

    public Catalogs getParentCatalog() {
        return parentCatalog;
    }

    public String getNameCatalog() {
        return nameCatalog;
    }

    public List getListFiles(){
        return listFiles;
    }

    public Catalogs clone2() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);


        ous.writeObject(this);
        ous.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);

        Catalogs catalogClone = (Catalogs) ois.readObject();

        catalogClone.setParentCatalog(null);

        for(int i = 0; i < catalogClone.getListFiles().size(); i++) {
            if (catalogClone.getListFiles().get(i).getClass().getSimpleName().equals("Files")) {
                Files files = (Files)catalogClone.getListFiles().get(i);
                Files cloneFile = files.clone2();
                catalogClone.getListFiles().set(i, cloneFile);
            }

            if (catalogClone.getListFiles().get(i).getClass().getSimpleName().equals("Catalogs")) {
                Catalogs catalogs = (Catalogs)catalogClone.getListFiles().get(i);
                catalogClone.setParentCatalog(null);
                catalogClone.getListFiles().set(i, catalogs.clone2());
            }
        }

        return catalogClone;
    }

    public void deleteCatalog(){
        for(int i = 0; i < listFiles.size(); i++){
            if(listFiles.get(i).getClass().getSimpleName().equals("Files")){
                Files files = (Files)listFiles.get(i);
                files.delAllSegments();
                listFiles.remove(i);
            }

            if(listFiles.get(i).getClass().getSimpleName().equals("Catalogs")){
                Catalogs catalogs = (Catalogs)listFiles.get(i);
                listFiles.remove(i);
                catalogs.deleteCatalog();
            }
        }
    }

    @Override
    public String toString(){
        return "Каталог " + nameCatalog;
    }
}
