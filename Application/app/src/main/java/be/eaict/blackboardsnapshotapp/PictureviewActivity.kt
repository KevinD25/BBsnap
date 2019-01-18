package be.eaict.blackboardsnapshotapp

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import be.eaict.blackboardsnapshotapp.Objects.Foto
import be.eaict.blackboardsnapshotapp.Objects.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pictureview.*
import java.util.*

class PictureviewActivity : AppCompatActivity() {

    lateinit var fotos: ArrayList<Foto>
    lateinit var data: DataFile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictureview)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        updateImage()
    }

    private fun updateImage(){
        /*val position = intent.getIntExtra("position", 0)
        data = Repository.getInstance().photos
        fotos = data.fotos
        val cameraID = fotos.get(position).camera.id
        val photoName = fotos.get(position).naam*/

        val cameraID = intent.getIntExtra("cameraID", 0)
        val photoName = intent.getStringExtra("photoName")
        Glide.with(this).load("http://brabo2.ddns.net:555/photo/getphoto/" + cameraID + "/" + photoName).into(BigImageView)
    }

}
