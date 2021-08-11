package com.example.testdemo

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment() {

    companion object {

        fun show(fragmentManager: FragmentManager, resColor: Int) {
            fragmentManager.beginTransaction()
                .add(BottomSheetDialog().apply {
                    arguments = bundleOf(Pair("color", resColor))
                }, BottomSheetDialog::class.simpleName)
                .commitAllowingStateLoss()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setContentView(R.layout.dialog_info)
        dialog.findViewById<View>(R.id.root)
            .setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    requireArguments().getInt("color")
                )
            )
        return dialog
    }

}