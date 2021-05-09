package com.nyan.foodie.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.nyan.foodie.R


@BindingAdapter("setTextResWithData")
fun bindStringResWithData(textView: TextView, text: String?) {
    text?.let {
        textView.text = String.format(textView.context.resources.getString(R.string.title_comment), text)
    }
}