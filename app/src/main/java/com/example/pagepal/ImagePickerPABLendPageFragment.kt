package com.example.pagepal

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider


class ImagePickerPABLendPageFragment : Fragment() {

    private lateinit var imageDisplay: ImageView
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val uri = data?.data
            uri?.let { u ->
                imageDisplay.setImageURI(u)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_image_picker_post_a_book_lend_page, container, false)

        imageDisplay = rootView.findViewById(R.id.picker_image)
        rootView.findViewById<Button>(R.id.gallery)?.setOnClickListener {
            ImagePicker.with(requireActivity())
                .provider(ImageProvider.BOTH)
                .createIntentFromDialog { intent ->
                    launcher.launch(intent)
                }
        }

        return rootView
    }
}