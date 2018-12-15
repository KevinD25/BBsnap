package be.eaict.blackboardsnapshotapp

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Environment.*
import android.support.annotation.UiThread
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.webkit.DownloadListener
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.Toast
import be.eaict.blackboardsnapshotapp.Adapters.MyAdapter
import be.eaict.blackboardsnapshotapp.Objects.*
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.Fuel
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_picture.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.Okio
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.*
import java.lang.Exception
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class PictureActivity : AppCompatActivity() {

    lateinit var fotos: ArrayList<Foto>
    lateinit var data: DataFile
    lateinit var context: Context
    lateinit var activity: Activity
    private lateinit var downloadPage: String
    lateinit var downloadListener: DownloadListener
    var writeAccess = false

    /** Permission Request Code */
    private val PERMISSION_REQUEST_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        this.setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        /** Application Context and Main Activity */
        context = applicationContext
        activity = this

        data = Repository.getInstance().photos
        fotos = data.fotos

        val adapter = MyAdapter(this, fotos)
        ListviewPictures.adapter = adapter

        checkWriteAccess()
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

    fun onClickDownload(v: View) {
        val parentRow = v.parent as View
        val listView = parentRow.parent as ListView
        val position = listView.getPositionForView(parentRow)
        val cameraID: Int
        val photoName: String

        cameraID = fotos[position].camera.id
        photoName = fotos[position].naam

        downloadPage = "http://brabo2.ddns.net:555/photo/downloadphoto/" + cameraID + "/" + photoName
        downloadPage = "http://brabo2.ddns.net:555/photo/getphoto/" + cameraID + "/" + photoName


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        100)

            } else {
                saveImageToStorage(cameraID, photoName)
                Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show()
            }
        } else {
            saveImageToStorage(cameraID, photoName)
            Toast.makeText(this, "Image saved!", Toast.LENGTH_SHORT).show()
        }

    }

    fun saveImageToStorage(cameraID: Int, photoName: String) {


        val externalStorageState = Environment.getExternalStorageState()
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            val storageDirectory = Environment.getExternalStorageDirectory().toString()
            val directory = File(storageDirectory + "/DCIM/BBsnap")
            directory.mkdirs()
            val file = File(directory, photoName)
                val stream: OutputStream = FileOutputStream(file)
                //var drawable = Glide.with(context).load("http://brabo2.ddns.net:555/photo/getphoto/" + cameraID + "/" + photoName).submit()
                // var bitmap = (drawable as BitmapDrawable).bitmap
                val me = this
                doAsync {
                    val input: InputStream = URL(downloadPage).openStream()
                    var bitmap: Bitmap = BitmapFactory.decodeStream(input)

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                    stream.close()

                }

        } else {
            Toast.makeText(this, "Unable to access the storage", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun checkWriteAccess() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /**
             * Check for permission status.
             * */
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    val builder = AlertDialog.Builder(activity)
                    builder.setMessage("Required permission to write external storage to save downloaded file.")
                    builder.setTitle("Please Grant Write Permission")
                    builder.setPositiveButton("OK") { _, _ ->
                        ActivityCompat.requestPermissions(
                                activity,
                                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                PERMISSION_REQUEST_CODE
                        )
                    }
                    builder.setNeutralButton("Cancel", null)
                    val dialog = builder.create()
                    dialog.show()
                } else {
                    ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            PERMISSION_REQUEST_CODE
                    )
                }
            } else {
                /**
                 * Already have required permission.
                 * */
                writeAccess = false
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    writeAccess = true
                } else {
                    // Permission denied
                    writeAccess = false
                    Toast.makeText(context, "Permission Denied. This app will not work with right permission.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

