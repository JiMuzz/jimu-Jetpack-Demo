package com.panda.jetpackdemo.livedata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.math.BigDecimal

public class MyFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myPriceListener: LiveData<BigDecimal> = StockLiveData("")
        myPriceListener.observe(viewLifecycleOwner, Observer<BigDecimal> { price: BigDecimal? ->
            // 监听livedata的数据变化，如果调用了setValue或者postValue会调用该onChanged方法
            //更新UI数据或者其他处理

        })
    }
}