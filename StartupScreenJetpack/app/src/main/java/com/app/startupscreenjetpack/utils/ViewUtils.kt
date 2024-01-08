package com.app.startupscreenjetpack.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import java.util.*
import kotlin.math.roundToInt





fun Context.showToast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.loadingHide() {
    visibility = View.GONE
}


/**
 * Adds TextWatcher to the EditText
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if(s.toString() !="")
                afterTextChanged.invoke(s.toString())

        }
    })
}




fun EditText.afterTextChanged1(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                afterTextChanged.invoke(s.toString())

        }
    })
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.scroll() {
    this.setOnTouchListener { v, event ->
        v.parent.requestDisallowInterceptTouchEvent(true)
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
        }

        false
    }
}



fun EditText.validate(message: String, validator: (String) -> Boolean) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}
fun EditText.setEmpty() {
    this.text =Editable.Factory.getInstance().newEditable("")
}
fun EditText.setValidText(text: String) {
    this.text =Editable.Factory.getInstance().newEditable(text)
}

fun EditText.setValidHint(text: String) {
    this.hint =Editable.Factory.getInstance().newEditable(text)
}

fun EditText.hideClick() {
    this.customSelectionActionModeCallback = object : android.view.ActionMode.Callback {
        override fun onActionItemClicked(mode: android.view.ActionMode?, item: MenuItem?): Boolean {
            return false
        }
        override fun onCreateActionMode(mode: android.view.ActionMode?, menu: Menu?): Boolean {
            return false
        }
        override fun onPrepareActionMode(mode: android.view.ActionMode?, menu: Menu?): Boolean {
            return false
        }
        override fun onDestroyActionMode(mode: android.view.ActionMode?) {
        }
    }
}



fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPhone(): Boolean =
    this.isNotEmpty() && Patterns.PHONE.matcher(this).matches();

fun String.isInteger() = this?.toIntOrNull()?.let { true } ?: false

/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyboard() {

    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
   // imm.showSoftInput(this, 0)
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/**
 * Try to hide the keyboard and returns whether it worked
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}


/***
 * show progress dialog without text
 */
fun Context?.showProgressDialog() {
    if (this != null) {
       // CustomProgressDialog.showProgress(this, "")
    }
}

/***
 * hide progress dialog with text
 */
fun Context?.hideProgressDialog() {
    if(this != null) {
       // CustomProgressDialog.hideProgress(this)
    }
}


fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}
fun View.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = context.convertDpToPx(50F).roundToInt()
    return heightDiff > marginOfError
}





inline fun <reified T: Enum<T>> Bundle.getEnum(key:String): T {
    return enumValueOf(getString(key,""))
}

