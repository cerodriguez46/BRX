package christopher.brx

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

//Interface contains declarations of abstract methods, and method implementations. Interfaces cannot store state
//can have properties but need to be abstract or provide accessor implementations, class or object can implement on or more interfaces
//interface can inherit from other interfaces and provide implementations for their members and declare new functions/properties
//abstract classes cannot be instantiated or create objects from them, however you can inherit sub classes from them
//abstract classes can store state and are always open.
interface StoresApiService {
    @GET("stores.json")
    fun getProperties():
            //Deferred value is a non-blocking cancellable future — it is a Job that has a result.
            Deferred<StoresProperty>
}
    object StoresApi {
        //lazy() is a function that takes a lambda and returns an instance of Lazy<T> which can serve as a delegate for implementing a lazy property: the first call to get()
        // executes the lambda passed to lazy() and remembers the result, subsequent calls to get() simply return the remembered result.
        //By default, the evaluation of lazy properties is synchronized: the value is computed only in one thread, and all threads will see the same
        // value. If the synchronization of initialization delegate is not required, so that multiple threads can execute it simultaneously
        val retrofitService : StoresApiService by lazy {
            retrofit.create(StoresApiService::class.java)
        }
}