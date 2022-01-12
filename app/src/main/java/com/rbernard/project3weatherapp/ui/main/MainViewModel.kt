package com.rbernard.project3weatherapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel : ViewModel() {

    private val API = "6461c949e027a06b23e0dacba919c303"
    private var temp : MutableLiveData<String> = MutableLiveData()
    private var date : MutableLiveData<String> = MutableLiveData()
    private var icon : MutableLiveData<String> = MutableLiveData()
    private var description : MutableLiveData<String> = MutableLiveData()


    //used for 2nd api call
    private var lon : Double = -83.14465
    private var lat : Double = 42.48948

    //create arrayList of MutableLiveData for forecast
    private var list: MutableLiveData<ArrayList<Items>> = MutableLiveData()
    private var listItems : ArrayList<Items> = ArrayList()


    //getters
    fun getTemp():MutableLiveData<String>{
        return temp
    }

    fun getDate():MutableLiveData<String>{
        return date
    }

    fun getDescription():MutableLiveData<String>{
        return description
    }

    fun getIcon():MutableLiveData<String>{
        return icon
    }

    fun getList():MutableLiveData<ArrayList<Items>>{
        return list
    }


    //queue functions
    fun current (city: String, queue:RequestQueue){
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&units=imperial&appid=$API"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> {response ->

            //create JSON OBJECT
            val obj = JSONObject (response)

            //get lat and lon
            val coord = obj.getJSONObject("coord")
            lon=coord.getDouble("lon")
            lat=coord.getDouble("lat")

            //get current weather info of a city
            val main = obj.getJSONObject("main")

            //format to no decimal places and F (for fahrenheit)
            temp.setValue("%.0f° F".format(main.getDouble("temp")))
            val toDate = obj.getLong("dt")
            //formats the date to preferred look/style
            date.setValue(SimpleDateFormat("EEE, MMMM dd hh:mm a", Locale.ENGLISH).format(Date(toDate*1000)))
            val weather = obj.getJSONArray("weather").getJSONObject(0)
            description.setValue(weather.getString("description"))
            icon.setValue("https://api.openweathermap.org/img/w/${weather.getString("icon")}.png")

        },
           object: Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError){
                    Log.e("e",volleyError.toString())
                    temp.value = ("err")
                }
           }//end ErrorResponseListener
        )

        //add the request to RequestQueue
        queue.add(stringRequest)

    }//end weather

    fun forecast (queue:RequestQueue){
        listItems.clear()
        val url ="https://api.openweathermap.org/data/2.5/onecall?lon=$lon&lat=$lat&exclude=current,minutely,hourly,alerts&units=imperial&appid=$API"
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->

            //create JSON object
            val obj = JSONObject(response)
            val myList = obj.getJSONArray("daily")

            //get 7 day daily forecast
            for(i in 0..6){

                val info =myList.getJSONObject(i)
                val toDate = info.getLong("dt")
                var date =(SimpleDateFormat("EEE, MMMM dd", Locale.ENGLISH).format(Date(toDate*1000)))
                var temp = info.getJSONObject("temp").getDouble("day")
                var icon = info.getJSONArray("weather").getJSONObject(0).getString("icon")
                var iconUrl = "https://api.openweathermap.org/img/w/$icon.png"

                 //add elements to the arrayList (Items) Date,Temp,Icon
                 listItems.add(Items(date,"%.0f° F".format(temp),iconUrl))
            }//end for

            //set mutable data to the list
            list.setValue(listItems)

        },
            object: Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError){
                    Log.e("e",volleyError.toString())
                }
            }//end ErrorResponseListener
        )
        //add the request to RequestQueue
        queue.add(stringRequest)
    }//end forecast

}//end MainViewModel