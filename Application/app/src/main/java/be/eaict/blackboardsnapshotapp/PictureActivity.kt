package be.eaict.blackboardsnapshotapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.Toast
import be.eaict.blackboardsnapshotapp.Adapters.MyAdapter
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import be.eaict.blackboardsnapshotapp.Objects.Foto
import be.eaict.blackboardsnapshotapp.Objects.Repository
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.android.synthetic.main.customlistlayout.*
import java.io.BufferedInputStream
import java.net.URL
import java.net.URLConnection


class PictureActivity : AppCompatActivity() {

    lateinit var fotos: ArrayList<Foto>
    lateinit var data: DataFile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        data = Repository.getInstance().photos
        fotos = data.fotos

        val adapter = MyAdapter(this, fotos)
        ListviewPictures.adapter = adapter
        btnDownload.setOnClickListener(myButtonClickListener)

    }

    fun createPopUp(view: View) {


        // Initialize a new instance of detailpopup window
        val popupWindow = PopupWindow(
                view, // Custom view to show in detailpopup window
                LinearLayout.LayoutParams.WRAP_CONTENT, // Width of detailpopup window
                LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )

        // Set an elevation for the detailpopup window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F
        }


        // If API level 23 or higher then execute the code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Create a new slide animation for detailpopup window enter transition
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            // Slide animation for detailpopup window exit transition
            val slideOut = Slide()
            slideOut.slideEdge = Gravity.RIGHT
            popupWindow.exitTransition = slideOut

        }






        popupWindow.isFocusable = true

        // Set a dismiss listener for detailpopup window
        popupWindow.setOnDismissListener {
            Toast.makeText(applicationContext, "Popup closed", Toast.LENGTH_SHORT).show()
            this.setRequestedOrientation(
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }

        // Finally, show the detailpopup window on app
        TransitionManager.beginDelayedTransition(root_layout)
        popupWindow.showAtLocation(
                root_layout, // Location to display detailpopup window
                Gravity.CENTER, // Exact position of layout to display detailpopup
                0, // X offset
                0 // Y offset
        )
    }

    fun onClickInfo(view: View) {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutfile: View = inflater.inflate(R.layout.detailpopup, null)
        val view2 = layoutfile
        createPopUp(view2)

    }

    fun onClickImage(view: View) {
        /*val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutfile : View = inflater.inflate(R.layout.imagepopup,null)
        val view2 : View = layoutfile

        createPopUp(view, view2)*/

        val intent = Intent(this, PictureviewActivity::class.java)
        startActivity(intent)

    }


    private val myButtonClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {
            val parentRow = v.parent as View
            val listView = parentRow.parent as ListView
            val position = listView.getPositionForView(parentRow)
            val cameraID: Int
            val photoName: String

            cameraID = fotos[position].camera.id
            photoName = fotos[position].naam

            URL("http://brabo2.ddns.net:555/photo/downloadphoto/" + cameraID + "/" + photoName).file
        }
    }

    fun downloadPhoto(){

    }
}
