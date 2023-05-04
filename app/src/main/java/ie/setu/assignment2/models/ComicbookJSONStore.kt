package ie.setu.assignment2.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.setu.assignment2.helpers.exists
import ie.setu.assignment2.helpers.read
import ie.setu.assignment2.helpers.write
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

const val JSON_FILE = "comicbooks.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<ComicbookModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ComicbookJSONStore(private val context: Context) : ComicbookStore {

    var comicbooks = mutableListOf<ComicbookModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ComicbookModel> {
        logAll()
        return comicbooks
    }

    override fun create(comicbook: ComicbookModel) {
        comicbook.id = generateRandomId()
        comicbooks.add(comicbook)
        serialize()
    }



    override fun update(comicbook: ComicbookModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(comicbooks, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        comicbooks = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        comicbooks.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}