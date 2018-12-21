package be.eaict.blackboardsnapshotapp.Objects;

public class Repository implements IRepository   {

    private static IRepository repo = null;
    protected DataFile photos;

    public static IRepository getInstance(){
        if(repo == null){
            repo = new Repository();
        }
        return repo;
    }

    public void setPhotos(DataFile data){
        this.photos = data;
    }

    public DataFile getPhotos(){

        return photos;
    }
}
