package com.parthdave93.tvdemo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Parth Dave on 25-07-2018.
 */
class CardItemModel : Serializable{
    @SerializedName("title")
    var title: String? = null
        set(value) {
            field = value
            value?.let { registerTitleChange?.onValueChanged(it) }
        }

    @SerializedName("description")
    var description: String? = null
        set(value) {
            field = value
            value?.let { registerDescriptionChange?.onValueChanged(it) }
        }

    @SerializedName("picture")
    var backgroundImageUrl: String? = null

    constructor(title: String, description: String, backgroundImageUrl: String) {
        this.title = title
        this.description = description
        this.backgroundImageUrl = backgroundImageUrl
    }

    @Transient
    var registerTitleChange: RegisterChange<String>? = null
    @Transient
    var registerDescriptionChange: RegisterChange<String>? = null
    @Transient
    var isFocused: Boolean = false


    interface RegisterChange<T> {
        fun onValueChanged(value: T)
    }
}