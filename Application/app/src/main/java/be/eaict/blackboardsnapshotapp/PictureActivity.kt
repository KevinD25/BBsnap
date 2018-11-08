package be.eaict.blackboardsnapshotapp

import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.customlistlayout.*

class PictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        txtDatum.typeface = type
        txtUur.typeface = type

    }
}
