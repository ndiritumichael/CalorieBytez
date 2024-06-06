package com.devmike.domain.models

sealed class AppErrors(
    override val message: String,
    override val cause: Throwable? = null,
) : Exception(message, cause) {
    class NoInternet : AppErrors("No internet connection, Please Ensure you have an internet connection")

    class Timeout : AppErrors("Connection Timed Out, Please Try Again")

    class Unknown : AppErrors("Something went wrong")

    class NotFound : AppErrors("Could not find any food items")

    class Empty : AppErrors("We could not find any items please search another item")

    class Unauthorized : AppErrors("You are not authorized to make this request")
}
