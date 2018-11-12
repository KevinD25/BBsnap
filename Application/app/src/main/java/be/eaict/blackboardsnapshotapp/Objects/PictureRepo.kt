package be.eaict.blackboardsnapshotapp.Objects

class PictureRepo : IPictureRepo {


    private var repo: IPictureRepo? = null

    fun getInstance(): IPictureRepo {
        if (repo == null) {
            repo = PictureRepo()
        }
        return repo as IPictureRepo
    }


    override fun getPictures(): List<Picture>{
        var Pictures : List<Picture> = emptyList()


        return Pictures
    }
}