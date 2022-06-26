package uz.orign.fourpicture

import android.view.View

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.invisibleOrInvisible(state:Boolean) = if (state) visible() else invisible()
fun View.invisibleOrGone(state:Boolean) = if (state) visible() else gone()

val View.isGone get() = visibility == View.GONE
val View.isVisible get() = visibility == View.VISIBLE
val View.isInvisible get() = visibility == View.INVISIBLE

