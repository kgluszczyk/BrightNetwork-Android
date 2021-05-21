package com.brightnetwork.summerfests

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InfoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("WARNING!")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") {dialog, _ -> requireActivity().finish()}
            .setNeutralButton("Not sure...") {_, _ -> }
            .setNegativeButton("NO!!!!") { dialog, _ -> dialog.dismiss()}
            .create()
    }
}