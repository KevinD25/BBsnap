package be.eaict.blackboardsnapshotapp.Objects

interface IRepository {

    fun getPhotos(): DataFile
    fun callAPI()
}