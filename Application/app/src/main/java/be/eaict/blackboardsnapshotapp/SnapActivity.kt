package be.eaict.blackboardsnapshotapp

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_snap.*
import android.view.View.OnTouchListener
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import com.beust.klaxon.Json
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.json
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


class SnapActivity : AppCompatActivity() {

    private lateinit var result: String
    private var userID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)
        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        btnSeePictures.typeface = type
    }

    fun OpenPictures(view:View){
        val intent = Intent(this, PictureActivity::class.java)
        startActivity(intent)
    }


    fun sendSnapCommand(view: View){
        userID = 1


        val requestParams = ArrayList<Pair<String, String>>(1)
        requestParams.add(Pair("studnr", userID.toString()))


        val json = JSONObject()
        json.put("studnr", userID)


        doAsync{
            val (ignoredRequest, ignoredResponse, result) =
                    Fuel.post("http://brabo2.ddns.net:555/takephoto/")
                            .body(json.toString())
                            .responseString()


            result.fold(success = {
                println(it.toString())
            }, failure = {
                println(String(it.errorData))
            })

            uiThread{

            }
        }




    }

}
