package com.meli.notbored.service

data class Response(val id: Long, val status: String, val msn: String, val url: String) {
    fun isOk() = "OK".equals(status, ignoreCase = true)
    companion object{
        fun error():Response{
            return Response(0, "error", ",", "")
        }
    }
}
