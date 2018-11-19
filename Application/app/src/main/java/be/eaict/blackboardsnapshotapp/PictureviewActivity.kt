package be.eaict.blackboardsnapshotapp

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class PictureviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictureview)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }
}
