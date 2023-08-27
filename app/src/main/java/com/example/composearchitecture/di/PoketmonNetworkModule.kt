//package com.example.composearchitecture.di
//
//import com.example.composearchitecture.service.PoketmonService
//import com.google.gson.FieldNamingPolicy
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Converter
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Named
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//class PoketmonNetworkModule {
//
//    @Singleton
//    @Provides
//    @Named("BASE_POKETMON_URL")
//    fun BaseUrl() : String = "https://pokeapi.co/api/v2/"
//
////    @Singleton
////    @Provides
////    fun provideGson(): Gson =
////        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
////            .create()
////
////    @Singleton
////    @Provides
////    fun provideConverterFactory(
////        gson: Gson
////    ): Converter.Factory = GsonConverterFactory.create(gson)
////
////    @Singleton
////    @Provides
////    fun provideRetrofit(
////        @Named("BASE_POKETMON_URL") baseUrl: String,
////        converterFactory: Converter.Factory
////    ): Retrofit = Retrofit.Builder()
////        .baseUrl(baseUrl)
////        .addConverterFactory(converterFactory)
////        .build()
//
//    @Singleton
//    @Provides
//    fun providePoketmonService(
//        retrofit: Retrofit
//    ): PoketmonService = retrofit.create(PoketmonService::class.java)
//}