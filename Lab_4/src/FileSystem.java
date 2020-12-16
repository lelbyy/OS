import java.util.List;
import java.util.Vector;

public class FileSystem {
    Catalogs rootCatalog = new Catalogs("root", null);
    Catalogs currentCatalog = rootCatalog;
    Object rememberObject;
    public FileSystem(){

    }

    public Object getRememberObject() {
        return rememberObject;
    }

    public void setRememberObject(Object rememberObject) {
        this.rememberObject = rememberObject;
    }

    public Catalogs getRootCatalog() {
        return rootCatalog;
    }

    public void openCatalog(int index) {
        List list = currentCatalog.getListFiles();
        if (list.get(index).getClass().getSimpleName().equals("Catalogs")) {
            Catalogs catalogs = (Catalogs) (list.get(index));
            currentCatalog = catalogs;
        }

    }

    public void deleteLinkToFile(String name){
        List list = currentCatalog.getListFiles();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getClass().getSimpleName().equals("Files")){
                Files file = (Files) list.get(i);
                if(file.getNameFile().equals(name)){
                    list.remove(i);
                    return;
                }
            }
        }
    }

    public void deleteLinkToCatalog(String name){
        List list = currentCatalog.getListFiles();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getClass().getSimpleName().equals("Catalogs")){
                Catalogs catalogs = (Catalogs) list.get(i);
                if(catalogs.getNameCatalog().equals(name)){
                    list.remove(i);
                    return;
                }
            }
        }
    }

    public Catalogs getCurrentCatalog() {
        return currentCatalog;
    }

    public void createCatalog(String name){
        List list = currentCatalog.getListFiles();
        Catalogs catalog = new Catalogs(name, currentCatalog);
        list.add(catalog);
    }

    public void createFile(String name){
        List list = currentCatalog.getListFiles();
        Files file = new Files(name);
        list.add(file);
    }

    public Files returnFile(String name){
        List list = currentCatalog.getListFiles();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getClass().getSimpleName().equals("Files")){
                Files file = (Files)list.get(i);
                if(file.getNameFile().equals(name)){
                    return file;
                }
            }
        }
        return null;
    }



    public void deleteFile(int index){
        List list = currentCatalog.getListFiles();
        if(list.get(index).getClass().getSimpleName().equals("Files")){
            Files file = (Files)list.get(index);
            file.delAllSegments();
            list.remove(index);
        }
    }

    public void deleteCatalog(int index){
        List list = currentCatalog.getListFiles();
        if(list.get(index).getClass().getSimpleName().equals("Catalogs")) {
            Catalogs catalogs = (Catalogs) list.get(index);
            list.remove(index);
            catalogs.deleteCatalog();
        }
    }

    public void returnToParentCatalog(){
        if(currentCatalog.getParentCatalog() != null){
            currentCatalog = currentCatalog.getParentCatalog();
        }
    }

    public void printCatalog(){
        List list = currentCatalog.getListFiles();
        System.out.println(list.toString());
    }

    public void enlargeFile(int index){
        List list = currentCatalog.getListFiles();
        if(list.get(index).getClass().getSimpleName().equals("Files")){
            Files file = (Files)(list.get(index));
            file.createSegment();
        }
    }

    public Vector getSegmentVector(){
        return currentCatalog.getSegmentsVector();
    }
}
