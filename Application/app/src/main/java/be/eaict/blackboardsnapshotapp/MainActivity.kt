package be.eaict.blackboardsnapshotapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Typeface
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.pm.ActivityInfo
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        txtUsername.typeface = type
        txtPassword.typeface = type
        txtSignIn.typeface = type
    }

    fun SignIn(view:View){
        val intent = Intent(this, SnapActivity::class.java)
        startActivity(intent)
    }
}
