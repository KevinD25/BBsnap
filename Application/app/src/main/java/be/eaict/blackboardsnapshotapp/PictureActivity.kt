package be.eaict.blackboardsnapshotapp

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import be.eaict.blackboardsnapshotapp.Adapters.MyAdapter
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import be.eaict.blackboardsnapshotapp.Objects.Foto
import be.eaict.blackboardsnapshotapp.Objects.Repository
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.android.synthetic.main.customlistlayout.*
import kotlinx.android.synthetic.main.detailpopup.*
import org.jetbrains.anko.doAsync
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class PictureActivity : AppCompatActivity() {

    lateinit var fotos: ArrayList<Foto>
    lateinit var data: DataFile
    lateinit var context: Context
    lateinit var activity: Activity
    private lateinit var downloadPage: String
    var writeAccess = false
    lateinit var customView : View
    var optionList: ArrayList<String> = arrayListOf()

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

        spinnerSetup()

    }

    fun spinnerSetup(){
        val startInput = arrayOf<String>("Klas", "Lokaal", "Vak")
        val spinner = findViewById(R.id.filterMain) as Spinner
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, startInput)
        spinner.adapter = adapter

        filterMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                onChangeChoice()
            }
        }
    }

    fun onChangeChoice(){
        val choice : String = filterMain.selectedItem.toString()
        var add : Boolean = false

        optionList.clear()

        when(choice){
            "Klas" -> for(item in fotos){
                add = true
                for(listItem in optionList){
                    if(listItem == item.les.klas.naam) add = false
                }
                if(add) optionList.add(item.les.klas.naam)
            }
            "Lokaal" ->    for(item in fotos){
                add = true
                for(listItem in optionList){
                    if(listItem == item.les.lokaal.naam) add = false
                }
                if(add) optionList.add(item.les.lokaal.naam)
            }
            "Vak" -> for(item in fotos){
                add = true
                for(listItem in optionList){
                    if(listItem == item.les.vak.naam) add = false
                }
                if(add) optionList.add(item.les.vak.naam)
            }
        }

        val spinner = findViewById(R.id.filterSub) as Spinner
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, optionList)
        spinner.adapter = adapter
    }


    fun createPopUp(view: View, position:Int) {


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

        customView = popupWindow.contentView ?: return


        val txtvak: TextView = customView.findViewById(R.id.txtVak)
        val txtprof: TextView = customView.findViewById(R.id.txtProf)
        val txtlokaal : TextView = customView.findViewById(R.id.txtLokaal)
        val txtklas: TextView = customView.findViewById(R.id.txtKlas)
        val txtdatum : TextView  = customView.findViewById(R.id.txtDatumPop)
        val txttijd : TextView = customView.findViewById(R.id.txtUurPop)

        val vak = fotos.get(position).les.vak.naam
        val prof= fotos.get(position).les.vak.prof.naam
        val lokaal = fotos.get(position).les.lokaal.naam
        val klas = fotos.get(position).les.klas.naam
        val len = fotos.get(position).naam.length
        var datum = fotos.get(position).naam.substring(0, 6)
        var tijd = fotos.get(position).naam.substring(7, len - 4)
        val year = datum.substring(0,2)
        val month  = datum.substring(2,4)
        val day = datum.substring(4,6)
        val hour = tijd.substring(0,2)
        val minutes = tijd.substring(2,4)
        datum = day + "/" + month + "/" + year
        tijd = hour + ":" + minutes


        txtvak.setText(vak)
        txtprof.setText(prof)
        txtlokaal.setText(lokaal)
        txtklas.setText(klas)
        txtdatum.setText(datum)
        txttijd.setText(tijd)


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

    fun onClickInfo(v: View) {
        val parentRow = v.parent as View
        val listView = parentRow.parent as ListView
        val position = listView.getPositionForView(parentRow)

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutfile: View = inflater.inflate(R.layout.detailpopup, null)
        val view2 = layoutfile

        createPopUp(view2, position)
    }

    fun onClickImage(view: View) {
        val parentRow = view.parent as View
        val listView = parentRow.parent as ListView
        val position = listView.getPositionForView(parentRow)
        val intent = Intent(this, PictureviewActivity::class.java)
        intent.putExtra("position", position)
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

