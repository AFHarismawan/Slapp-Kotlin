package com.harismawan.slapp.data

import com.harismawan.slapp.config.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private var retrofit: Retrofit? = null

        val client: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(Constant.baseUrl)

                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retrofit
            }
    }
}