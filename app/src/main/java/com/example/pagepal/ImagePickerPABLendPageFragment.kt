package com.example.pagepal

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.pagepal.databinding.FragmentImagePickerPostABookLendPageBinding
import com.example.pagepal.models.LendBookViewModel
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import java.io.File
import java.io.FileOutputStream


class ImagePickerPABLendPageFragment : Fragment() {
    private var _binding: FragmentImagePickerPostABookLendPageBinding? = null
    private val binding get() = _binding!!



    private lateinit var imageDisplay: ImageView
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val uri = data?.data
                uri?.let { u ->
                    imageDisplay.setImageURI(u)
                    onImagePicked()
                }
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagePickerPostABookLendPageBinding.inflate(inflater, container, false)


        imageDisplay = binding.pickerImage
        binding.gallery.setOnClickListener {
            ImagePicker.with(requireActivity())
                .provider(ImageProvider.BOTH)
                .createIntentFromDialog { intent ->
                    launcher.launch(intent)
                }


        }
        binding.saveBtn.setOnClickListener {
                val postabookPageContainer = requireActivity().findViewById<ViewGroup>(R.id.imagepage)


                postabookPageContainer?.removeAllViews()
            val imageUri = getImageUriFromImageView()

            // Create a new instance of LendBookFragment and pass the image URI as an argument
            val lendBookFragment = LendBookFragment.newInstance(imageUri)

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.imagepage, lendBookFragment)
            transaction.commit()



        }


        return binding.root
    }



    private fun getImageUriFromImageView(): Uri? {
        val drawable = imageDisplay.drawable
        return if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            saveImageToInternalStorage(bitmap)
        } else {
            null
        }
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap): Uri? {
        val context = requireContext()

        // Create a new file in the cache directory
        val file = File(context.cacheDir, "image.png")

        // Create a FileOutputStream to write the bitmap data to the file
        val fos = FileOutputStream(file)

        // Compress the bitmap and write it to the file as PNG
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)

        // Close the FileOutputStream
        fos.close()

        // Get the URI of the saved file
        return FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)

    }


    private fun onImagePicked() {
        binding.saveBtn.visibility = View.VISIBLE // Show the save button
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding


    }
}