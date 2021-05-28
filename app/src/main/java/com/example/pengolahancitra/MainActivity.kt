package com.example.pengolahancitra

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.pengolahancitra.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener,
    PermissionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var defaultBitmap: Bitmap
    private var isPictureAdded: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnTakePicture.setOnClickListener(this@MainActivity)
            btnSave.setOnClickListener(this@MainActivity)
            btnImageInformation.setOnClickListener(this@MainActivity)
            btnSetToGrayscale.setOnClickListener(this@MainActivity)
            btnFlipHorizontal.setOnClickListener(this@MainActivity)
            btnFlipVertical.setOnClickListener(this@MainActivity)
            btnRotateLeft90.setOnClickListener(this@MainActivity)
            btnRotateRight90.setOnClickListener(this@MainActivity)
            btnBw.setOnClickListener(this@MainActivity)

            btnTakePicture.setOnLongClickListener(this@MainActivity)
            btnSave.setOnLongClickListener(this@MainActivity)
            btnImageInformation.setOnLongClickListener(this@MainActivity)
            btnSetToGrayscale.setOnLongClickListener(this@MainActivity)
            btnFlipHorizontal.setOnLongClickListener(this@MainActivity)
            btnFlipVertical.setOnLongClickListener(this@MainActivity)
            btnRotateLeft90.setOnLongClickListener(this@MainActivity)
            btnRotateRight90.setOnLongClickListener(this@MainActivity)
            btnBw.setOnLongClickListener(this@MainActivity)
        }

        //Set Bitmap
        val fileUri =
            "file:///storage/emulated/0/Android/data/com.example.pengolahancitra/files/DCIM/IMG_20210520_203823443.jpg".toUri()
        defaultBitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(fileUri))
        binding.ivImageTaken.setImageBitmap(defaultBitmap)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                Log.d("fileUri", fileUri.toString())

                //Set Bitmap
                defaultBitmap =
                    BitmapFactory.decodeStream(contentResolver.openInputStream(fileUri))
                // Use Uri object instead of File to avoid storage permissions

                isPictureAdded = true
                binding.ivImageTaken.setImageBitmap(defaultBitmap)
                binding.ivImageTaken.elevation = 0F
            }
            ImagePicker.RESULT_ERROR -> {
                showToast("ImagePicker.getError(data)")
                isPictureAdded = false
            }
            else -> {
                showToast("Gagal mengupload foto")
                isPictureAdded = false
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when (v) {
            binding.btnTakePicture -> showToast("Ambil Citra")
            binding.btnTakePicture -> showToast("Simpan Citra")
            binding.btnImageInformation -> showToast("Tampilkan Detail Citra")
            binding.btnSetToGrayscale -> showToast("Ubah Citra Ke Grayscale")
            binding.btnFlipVertical -> showToast("Flip Vertikal")
            binding.btnFlipHorizontal -> showToast("Flip Horizontal")
            binding.btnRotateLeft90 -> showToast("Rotasi ke kiri")
            binding.btnRotateRight90 -> showToast("Rotasi ke kanan")
            binding.btnBw -> showToast("Ubah Citra ke Black and White")

        }
        return true
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnTakePicture -> addBitmap()

            binding.btnSave -> {
                Utils.saveImage(defaultBitmap, this, "PCDPengeloahan Citra")
            }

            binding.btnImageInformation -> {
                if (isPictureAdded) {
                    val bitmapHeight = defaultBitmap.height
                    val bitmapWidth = defaultBitmap.width
                    val bitmapByteCount = defaultBitmap.byteCount
                    val bitmapDensity = defaultBitmap.density
                    val bitmapIsMutable = defaultBitmap.isMutable
                    val bitmapRowBytes = defaultBitmap.rowBytes

                    Log.d("bitmapInfo", "bitmap height\t: $bitmapHeight")
                    Log.d("bitmapInfo", "bitmap width\t: $bitmapWidth")
                    Log.d("bitmapInfo", "bitmap byte count\t: $bitmapByteCount")
                    Log.d("bitmapInfo", "bitmap density\t: $bitmapDensity")
                    Log.d("bitmapInfo", "bitmap is mutable\t: $bitmapIsMutable")
                    Log.d("bitmapInfo", "bitmap row bytes\t: $bitmapRowBytes")
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnSetToGrayscale -> {
                if (isPictureAdded) {
                    defaultBitmap = ImageFilters.setGreyFilter(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnFlipVertical -> {
                if (isPictureAdded) {
                    runBlocking {
                        defaultBitmap = ImageFlipping.verticalFlip(defaultBitmap)
                    }
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                    showLoading(false)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnFlipHorizontal -> {
                if (isPictureAdded) {
                    defaultBitmap = ImageFlipping.horizontalFlip(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnRotateLeft90 -> {
                if (isPictureAdded) {
                    defaultBitmap = ImageRotating.rotateLeft90(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnRotateRight90 -> {
                if (isPictureAdded) {
                    defaultBitmap = ImageRotating.rotateRight90(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnBw -> {
                if (isPictureAdded) {
                    defaultBitmap = ImageFilters.setBlackAndWhite(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }
        }
    }

    private fun addBitmap() {
        ImagePicker.with(this)
            .cropSquare()
            .start()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.run {
                pbLoading.visibility = View.VISIBLE
                ivLoadingBg.visibility = View.VISIBLE
            }
        } else {
            binding.run {
                pbLoading.visibility = View.INVISIBLE
                ivLoadingBg.visibility = View.INVISIBLE
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
        addBitmap()
    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
        Toast.makeText(
            this,
            "Tidak bisa mengakses kamera dan storage, silakan mengubahnya di setting.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        p0: PermissionRequest?,
        p1: PermissionToken?
    ) {
    }
}