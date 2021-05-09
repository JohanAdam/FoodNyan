package com.nyan.foodie.binding

import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter


@BindingAdapter("setRatingFromString")
fun bindRatingFromString(ratingBar: RatingBar, ratingValue: String) {
    ratingBar.rating = ratingValue.toFloat()
}

@BindingAdapter("setRatingFromDouble")
fun bindRatingFromDouble(ratingBar: RatingBar, ratingValue: Double) {
    ratingBar.rating = ratingValue.toFloat()
}