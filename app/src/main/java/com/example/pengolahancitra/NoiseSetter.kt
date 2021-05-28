package com.example.pengolahancitra

import android.graphics.Bitmap
import android.graphics.Color
import kotlin.random.Random

/**
 * Created by Fakhry on 28/05/2021.
 */
object NoiseSetter {
    fun setNoiseSaltAndPepper(oldBitmap: Bitmap) : Bitmap{
        // copying to newBitmap for manipulation
        val newBitmap = oldBitmap.copy(Bitmap.Config.ARGB_8888, true)

        // height and width of Image
        val h = newBitmap.height
        val w = newBitmap.width

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

                // set noise probability
                val noiseProb = 10;
                val randInt = Random.nextInt(0, 100)

                //set new pixel
                val newPixel = if(randInt < noiseProb){
                    Color.argb(oldAlpha, 255, 255, 255)
                }else{
                    Color.argb(oldAlpha, oldRed, oldGreen, oldBlue)
                }
                newBitmap.setPixel(i, j, newPixel)
            }
        }
        return newBitmap
    }
}