package com.example.nutria.data.network

enum class StateCodes(var code: Int) {
    LOADING(0),
    LOADED(2),
    SUCCESSFUL(3),
    NETWORK_ERROR(4),
    FAILED(5),
    UNAUTHORIZED(6),
    GENERAL_ERROR(1000);

    companion object {
        fun getStateFromCode(code: Int): StateCodes {
            var code = values().firstOrNull { it.code == code }
            if (code == null) {
                code = GENERAL_ERROR
            }
            return code
        }
    }
}