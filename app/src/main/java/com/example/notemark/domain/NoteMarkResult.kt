package com.example.notemark.domain

sealed class NoteMarkResult<out T> {

    data class Success<T>(val data: T): NoteMarkResult<T>()

    sealed class Error: NoteMarkResult<Nothing>() {

        data object FormatError: Error()//Error on the format about the request
        data object AuthorizationError: Error()//Unauthorized
        data object MethodError: Error()//Incorrect request
        data object ConflictError: Error()//Conflict in response
        data object TooManyRequestError: Error()//Conflict in response
        data object UnexpectedResponseError: Error()
        data object UnknownError: Error()

    }

}
