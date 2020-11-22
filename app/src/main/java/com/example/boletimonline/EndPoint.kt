package com.example.boletimonline

import com.example.boletimonline.Notas
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {
        @GET("notas")
        fun obterNotas(): Call<List<Notas>>
    }

