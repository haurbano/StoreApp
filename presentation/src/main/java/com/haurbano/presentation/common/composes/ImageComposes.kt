package com.haurbano.presentation.common.composes

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.vectorResource
import com.haurbano.presentation.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target



@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
    var image by remember { mutableStateOf<Bitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }

    onCommit(url) {

        val picasso = Picasso.get()
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                drawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Log.w("ImageLoading", "Error Loading image: ${e?.localizedMessage}")
                drawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                image = bitmap
            }
        }
        picasso.load(url).into(target)

        onDispose {
            image = null
            drawable = null
            picasso.cancelRequest(target)
        }
    }
    if (image != null) {
        Image(bitmap = image!!.asImageBitmap(), modifier = modifier)
    } else {
        Image(
            imageVector = vectorResource(id = R.drawable.ic_broken_image_24),
            modifier = modifier
        )
    }

}