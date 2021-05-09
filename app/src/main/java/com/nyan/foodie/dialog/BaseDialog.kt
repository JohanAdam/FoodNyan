package com.nyan.foodie.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface

class BaseDialog : Dialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    protected constructor(
        context: Context, cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener)

    override fun show() {
        if (!Companion.isShowing) super.show()
        Companion.isShowing = true
    }

    override fun dismiss() {
        super.dismiss()
        Companion.isShowing = false
    }

    companion object {
        private var isShowing = false
    }
}