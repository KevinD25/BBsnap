package be.eaict.blackboardsnapshotapp.Objects

import android.provider.ContactsContract
import java.util.ArrayList

class Repository : IRepository {
    private lateinit var repo: IRepository
    val api = API()
    private lateinit var photos : DataFile

    fun getInstance(): IRepository {
        if (repo == null) {
            repo = Repository()
        }
        return repo
    }

    override fun callAPI(){
        photos = api.callAPI()
    }

    override fun getPhotos(): DataFile {

        return photos
    }
}
