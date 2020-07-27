package com.panda.jetpackdemo.palette

import android.graphics.Bitmap
import android.graphics.Color
import androidx.palette.graphics.Palette
import com.panda.jetpackdemo.ktx.KtxUtils

object PaletteUtils {
    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    // Generate palette asynchronously and use it on a different
// thread using onGenerated()
    fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            val mutedColor = palette!!.getMutedColor(Color.BLUE)
            //主色调
            val rgb: Int? = palette?.vibrantSwatch?.rgb
            //文字颜色
            val bodyTextColor: Int? = palette?.vibrantSwatch?.bodyTextColor
            //标题的颜色
            val titleTextColor: Int? = palette?.vibrantSwatch?.titleTextColor
        }
    }
}

