package com.example.notemark.domain

sealed class NoteMarkResult<T> {

    data class Success<T>(val data: T): NoteMarkResult<T>()

    sealed class Error<T>: NoteMarkResult<T>() {

        data object FormatError: Error<Nothing>()//Error on the format about the request
        data object AuthorizationError: Error<Nothing>()//Unauthorized
        data object MethodError: Error<Nothing>()//Incorrect request
        data object ConflictError: Error<Nothing>()//Conflict in response
        data object TooManyRequestError: Error<Nothing>()//Conflict in response
        data object UnknownError: Error<Nothing>()

    }

}
