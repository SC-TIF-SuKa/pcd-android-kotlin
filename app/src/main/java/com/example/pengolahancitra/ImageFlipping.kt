package com.example.pengolahancitra

import android.graphics.Bitmap

/**
 * Created by Fakhry on 28/05/2021.
 */
object ImageFlipping {

    fun verticalFlip(oldBitmap: Bitmap): Bitmap {
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width
        for (x in 0 until w) {
            for (y in 0 until h) {
                //get the old pixel
                val oldPixel = oldBitmap.getPixel(x, y)

                //set the old pixel to the new pixel
                newBitmap.setPixel(((w - 1) - x), y, oldPixel)
            }
        }
        return newBitmap
    }

    fun horizontalFlip(oldBitmap: Bitmap): Bitmap {
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width
        for (x in 0 until w) {
            for (y in 0 until h) {
                //get the old pixel
                val oldPixel = oldBitmap.getPixel(x, y)

                //set the old pixel to the new pixel
                newBitmap.setPixel(x, ((h - 1) - y), oldPixel)
            }
        }
        return newBitmap
    }
}