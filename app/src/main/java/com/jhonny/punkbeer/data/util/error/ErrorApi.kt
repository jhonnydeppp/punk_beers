package com.jhonny.punkbeer.data.util.error

data class ErrorApi(
    val errorCode: String?,
    val statusCode: Any?,
    val error: String?,
    val message: Any?
)
