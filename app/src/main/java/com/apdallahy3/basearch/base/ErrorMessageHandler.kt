package com.apdallahy3.basearch.base

interface ErrorMessageHandler {

    fun getMessage() : String?

    fun onRetry() : Unit
}