package com.panda.benchmark

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val JETPACK = "images/test001.jpg"

@LargeTest
@RunWith(AndroidJUnit4::class)
class BitmapBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var bitmap: Bitmap

    @Before
    fun setUp() {
        val inputStream = context.assets.open(JETPACK)
        bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
    }

    /**
     * Measure the cost of many relatively cheaper JNI calls to fetch a row of pixels, one pixel at
     * a time.
     */
    @Test
    fun bitmapGetPixelBenchmark() {
        val pixels = IntArray(100) { it }
        benchmarkRule.measureRepeated {
            pixels.map { bitmap.getPixel(it, 0) }
        }
    }

    /**
     * Measure the cost of a single expensive JNI call to fetch a row of 100 pixels.
     */
    @Test
    fun bitmapGetPixelsBenchmark() {
        val pixels = IntArray(100) { it }
        benchmarkRule.measureRepeated {
            bitmap.getPixels(pixels, 0, 100, 0, 0, 100, 1)
        }
    }
}