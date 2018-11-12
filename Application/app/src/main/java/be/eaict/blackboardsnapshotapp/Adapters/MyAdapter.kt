package be.eaict.blackboardsnapshotapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import be.eaict.blackboardsnapshotapp.Objects.Picture
import be.eaict.blackboardsnapshotapp.R
import org.w3c.dom.Text
import javax.sql.CommonDataSource

class MyAdapter(private val context: Context,
                /*private val dataSource: ArrayList<Picture>)*/private val dataSource: MutableList<String> ): BaseAdapter() {


    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


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

        val picture = getItem(position) as Picture

        DateTextView.text = "TestDatum"
        TimeTextView.text = "TestUur"


        return rowView
    }

}

