package com.example.nutria.data.network

import android.app.AlertDialog
import android.content.Context
import android.view.View

class ErrorHandler {

    companion object {
        fun showAlertForError(error: StateCodes, context: Context?,
                              listener: View.OnClickListener? = null) {
            val message = messageForError(error, context)
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage(message)
                .setNegativeButton("Ok", { dialog, id ->
                    dialog.cancel()
                    listener?.onClick(null)
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Error")
//            alert.setTitle(context.getString(R.string.error))
            alert.show()
        }

        private fun messageForError(error: StateCodes, context: Context?): String {
            return "Error"
            /*return when (error) {
            }*/
        }
    }
}