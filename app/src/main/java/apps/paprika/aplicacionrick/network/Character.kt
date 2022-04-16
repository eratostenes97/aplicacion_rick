package apps.paprika.aplicacionrick.network

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

data class Character(
    @Json(name = "name")
    val name:String,
    @Json(name = "image")
    val image:String,
)

data class CharacterResponse(@Json(name = "results")
val result: List<Character>
)

