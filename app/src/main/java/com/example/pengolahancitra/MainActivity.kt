package com.example.pengolahancitra

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pengolahancitra.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.coroutines.runBlocking

/**
 * Created by Fakhry on 28/05/2021.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener,
    PermissionListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var defaultBitmap: Bitmap
    private var isPictureAdded: Boolean = false

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
            btnMonochrome.setOnClickListener(this@MainActivity)
            btnNoiseSalt.setOnClickListener(this@MainActivity)
            btnAvgFilter.setOnClickListener(this@MainActivity)


            btnTakePicture.setOnLongClickListener(this@MainActivity)
            btnSave.setOnLongClickListener(this@MainActivity)
            btnImageInformation.setOnLongClickListener(this@MainActivity)
            btnSetToGrayscale.setOnLongClickListener(this@MainActivity)
            btnFlipHorizontal.setOnLongClickListener(this@MainActivity)
            btnFlipVertical.setOnLongClickListener(this@MainActivity)
            btnRotateLeft90.setOnLongClickListener(this@MainActivity)
            btnRotateRight90.setOnLongClickListener(this@MainActivity)
            btnMonochrome.setOnLongClickListener(this@MainActivity)
            btnNoiseSalt.setOnLongClickListener(this@MainActivity)
            btnAvgFilter.setOnLongClickListener(this@MainActivity)
        }

        //Set Default Bitmap
//        val fileUri = "content://com.android.providers.media.documents/document/image%3A220701".toUri()
//        defaultBitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(fileUri))
//        binding.ivImageTaken.setImageBitmap(defaultBitmap)

    }

    /** Solution For Deprecated Activity For Result
    var resultLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    when (result.resultCode) {
    Activity.RESULT_OK -> {
    //Image Uri will not be null for RESULT_OK
    val fileUri = result.data?.data!!
    //Set Bitmap
    defaultBitmap =
    BitmapFactory.decodeStream(contentResolver.openInputStream(fileUri))
    // Use Uri object instead of File to avoid storage permissions

    isPictureAdded = true
    binding.ivImageTaken.setImageBitmap(defaultBitmap)
    binding.ivImageTaken.elevation = 0F
    }
    ImagePicker.RESULT_ERROR -> {
    showToast(ImagePicker.getError(result.data))
    isPictureAdded = false
    }
    else -> {
    showToast("Gagal mengupload foto")
    isPictureAdded = false
    }
    }
    }
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                val fileUri = data?.data!!

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
            binding.btnMonochrome -> showToast("Ubah Citra ke Black and White")
            binding.btnNoiseSalt -> showToast("Berikan noise salt and papper ke citra")
            binding.btnAvgFilter -> showToast("Restorasi citra")
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnTakePicture -> addBitmap()

            binding.btnSave -> {
                if (isPictureAdded) {
                    Utils.saveImage(defaultBitmap, this, "PCD")
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnImageInformation -> {
                if (isPictureAdded) {
//                    val bitmapHeight = defaultBitmap.height
//                    val bitmapWidth = defaultBitmap.width
//                    val bitmapByteCount = defaultBitmap.byteCount
//                    val bitmapDensity = defaultBitmap.density
//                    val bitmapIsMutable = defaultBitmap.isMutable
//                    val bitmapRowBytes = defaultBitmap.rowBytes
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

            binding.btnMonochrome -> {
                if (isPictureAdded) {
                    defaultBitmap = ImageFilters.setBlackAndWhite(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnNoiseSalt -> {
                if (isPictureAdded) {
                    defaultBitmap = NoiseSetter.setNoiseSaltAndPepper(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }

            binding.btnAvgFilter -> {
                if (isPictureAdded) {
                    defaultBitmap = NoiseRemover.averageFilter(defaultBitmap)
                    binding.ivImageTaken.setImageBitmap(defaultBitmap)
                } else showToast("Gambar belum ditambahkan.")
            }
        }
    }

    private fun addBitmap() {
        ImagePicker.with(this)
            .start()
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