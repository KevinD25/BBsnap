package be.eaict.blackboardsnapshotapp

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_snap.*
import android.view.View.OnTouchListener



class SnapActivity : AppCompatActivity() {

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


    fun SendSnapCommand(){

    }

}
