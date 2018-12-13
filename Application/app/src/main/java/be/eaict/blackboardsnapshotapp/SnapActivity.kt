package be.eaict.blackboardsnapshotapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import be.eaict.blackboardsnapshotapp.Objects.API
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import be.eaict.blackboardsnapshotapp.Objects.Foto
import be.eaict.blackboardsnapshotapp.Objects.Repository
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_snap.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.Serializable


class SnapActivity : AppCompatActivity() {

    private val api = API()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)
        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        btnSeePictures.typeface = type
        api.callAPI()
    }

    fun OpenPictures(view: View) {
        val intent = Intent(this, PictureActivity::class.java)
        startActivity(intent)
    }


    fun sendSnapCommand(view: View) {
        api.sendSnapCommand()
    }


}
