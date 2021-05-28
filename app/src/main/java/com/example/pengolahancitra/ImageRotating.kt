package com.example.pengolahancitra

import android.graphics.Bitmap
import android.graphics.Color

object ImageRotating {

    /**
     * Rotate Left 90 Degree
     *
     * @param oldBitmap image where filter to be applied
     * @return newBitmap new image after filter
     */
    fun rotateLeft90(oldBitmap: Bitmap): Bitmap {
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width
        for (x in 0 until w) {
            for (y in 0 until h) {
                //get the old pixel
                val oldPixel = oldBitmap.getPixel(x, y)

                //set the old pixel to the new pixel
                newBitmap.setPixel(y, ((w - 1) - x), oldPixel)
            }
        }
        return newBitmap
    }

    /**
     * Rotate Right 90 Degree
     *
     * @param oldBitmap image where filter to be applied
     * @return newBitmap new image after filter
     */
    fun rotateRight90(oldBitmap: Bitmap): Bitmap {
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width
        for (x in 0 until w) {
            for (y in 0 until h) {
                //get the old pixel
                val oldPixel = oldBitmap.getPixel(x, y)

                //set the old pixel to the new pixel
                newBitmap.setPixel(((w - 1) - y), x, oldPixel)
            }
        }
        return newBitmap
    }
}