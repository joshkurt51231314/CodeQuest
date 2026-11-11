package com.example.codequest.ui

import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun View.setPixelEnabled(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.setBackgroundKeepPadding(resId: Int) {
    val l = paddingLeft
    val t = paddingTop
    val r = paddingRight
    val b = paddingBottom
    setBackgroundResource(resId)
    setPadding(l, t, r, b)
}

fun setupPasswordToggle(field: EditText, toggle: TextView) {
    var visible = false
    toggle.setOnClickListener {
        visible = !visible
        field.transformationMethod = if (visible) null else PasswordTransformationMethod.getInstance()
        field.setSelection(field.text.length)
        toggle.text = if (visible) "HIDE" else "SHOW"
    }
}
