package christopher.brx

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseStores constructor(

val address: String,

val city: String,

val latitude: String,

val longitude: String,

val name: String,

val phone: String,

val state: String,
//@Primary key creates a unique primary key based on this value for the ROOM db
@PrimaryKey
val storeID: String,

val storeLogoURL: String,

val zipcode: String)

fun List<DatabaseStores>.asDomainModel(): List<StoresPropertyX> {
    return map {
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