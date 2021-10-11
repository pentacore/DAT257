package model

import android.content.Context
import dat257.gyro.Jsonable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream

/**
 * @author Erik
 * @author Jonathan
 * @author Felix
 **/
@ExperimentalSerializationApi
@Serializable
data class Route(var coordinates: MutableList<Pair<String, Coordinate>>): Jsonable {

    override fun fromJson(applicationContext: Context, fileName: String): Route {
        val input = applicationContext.openFileInput(fileName)
        val route = Json.decodeFromStream(
            serializer(),
            input
        )
        input.close()
        return route
    }

    override fun toJson(applicationContext: Context, fileName: String) {
        val output = applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE)
        Json.encodeToStream(
            this,
            output
        )
        output.close()
    }
}