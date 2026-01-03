package uk.ac.tees.mad.s3484543.skyhop.network

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.s3484543.skyhop.model.api.FlightResponse

interface FlightApiService {

    @GET("flights")
    suspend fun getFlights(
        @Query("access_key") apiKey: String,
        @Query("dep_iata") from: String,
        @Query("arr_iata") to: String
    ): FlightResponse
}
