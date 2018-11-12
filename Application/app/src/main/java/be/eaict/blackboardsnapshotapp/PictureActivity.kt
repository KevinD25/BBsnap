package be.eaict.blackboardsnapshotapp

import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import be.eaict.blackboardsnapshotapp.Adapters.MyAdapter
import be.eaict.blackboardsnapshotapp.Objects.PictureRepo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.android.synthetic.main.customlistlayout.*

class PictureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val pictureList : MutableList<String> = mutableListOf()
        pictureList.add(0, "item1")
        pictureList.add(1, "item2")
        pictureList.add(1, "item3")
        pictureList.add(1, "item4")


        val adapter = MyAdapter(this, pictureList)
        ListviewPictures.adapter = adapter

        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        txtDatum.typeface = type
        txtUur.typeface = type



    }
}
