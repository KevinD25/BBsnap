package be.eaict.blackboardsnapshotapp

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import be.eaict.blackboardsnapshotapp.Objects.Foto
import be.eaict.blackboardsnapshotapp.Objects.Repository
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_snap.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import org.json.JSONObject


class SnapActivity : AppCompatActivity() {

    private lateinit var result: String
    private var userID: Int = 0
    private var photoTaken: Boolean = false
    val repo = Repository()
    lateinit var data : DataFile
    lateinit var fotos : ArrayList<Foto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)
        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        btnSeePictures.typeface = type
        getPhotos()
        data = repo.getPhotos()
        fotos = data.fotos
    }

    fun OpenPictures(view:View){
        val intent = Intent(this, PictureActivity::class.java)
        intent.putExtra("fotos", fotos) //TODO PUT FOTOS IN BUNDLE TO PASS THROUGH
        startActivity(intent)
    }

    fun getPhotos(){
        //repo.getInstance()
        repo.callAPI()
    }



    fun sendSnapCommand(view: View){
        userID = 1
        photoTaken = false

        val json = JSONObject()
        json.put("studnr", userID)
        val jsonbody = json.toString()

        val me = this

        doAsync{
            val request = "http://brabo2.ddns.net:555/takephoto/".httpPost().body(jsonbody)
            request.headers["Content-Type"] = "application/json"
            request.response { request, response, result ->
                Log.i("Result", response.toString())
                if(response.responseMessage == "OK"){
                    photoTaken = true
                    Toast.makeText(me, "Photo taken...", Toast.LENGTH_SHORT).show()
                    getPhotos()
                }
                if (photoTaken){
                    //btnSnap.isEnabled = false
                    Handler().postDelayed({   //TODO Timer werkt niet
                        btnSnap.isEnabled = true
                    }, 3000)
                }
            }

            uiThread{

            }
        }
    }

}
