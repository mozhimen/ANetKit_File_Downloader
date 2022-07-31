package com.chiclaim.android.updater

import android.content.Context

class EmptyDownloader(context: Context, request: Request) :
    Downloader<Request>(context.applicationContext, request) {

    override fun startDownload() {
        // do nothing
    }
}