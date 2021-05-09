package com.nyan.foodie.dialog

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.Window
import android.widget.TextView
import com.nyan.foodie.R

internal class DialogLoading {
    private var dialog: BaseDialog? = null

    fun showDialog(
        context: Context,
        msg: String?,
        dismissListener: DialogInterface.OnDismissListener?
    ) {
        if (!(context as Activity).isFinishing) {
            dialog = BaseDialog(context, R.style.DialogLoadingStyle)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.layout_default_loading)
            dialog!!.setOnDismissListener(dismissListener)
            dialog!!.setCancelable(true)
            dialog!!.show()
            val tvMsg = dialog!!.findViewById<TextView>(R.id.tv_msg)
            tvMsg.text =
                if (!TextUtils.isEmpty(msg)) msg else context.getString(R.string.title_loading)
            tvMsg.setOnLongClickListener {
                removeDialog()
                false
            }
        }
    }

    val isShowing: Boolean
        get() = if (dialog != null) {
            dialog!!.isShowing
        } else {
            false
        }

    fun removeDialog() {
        if (dialog != null && dialog!!.isShowing) {
                dialog!!.dismiss()
            }
        }
    }

