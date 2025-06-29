package com.example.notemark.data

import com.example.notemark.domain.NoteMarkResult
import retrofit2.Response

suspend fun <T>makeRequest(
    request: suspend() -> Response<out T?>
): NoteMarkResult<T> {
    return try {
        val result = request()
        if (result.isSuccessful) {
            val body = result.body()
            if (body == null) {
                NoteMarkResult.Error.UnknownError
            } else {
                NoteMarkResult.Success(body)
            }
        } else {
            when (result.code()) {
                400 -> NoteMarkResult.Error.FormatError
                401 -> NoteMarkResult.Error.AuthorizationError
                405 -> NoteMarkResult.Error.MethodError
                409 -> NoteMarkResult.Error.ConflictError
                429 -> NoteMarkResult.Error.TooManyRequestError
                else -> NoteMarkResult.Error.UnknownError
            }
        }
    } catch (e: Exception) {
        print(e)
        NoteMarkResult.Error.UnknownError
    }

}

//login
//get tokens response
//save the token locally
//add token from preference in each request
//  if the access token is invalid i get 401. use the refresh endpoint and the refresh token to get a new Tokens (save locally) and retry the request

//if the refresh token expires (in any request) the session expires (code 401). The user is logout, local tokens is cleared and with a login you get a new one.
