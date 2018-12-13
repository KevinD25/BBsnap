package be.eaict.blackboardsnapshotapp.Objects

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.google.gson.GsonBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL

class API {
    private lateinit var result: String
    private lateinit var json: JsonObject
    private lateinit var dataFile : DataFile

    fun callAPI() : DataFile{  //Asynchronous
        val leeg:List<Foto> = emptyList()
        dataFile.fotos = leeg


        doAsync{
            result = URL("http://brabo2.ddns.net:555/photo").readText()

            uiThread{
                Log.d("Request", result)

                val parser: Parser = Parser()
                val stringBuilder: StringBuilder = StringBuilder(result)
                json = parser.parse(stringBuilder) as JsonObject

                val gson = GsonBuilder().create()
                dataFile = gson.fromJson(result, DataFile::class.java)
                Log.d("JSONDATA", json.toString())
                println(dataFile.toString())
                println(dataFile.fotos[0].camera.ip)
            }
        }

        return dataFile
    }


}