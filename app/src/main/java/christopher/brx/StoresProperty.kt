package christopher.brx

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class StoresProperty(

    //returns a list of store attributes from store X
//@Json sets a specific name that you want it to be called
    @Json(name = "stores")val stores: List<StoresPropertyX>
)
//@Entity specifies a table name for the list of properties
@Entity(tableName = "storestable")
//@Parcelize puts all the properties into a parcelable
@Parcelize
//data class in Kotlin automatically generates getters and setters for all the properties
//and getter for only read only properties
//also has equals(), hashCode(), and toString() methods from the properties
data class StoresPropertyX (



    val address: String,

    val city: String,

    val latitude: String,

    val longitude: String,

    val name: String,

    val phone: String,

    val state: String,

    val storeID: String,

    @Json(name = "storeLogoURL")val storeLogoURL: String,

    val zipcode: String) : Parcelable {

}
fun StoresProperty.asDomainModel(): List<StoresPropertyX> {
    return stores.map {
        StoresPropertyX(
            address = it.address,
            city = it.city,
            latitude = it.latitude,
            longitude = it.longitude,
            name = it.name,
            phone = it.phone,
            state = it.state,
            storeID = it.storeID,
            storeLogoURL = it.storeLogoURL,
            zipcode = it.zipcode)

    }
}

fun StoresProperty.asDatabaseModel(): Array<DatabaseStores> {
    return stores.map {
        DatabaseStores(
            address = it.address,
            city = it.city,
            latitude = it.latitude,
            longitude = it.longitude,
            name = it.name,
            phone = it.phone,
            state = it.state,
            storeID = it.storeID,
            storeLogoURL = it.storeLogoURL,
            zipcode = it.zipcode)
    }.toTypedArray()
}
