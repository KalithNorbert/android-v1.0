package com.example.projectandroid.network
//a hálózati réteg megtartásához sszükséges
// az Api amit a ViewModel a webszolgáltatással
// valő kommunikációhoz fog használni

/*
A retrofit egy konyvtar, amely halozati API-t hoz letre
az alkalmazasunkhoz a webszerver alapjan.
Adatokat kap a webszolgaltatastol es egy kulon konverte konyvtaron kereszyul
tovabbitja, hogz tudja hogzan kell dekodolni az adatokat es hasznos objektumok
formajaban visszaadni
A retrofit barmilyen visszakuldott adatformatumot tamogat a webes szolgaltatasunkbol
a megfelelo konverter konyvtarral
A retrofit meg magaban foglalaja a nepszeru webes adatformatumokat elemzo
konyvtarak beepitett tamogatasat  XML/JSON

Retrofitehez 2 dolognak rendelkezésre kell állnia az API felépítéséhezÉ
        wbeszolgáltatásunk alap URL-je
        és egy konverter gyár ami a szerver válaszához megfelelő formátumba készíti elő az adatokat
 */
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://ratpark-api.imok.space/"

//RetrofitBuilder hasznalata a ScalarsConverter es  BASE_URL-l


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//RestaurantApi implementalasa a @GET getProperties segitsegevel ami egy Stringet add vissza

interface RestaurantsApiService{
    @GET("restaurants")
    fun getProperties():
            Call<String>
}

object RestaurantsApi{
    val retrofitService : RestaurantsApiService by lazy {
        retrofit.create(RestaurantsApiService::class.java)
    }
}



/*
    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val okHttp = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl("https://ratpark-api.imok.space/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }

    val api: RestaurantsApiService by lazy {
        retrofit.create(RestaurantsApiService::class.java)
    }
*/






