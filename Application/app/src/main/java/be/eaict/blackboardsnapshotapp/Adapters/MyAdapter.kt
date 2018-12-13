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

        val len: Int = dataSource.get(position).naam.length
        val datum: String = dataSource.get(position).naam.substring(0, 6)
        val tijd: String = dataSource.get(position).naam.substring(7, len - 4)


        DateTextView.text = datum
        TimeTextView.text = tijd
        val foto = BitmapFactory.decodeResource(context.resources,
                R.mipmap.joren)
        PictureImage.setImageBitmap(foto)
        //TODO GET ACTUAL FOTO FROM SERVER USING NAME AS PARAMETER IN CALL. JOREN FIXT INFO SHIT (GETPHOTO)


        /* val type = Typeface.createFromAsset(assets, "fonts/SigmarOne.ttf")
        txtDatum.typeface = type
        txtUur.typeface = type*/

        return rowView
    }

}

