package com.example.pengolahancitra

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log

object ImageFilters {

    private const val HIGHEST_COLOR_VALUE = 255
    private const val LOWEST_COLOR_VALUE = 0

    /**
     * Apply Grey Filter on image
     *
     * @param oldBitmap image where filter to be applied
     * @return newBitmap new image after filter
     */
    fun setGreyFilter(oldBitmap: Bitmap): Bitmap {

        // copying to newBitmap for manipulation
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width
        Log.e("Image Size", "Height=$h Width=$w")


        // traversing each pixel in Image as an 2D Array
        for (i in 0 until w) {
            for (j in 0 until h) {

                // getting each pixel
                val oldPixel = oldBitmap.getPixel(i, j)

                // each pixel is made from RED_BLUE_GREEN_ALPHA
                // so, getting current values of pixel
                val oldRed = Color.red(oldPixel)
                val oldBlue = Color.blue(oldPixel)
                val oldGreen = Color.green(oldPixel)
                val oldAlpha = Color.alpha(oldPixel)

                // Algorithm for getting new values after calculation of filter
                // Algorithm for GREY FILTER, by intensity of each pixel
                val intensity = (oldRed + oldBlue + oldGreen) / 3

                // applying new pixel values to newBitmap
                val newPixel = Color.argb(oldAlpha, intensity, intensity, intensity)
                newBitmap.setPixel(i, j, newPixel)
            }
        }
        return newBitmap
    }


    /**
     * Apply Negative Filter on image
     *
     * @param oldBitmap image where filter to be applied
     * @return newBitmap new image after filter
     */
    fun setNegativeFilter(oldBitmap: Bitmap): Bitmap {

        // copying to newBitmap for manipulation
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width
        Log.e("Image Size", "Height=$h Width=$w")

        // traversing each pixel in Image as an 2D Array
        for (i in 0 until w) {
            for (j in 0 until h) {

                // getting each pixel
                val oldPixel = oldBitmap.getPixel(i, j)

                // each pixel is made from RED_BLUE_GREEN_ALPHA
                // so, getting current values of pixel
                val oldRed = Color.red(oldPixel)
                val oldBlue = Color.blue(oldPixel)
                val oldGreen = Color.green(oldPixel)

                // Algorithm for getting new values after calculation of filter
                // Algorithm for NEGATIVE FILTER
                val newRed = HIGHEST_COLOR_VALUE - oldRed
                val newBlue = HIGHEST_COLOR_VALUE - oldBlue
                val newGreen = HIGHEST_COLOR_VALUE - oldGreen

                // applying new pixel value to newBitmap
                val newPixel = Color.rgb(newRed, newGreen, newBlue)
                newBitmap.setPixel(i, j, newPixel)
            }
        }
        return newBitmap
    }

    /**
     * Apply Sketch Filter on image
     *
     * @param oldBitmap image where filter to be applied
     * @return newBitmap new image after filter
     */
    fun setBlackAndWhite(oldBitmap: Bitmap): Bitmap {
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val h = newBitmap.height
        val w = newBitmap.width
        Log.e("Image Size", "Height=$h Width=$w")

        // traversing each pixel in Image as an 2D Array
        for (i in 0 until w) {
            for (j in 0 until h) {

                // operating on each pixel
                val oldPixel = oldBitmap.getPixel(i, j)

                // each pixel is made from RED_BLUE_GREEN
                // so, getting current values of pixel
                val oldRed = Color.red(oldPixel)
                val oldBlue = Color.blue(oldPixel)
                val oldGreen = Color.green(oldPixel)
                val oldAlpha = Color.alpha(oldPixel)


                // Algorithm for getting new values after calculation of filter
                // Algorithm for SKETCH FILTER
                val intensity = (oldRed + oldBlue + oldGreen) / 3

                // applying new pixel value to newBitmap
                // condition for Sketch
                var newPixel = 0
                val intensityFactor = 128
                newPixel = if (intensity > intensityFactor) {
                    // apply white color
                    Color.argb(
                        oldAlpha,
                        HIGHEST_COLOR_VALUE,
                        HIGHEST_COLOR_VALUE,
                        HIGHEST_COLOR_VALUE
                    )
                } else {
                    // apply black color
                    Color.argb(oldAlpha, LOWEST_COLOR_VALUE, LOWEST_COLOR_VALUE, LOWEST_COLOR_VALUE)
                }
                newBitmap.setPixel(i, j, newPixel)
            }
        }
        return newBitmap
    }
}