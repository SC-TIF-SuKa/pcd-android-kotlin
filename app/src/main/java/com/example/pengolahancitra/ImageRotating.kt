package com.example.pengolahancitra

import android.graphics.Bitmap

/**
 * Created by Fakhry on 28/05/2021.
 */
object ImageRotating {

    /**
     * Rotate Left 90 Degree
     *
     * @param oldBitmap image where filter to be applied
     * @return newBitmap new image after filter
     */
    fun rotateLeft90(oldBitmap: Bitmap): Bitmap {

        val resizedOldBitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.height, oldBitmap.width, false)
        val newBitmap = resizedOldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = oldBitmap.height
        val w = oldBitmap.width

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
        val resizedOldBitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.height, oldBitmap.width, false)
        val newBitmap = resizedOldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = oldBitmap.height
        val w = oldBitmap.width

        for (x in 0 until w) {
            for (y in 0 until h) {
                //get the old pixel
                val oldPixel = oldBitmap.getPixel(x, y)

                //set the old pixel to the new pixel
                newBitmap.setPixel(((h - 1) - y), x, oldPixel)
            }
        }
        return newBitmap
    }
}