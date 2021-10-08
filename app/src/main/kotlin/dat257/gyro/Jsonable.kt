package dat257.gyro

import android.content.Context

interface Jsonable {

    fun toJson(applicationContext: Context, fileName: String)

    fun fromJson(applicationContext: Context, fileName: String): Jsonable

}
