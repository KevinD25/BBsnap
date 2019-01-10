package be.eaict.blackboardsnapshotapp.Adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import be.eaict.blackboardsnapshotapp.Objects.DataFile
import be.eaict.blackboardsnapshotapp.Objects.Foto
import be.eaict.blackboardsnapshotapp.R
import com.bumptech.glide.Glide

class MyAdapter(private val context: Context,
                private val dataSource: List<Foto>/*private val dataSource: MutableList<String>*/) : BaseAdapter() {


    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.customlistlayout, parent, false)

        val DateTextView = rowView.findViewById(R.id.txtDatum) as TextView
        val TimeTextView = rowView.findViewById(R.id.txtUur) as TextView
        val PictureImage: ImageView = rowView.findViewById(R.id.imagePicture) as ImageView

        val len = dataSource.get(position).naam.length
        var datum = dataSource.get(position).naam.substring(0, 6)
        var tijd = dataSource.get(position).naam.substring(7, len - 4)
        val year = datum.substring(0,2)
        val month  = datum.substring(2,4)
        val day = datum.substring(4,6)
        val hour = tijd.substring(0,2)
        val minutes = tijd.substring(2,4)
        datum = day + "/" + month + "/" + year
        tijd = hour + ":" + minutes
        val cameraID = dataSource.get(position).camera.id
        val photoName = dataSource.get(position).naam

        DateTextView.text = datum
        TimeTextView.text = tijd
        val foto = BitmapFactory.decodeResource(context.resources,
                R.mipmap.placeholder)
        PictureImage.setImageBitmap(foto)

        Glide.with(context).load("http://brabo2.ddns.net:555/photo/getphoto/" + cameraID + "/" + photoName).into(PictureImage)


        /* val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        txtDatum.typeface = type
        txtUur.typeface = type*/

        return rowView
    }

}

