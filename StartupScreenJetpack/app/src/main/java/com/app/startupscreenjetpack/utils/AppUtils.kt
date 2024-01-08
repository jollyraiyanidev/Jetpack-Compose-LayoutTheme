package com.app.startupscreenjetpack.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import okhttp3.ResponseBody
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class AppUtils {

    companion object {

        var isDialogOpen =false


        /**
         * This function is used to check Internet availability
         * call this method when you want to perform network operation
         */
        fun isNetworkAvailable(context: Context): Boolean {
            try {
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val nw      = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    //for other device how are able to connect with Ethernet
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    //for check internet over Bluetooth
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            } catch (e: Exception) {
            }
            return false
        }


        /**
         * This method used for hide the keyboard if showing
         */
        fun hideSoftKeyboard(activity: Activity) {
            try {
                if (activity.currentFocus != null && activity.currentFocus!!.windowToken != null) {
                    val inputMethodManager =
                        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }


        /**
         * This function is used to hide soft keyboard
         */
        fun hideKeyBoard(context: Activity?) {
            if (context != null) {
                val view = context.currentFocus
                if (view != null) {
                    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }


        /**
         * This method will use to get current date and time
         */
        @JvmStatic
        fun currentDate(): String {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        }

        @JvmStatic
        fun currentDate1(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }
       /* @JvmStatic
        fun beforeDate(): String {
            val date = LocalDate.now()
            val newDate: LocalDate = date.minusDays(4)
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }
*/
        @JvmStatic
        fun beforeDate(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(getDaysAgo(15))
        }

        private fun getDaysAgo(daysAgo: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

            return calendar.time
        }
        @JvmStatic
        fun currentTime(): String {
            return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        }

        fun dateConvert(date:String): String {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date.toLong())
        }

        @JvmStatic
        fun dateConvert1(date:String): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date.toLong())
        }


        fun currentDateWithoutFormat(): String {
            return SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(Date())
        }

        fun currentDateAndTimeWithoutFormat(): String {
            return SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
        }


        fun dateFormate(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        }


        fun getSlider(context: Context, activity: Class<*>): Any? {

            lateinit var jsonString: String
            try {
                jsonString = context.assets.open("slider.json")
                    .bufferedReader()
                    .use { it.readText() }
            } catch (ioException: IOException) {
                Log.i("ioException",ioException.toString())
            }
            return Gson().fromJson(jsonString, activity)
        }

        fun convertJsonToString(response:ResponseBody): String {

            val temp =response.string().replace(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>",
                ""
            ).replace("<string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "")

            return temp
        }

        fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
            val theta = lon1 - lon2
            var dist = sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(theta))
            dist = acos(dist)
            dist = rad2deg(dist)
            dist *= 60 * 1.1515
            dist *= 1.609344
            return dist
        }

        private fun deg2rad(deg: Double): Double {
            return deg * Math.PI / 180.0
        }

        private fun rad2deg(rad: Double): Double {
            return rad * 180.0 / Math.PI
        }

        fun String.decodeHex(): String {
            require(length % 2 == 0) {"Must have an even length"}
            val bytes= chunked(2)
                .map { it.toInt(16).toByte() }
                .toByteArray()
                //.toString(StandardCharsets.US_ASCII)  // Or whichever encoding your input uses

            for (i in bytes.indices) {
                if (bytes[i] < 0x20.toByte() || bytes[i] == 0x7F.toByte()) {
                    bytes[i] = 0x2E.toByte()
                }
            }
            val ret = String(bytes, StandardCharsets.US_ASCII)
            return ret

        }


         fun scaleImage(bitmap1: Bitmap): Bitmap? {
            val width = bitmap1.width
            val height = bitmap1.height
            // 设置想要的大小
            val newWidth = (width / 8 + 1) * 8
            // 计算缩放比例
            val scaleWidth = newWidth.toFloat() / width
            // 取得想要缩放的matrix参数
            val matrix = Matrix()
            matrix.postScale(scaleWidth, 1f)
            // 得到新的图片
            return Bitmap.createBitmap(bitmap1, 0, 0, width, height, matrix, true)
        }


//        @JvmStatic
//        fun merge(brand: String, model: String): String {
//            return "$brand $model"
//        }


        fun isValid(s: String): Boolean {
            val n = ".*[0-9].*"
            val a = ".*[A-Z].*"
            return s.matches(Regex(n)) && s.matches(Regex(a))
        }

        fun random(from: Int, to: Int) : Int {

            val random = Random()
            return random.nextInt(to - from) + from
        }

        fun getFinancialYearFromDate(date : String) : String{
            var fiscalYear = ""
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateObj = formattedDate.parse(date)
            val calendar = Calendar.getInstance()
            if (dateObj != null) {
                calendar.time = dateObj
            }

            println(""+calendar.get(Calendar.MONTH))

            fiscalYear = if((calendar.get(Calendar.MONTH) + 1) <= 3){
                ""+(calendar.get(Calendar.YEAR) - 1) + "-" + (calendar.get(Calendar.YEAR))
            }else{
                ""+(calendar.get(Calendar.YEAR)) + "-" + (calendar.get(Calendar.YEAR)+1)
            }
            return fiscalYear
        }


        // Message types sent from the BluetoothService Handler
        val MESSAGE_STATE_CHANGE = 1
        val MESSAGE_READ = 2
        val MESSAGE_WRITE = 3
        val MESSAGE_DEVICE_NAME = 4
        val MESSAGE_TOAST = 5
        var MESSAGE_TYPE_SENT = 0
        var MESSAGE_TYPE_RECEIVED = 1

        // Key names received from the BluetoothChatService Handler
        val DEVICE_NAME = "device_name"
        val TOAST = "toast"



    }


}