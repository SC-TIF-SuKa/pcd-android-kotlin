package com.example.pengolahancitra

import android.graphics.Bitmap
import android.graphics.Color

/**
 * Created by Fakhry on 28/05/2021.
 */
object NoiseRemover {
    fun averageFilter(oldBitmap: Bitmap): Bitmap {
        // copying to newBitmap for manipulation
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width

        // traversing each pixel in Image as an 2D Array
        for (i in 0 until w) {
            for (j in 0 until h) {
                // Algorithm for getting new values after calculation of filter
                // Algorithm for GREY FILTER, by intensity of each pixel

                val oldPixel = oldBitmap.getPixel(i, j)
                val oldAlpha = Color.alpha(oldPixel)

                var avgRed = 0
                var avgGreen = 0
                var avgBlue = 0
                var totalIteration = 0

                //Optimized Algorithm
                for (m in i - 1..i + 1) {
                    for (n in j - 1..j + 1) {

                        //Try and catch to avoid out of index iteration
                        try {
                            val mnPixel = oldBitmap.getPixel(m, n)
                            avgRed += Color.red(mnPixel)
                            avgGreen += Color.green(mnPixel)
                            avgBlue += Color.blue(mnPixel)
                            totalIteration++
                        } catch (e: Exception) {
                        }
                    }
                }
                avgRed /= totalIteration
                avgGreen /= totalIteration
                avgBlue /= totalIteration

                // CONQUER THE RISE OF SALT(WHITE)
                /**
                if(newRed == 255 && newGreen == 255 && newBlue == 255){
                for (m in i - 1..i + 1) {
                for (n in j - 1..j + 1) {

                //Try and catch to avoid out of index iteration
                try {
                val mnPixel = oldBitmap.getPixel(m, n)
                avgRed += Color.red(mnPixel)
                avgGreen += Color.green(mnPixel)
                avgBlue += Color.blue(mnPixel)

                totalIteration++
                } catch (e: Exception) {}
                }
                }
                avgRed /= totalIteration
                avgGreen /= totalIteration
                avgBlue /= totalIteration
                }
                 */

                //use the avg filter
                val newPixel = Color.argb(oldAlpha, avgRed, avgGreen, avgBlue)
                newBitmap.setPixel(i, j, newPixel)
            }
        }
        return newBitmap
    }
}