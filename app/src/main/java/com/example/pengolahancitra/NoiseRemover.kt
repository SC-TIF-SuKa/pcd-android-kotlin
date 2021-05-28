package com.example.pengolahancitra

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log

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

                var newRed = Color.red(oldPixel)
                var newGreen = Color.green(oldPixel)
                var newBlue = Color.blue(oldPixel)

                var totalRed = 0
                var totalGreen = 0
                var totalBlue = 0

                //ALGORITHM USING 3X3
                if ((i > 0 && j > 0) && (i < w - 1 && j < h - 1)) {
                    for (m in i - 2..i + 2) {
                        for (n in j - 2..j + 2) {
                            val mnPixel = oldBitmap.getPixel(m, n)
                            totalRed += Color.red(mnPixel)
                            totalGreen += Color.green(mnPixel)
                            totalBlue += Color.blue(mnPixel)
                        }
                    }
                    newRed = totalRed / 25
                    newGreen = totalGreen / 25
                    newBlue = totalBlue / 25
                }


                //ALGORITHM USING 5X5
                /**
                if ((i > 1 && j > 1) && (i < w - 2 && j < h - 2)) {
                    for (m in i - 2..i + 2) {
                        for (n in j - 2..j + 2) {
                            val mnPixel = oldBitmap.getPixel(m, n)
                            totalRed += Color.red(mnPixel)
                            totalGreen += Color.green(mnPixel)
                            totalBlue += Color.blue(mnPixel)
                        }
                    }
                    newRed = totalRed / 25
                    newGreen = totalGreen / 25
                    newBlue = totalBlue / 25
                }

                */

                //use the avg filter
                val newPixel = Color.argb(oldAlpha, newRed, newGreen, newBlue)
                newBitmap.setPixel(i, j, newPixel)
            }
        }
        return newBitmap
    }
}