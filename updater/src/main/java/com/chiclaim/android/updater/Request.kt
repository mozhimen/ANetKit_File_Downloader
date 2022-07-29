package com.chiclaim.android.updater

import android.content.Context
import android.net.Uri
import com.chiclaim.android.updater.util.NotifierVisibility

/**
 *
 * @author by chiclaim@google.com
 */
abstract class Request(val url: String) {

    /**
     * Whether to ignore the local file, if true, it will be downloaded again
     */
    private var ignoreLocal = false

    /**
     * 本次下载是否为更新当前的APP，如果是，则会自动处理弹出安装界面
     */
    private var needInstall = false

    private var notificationVisibility = NOTIFIER_VISIBLE_NOTIFY_COMPLETED

    fun setIgnoreLocal(ignore: Boolean): Request {
        this.ignoreLocal = ignore
        return this
    }

    fun isIgnoreLocal() = ignoreLocal

    fun setNeedInstall(need: Boolean): Request {
        this.needInstall = need
        return this
    }

    fun isNeedInstall() = needInstall

    fun getNotificationVisibility() = notificationVisibility

    abstract fun setNotificationTitle(title: CharSequence): Request

    abstract fun setNotificationDescription(description: CharSequence): Request

    open fun allowScanningByMediaScanner(): Request = this

    open fun setDestinationInExternalFilesDir(
        context: Context,
        dirType: String?,
        subPath: String?
    ): Request = this

    open fun setDestinationDir(uri: Uri): Request = this


    open fun setMimeType(mimeType: String): Request = this

    /**
     * 设置通知栏可见性，默认为 [NOTIFIER_VISIBLE_NOTIFY_COMPLETED]
     * @see [NOTIFIER_VISIBLE]
     * @see [NOTIFIER_HIDDEN]
     * @see [NOTIFIER_VISIBLE_NOTIFY_COMPLETED]
     * @see [NOTIFIER_VISIBLE_NOTIFY_ONLY_COMPLETION]
     */
    open fun setNotificationVisibility(@NotifierVisibility visibility: Int): Request {
        notificationVisibility = visibility
        return this
    }

    open fun setAllowedNetworkTypes(flags: Int): Request = this

    open fun setAllowedOverRoaming(allowed: Boolean): Request = this

    open fun setAllowedOverMetered(allow: Boolean): Request = this


    abstract fun buildDownloader(context: Context): Downloader<*>
}