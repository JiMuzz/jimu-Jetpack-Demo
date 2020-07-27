package com.panda.jetpackdemo.dataBinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso

object ViewBinding {
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }

    //方法1，数据绑定到view
    @JvmStatic
    @BindingAdapter("app:bind_refreshing")
    fun setSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout, newValue: Boolean) {
        if (swipeRefreshLayout.isRefreshing != newValue)
            swipeRefreshLayout.isRefreshing = newValue
    }

    //方法1，view改变会通知bind_refreshingChanged，并且从该方法获取view的数据
    @JvmStatic
    @InverseBindingAdapter(attribute = "app:bind_refreshing",event = "app:bind_refreshingChanged")
    fun isSwipeRefreshLayoutRefreshing(swipeRefreshLayout: SwipeRefreshLayout): Boolean =swipeRefreshLayout.isRefreshing

    //方法3，view如何改变来影响数据内容
    @JvmStatic
    @BindingAdapter("app:bind_refreshingChanged",requireAll = false)
    fun setOnRefreshListener(swipeRefreshLayout: SwipeRefreshLayout,bindingListener: InverseBindingListener?) {
        if (bindingListener != null)
            swipeRefreshLayout.setOnRefreshListener {
                bindingListener.onChange()
            }
    }

}


