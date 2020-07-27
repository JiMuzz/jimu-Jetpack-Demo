package com.panda.jetpackdemo.slice


import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import com.panda.jetpackdemo.R
import com.panda.jetpackdemo.setting.SettingActivity

class MySliceProvider : SliceProvider() {
    /**
     * Instantiate any required objects. Return true if the provider was successfully created,
     * false otherwise.
     */
    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    /**
     * Converts URL to content URI (i.e. content://com.panda.jetpackdemo...)
     */
    override fun onMapIntentToUri(intent: Intent?): Uri {
        // Note: implementing this is only required if you plan on catching URL requests.
        // This is an example solution.
        var uriBuilder: Uri.Builder = Uri.Builder().scheme(ContentResolver.SCHEME_CONTENT)
        if (intent == null) return uriBuilder.build()
        val data = intent.data
        val dataPath = data?.path
        if (data != null && dataPath != null) {
            val path = dataPath.replace("/", "")
            uriBuilder = uriBuilder.path(path)
        }
        val context = context
        if (context != null) {
            uriBuilder = uriBuilder.authority(context.packageName)
        }
        return uriBuilder.build()
    }

    /**
     * Construct the Slice and bind data if available.
     */
    override fun onBindSlice(sliceUri: Uri): Slice? {
        // Note: you should switch your build.gradle dependency to
        // slice-builders-ktx for a nicer interface in Kotlin.
        val context = context ?: return null
        val activityAction = createActivityAction() ?: return null
        return if (sliceUri.path == "/hello") {
            // Path recognized. Customize the Slice using the androidx.slice.builders API.
            // Note: ANR and StrictMode are enforced here so don't do any heavy operations.
            // Only bind data that is currently available in memory.
            Log.e("lz5","1111")
            ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .addRow(
                    ListBuilder.RowBuilder()
                        .setTitle("URI found.")
                        .setPrimaryAction(activityAction)
                )
                .build()
        } else {
            // Error: Path not found.
            ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                .addRow(
                    ListBuilder.RowBuilder()
                        .setTitle("URI not found.")
                        .setPrimaryAction(activityAction)
                )
                .build()
        }
    }

    // [START create_slice]
    open fun createSlice(sliceUri: Uri?): Slice? {
        if (context == null) {
            return null
        }
        val activityAction = createActivityAction()
        return ListBuilder(
            context!!,
            sliceUri!!,
            ListBuilder.INFINITY
        ).addRow(
                ListBuilder.RowBuilder()
                    .setTitle("Perform action in app.")
                    .setPrimaryAction(activityAction!!)
            ).build()
    }

    private fun createActivityAction(): SliceAction? {
        val intent = Intent(context, SettingActivity::class.java)
        return SliceAction.create(
            PendingIntent.getActivity(context, 0, Intent(context, SettingActivity::class.java), 0),
            IconCompat.createWithResource(context,
                R.drawable.ic_launcher_foreground
            ),
            ListBuilder.ICON_IMAGE,
            "Enter app"
        )
    }

    /**
     * Slice has been pinned to external process. Subscribe to data source if necessary.
     */
    override fun onSlicePinned(sliceUri: Uri?) {
        // When data is received, call context.contentResolver.notifyChange(sliceUri, null) to
        // trigger MySliceProvider#onBindSlice(Uri) again.
    }

    /**
     * Unsubscribe from data source if necessary.
     */
    override fun onSliceUnpinned(sliceUri: Uri?) {
        // Remove any observers if necessary to avoid memory leaks.
    }
}
