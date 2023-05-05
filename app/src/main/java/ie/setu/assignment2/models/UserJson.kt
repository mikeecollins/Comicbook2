
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

const val JSON_FILE_USER = "users.json"
val gsonBuilderUser: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParserUser())
    .create()
val listTypeUser: Type = object : TypeToken<ArrayList<UserModel>>() {}.type



class UserJson(private val context: Context):UserJsonStore {

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, JSON_FILE_USER)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        logAll()
        return users
    }
    override fun findUser(uname:String, pass:String): UserModel? {
        return users.find{it.comicbookpassword.equals(pass) && it.comicbookusername.equals(uname)}
    }

    override fun create(user: UserModel) {

        users.add(user)
        serialize()
    }

    override fun update(user: UserModel) {
        TODO("Not yet implemented")
    }


    private fun serialize() {
        val jsonString = gsonBuilderUser.toJson(users, listTypeUser)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        users = gsonBuilderUser.fromJson(jsonString, listTypeUser)
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }
}

class UriParserUser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
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