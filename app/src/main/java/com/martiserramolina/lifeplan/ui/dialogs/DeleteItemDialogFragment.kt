package com.martiserramolina.lifeplan.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.martiserramolina.lifeplan.R

class DeleteItemDialogFragment(
    private val messageId: Int,
    private val yesFunction: () -> Unit,
    private val noFunction: () -> Unit = {}
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireActivity())
            .setMessage(messageId)
            .setPositiveButton(R.string.yes) { _, _ -> yesFunction() }
            .setNegativeButton(R.string.no) { _, _ -> noFunction() }
            .create()
    }
}