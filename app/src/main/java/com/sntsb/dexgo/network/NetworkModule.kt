package com.sntsb.dexgo.network

import com.sntsb.dexgo.pokemon.api.PokemonAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
class NetworkModule {

    //    @Module
//    @InstallIn(SingletonComponent::class)
    object NetworkModule {

        //        @Provides
//        @Singleton
        fun providePokemonApi(retrofit: Retrofit): PokemonAPI {
            return retrofit.create(PokemonAPI::class.java)
        }

        //        @Provides
//        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}