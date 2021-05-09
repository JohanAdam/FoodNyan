package com.nyan.foodie.binding

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.nyan.foodie.R
import com.nyan.foodie.ui.main.MainActivity
import timber.log.Timber


@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        //Convert String to Uri and append https.
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(RequestOptions()
                .error(R.drawable.ic_broken_img))
            .transition(DrawableTransitionOptions.withCrossFade()) //Here a fading animation
            .centerCrop()
            .into(imgView)
    }
}

@BindingAdapter("setProfilePicture")
fun setProfilePicture(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .apply(RequestOptions()
                .error(R.drawable.ic_broken_img))
            .transition(DrawableTransitionOptions.withCrossFade()) //Here a fading animation
            .circleCrop()
            .into(imgView)
    }
}
