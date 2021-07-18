package com.example.nutria.data.network

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.example.nutria.R

class ErrorHandler {

    companion object {
        fun showAlertForError(error: StateCodes, context: Context?,
                              listener: View.OnClickListener? = null) {
            val message = messageForError(error, context)
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage(message)
                .setNegativeButton(context?.getString(R.string.ok), { dialog, id ->
                    dialog.cancel()
                    listener?.onClick(null)
                })
            val alert = dialogBuilder.create()
            alert.setTitle(context?.getString(R.string.error_title))
            alert.show()
        }

        private fun messageForError(error: StateCodes, context: Context?): String {
            val generalError: String = context?.getString(R.string.error_general) ?: "Error"
            return when (error) {
                StateCodes.NETWORK_ERROR -> context?.getString(R.string.error_connection) ?: ""
                StateCodes.UNAUTHORIZED -> context?.getString(R.string.error_unauthorized) ?: ""
                StateCodes.INVALID_RECIPE -> context?.getString(R.string.error_deformed_recipe) ?: ""
                StateCodes.UNPROCESSABLE_ENTITY -> context?.getString(R.string.error_deformed_recipe) ?: ""
                else -> generalError
            }
        }
    }
}