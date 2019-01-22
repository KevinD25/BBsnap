package be.eaict.blackboardsnapshotapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import be.eaict.blackboardsnapshotapp.Objects.API
import kotlinx.android.synthetic.main.activity_snap.*
import java.util.*
import kotlin.concurrent.schedule


class SnapActivity : AppCompatActivity() {

    private val api = API()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)
        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        btnSeePictures.typeface = type
        api.callAPI()
    }

    override fun onStart() {
        super.onStart()
        etxtStudNr.setText("")
    }

    override fun onResume() {
        super.onResume()
        etxtStudNr.setText("")
    }

    fun OpenPictures(view: View) {
        val intent = Intent(this, PictureActivity::class.java)
        startActivity(intent)
    }

    fun sendSnapCommand(view: View) {
        val studnr = etxtStudNr.text.trim().toString().toLowerCase()
        var num = studnr.substring(3, studnr.length)
        num.toIntOrNull()
        if (studnr.isNotEmpty() &&
                studnr.length == 7 &&
                studnr.substring(0, 3) == "s09" &&
                !num.isNullOrBlank()) {

            api.sendSnapCommand(studnr)

            Timer("APICall", false).schedule(4000) {
                api.callAPI()

            }

            etxtStudNr.setText("")
        }
        else{
            Toast.makeText(this, "Vul een correct studenten nummer in... \n (Format - S091234", Toast.LENGTH_SHORT).show()
        }

    }


}
