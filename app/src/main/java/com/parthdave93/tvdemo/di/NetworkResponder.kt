package com.parthdave93.di

/**
 * Created by Parth Dave on 03-07-2018.
 */
interface NetworkResponder<T>{
    fun onSuccess(response: T)
    fun onError(response: T)
}