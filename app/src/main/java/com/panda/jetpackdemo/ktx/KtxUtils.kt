package com.panda.jetpackdemo.ktx

import androidx.collection.arraySetOf

object KtxUtils {
    fun addTest(){
        val arraySet1 = LinkedHashSet<Int>()
        arraySet1.add(1)
        arraySet1.add(2)
        arraySet1.add(3)

        val arraySet2 = LinkedHashSet<Int>()
        arraySet2.add(4)
        arraySet2.add(5)
        arraySet2.add(6)

        val combinedArraySet1 = LinkedHashSet<Int>()
        combinedArraySet1.addAll(arraySet1)
        combinedArraySet1.addAll(arraySet2)
    }

    fun addTestKtx(){
        // Combine 2 ArraySets into 1.
        val combinedArraySet = arraySetOf(1, 2, 3) + arraySetOf(4, 5, 6)
    }
}
