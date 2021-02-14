package com.rafaelperezolm.cinepolis.data.remote

import com.rafaelperezolm.cinepolis.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {

    // Getting the results of the API call
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    // If an error is obtained, a message is returned with the problem
    private fun <T> error(message: String): Resource<T> {
        val retrnMessage: String = when(message.trim()) {
            "timeout" -> "Tiempo de conexión agotado"
            "400" -> "Usuario o Contraseña incorrectos"
            "404" -> "Recurso no encontrado"
            "500" -> "Error de servidor"
            else -> "Error desconocido"
        }
        return Resource.error(retrnMessage)
    }

}