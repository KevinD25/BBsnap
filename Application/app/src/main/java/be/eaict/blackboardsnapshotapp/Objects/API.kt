package be.eaict.blackboardsnapshotapp.Objects

import android.os.Handler
import android.util.Log
import android.view.View
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpPost
import com.google.gson.GsonBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class API {
    private lateinit var result: String
    private lateinit var json: JsonObject
    private lateinit var dataFile : DataFile
    private var userID: Int = 0
    private var photoTaken: Boolean = false


    fun callAPI(){  //Asynchronous

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
                Repository.getInstance().photos = dataFile
            }
        }
    }


    fun sendSnapCommand(){
        userID = 1
        photoTaken = false

        val json = JSONObject()
        json.put("studnr", userID)
        val jsonbody = json.toString()

        val me = this

        doAsync{
            val request = "http://brabo2.ddns.net:555/takephoto/".httpPost().body(jsonbody)
            request.headers["Content-Type"] = "application/json"
            request.response { request, response, result ->
                Log.i("Result", response.toString())
                if(response.responseMessage == "OK"){
                    photoTaken = true

                }
                if (photoTaken){
                    //btnSnap.isEnabled = false
                    Handler().postDelayed({   //TODO Timer werkt niet
                        //btnSnap.isEnabled = true
                    }, 3000)
                }
            }

            uiThread{

            }
        }
    }
}