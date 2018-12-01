package be.eaict.blackboardsnapshotapp

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import be.eaict.blackboardsnapshotapp.Adapters.MyAdapter
import kotlinx.android.synthetic.main.activity_picture.*
import okhttp3.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.net.URL

class PictureActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


        // Two ways of calling API
        // callAPI()
        // run("APILINK")

        val pictureList : MutableList<String> = mutableListOf()
        pictureList.add(0, "item1")
        pictureList.add(1, "item2")
        pictureList.add(1, "item3")
        pictureList.add(1, "item4")


        val adapter = MyAdapter(this, pictureList)
        ListviewPictures.adapter = adapter

    }

    fun createPopUp( view: View){


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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
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
            Toast.makeText(applicationContext,"Popup closed",Toast.LENGTH_SHORT).show()
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
        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutfile : View = inflater.inflate(R.layout.detailpopup,null)
        val view2 = layoutfile
        createPopUp(view2)

    }

    fun onClickImage(view:View) {
        /*val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutfile : View = inflater.inflate(R.layout.imagepopup,null)
        val view2 : View = layoutfile

        createPopUp(view, view2)*/

        val intent = Intent(this, PictureviewActivity::class.java)
        startActivity(intent)

    }

    fun callAPI(){  //Asynchronous
        doAsync{
            val result = URL("<api call>").readText()
            uiThread{
                Log.d("Request", result)
                longToast("Request performed")
            }
        }

    }

    fun run(url: String) {
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }
}
