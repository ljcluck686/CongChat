package com.software.mywechat.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ImageUtils {
    private const val TAG = "ImageUtils"
    private const val IMAGE_DIR = "profile_images"
    private const val PROFILE_IMAGE_NAME = "profile_image.jpg"

    // 保存图片到本地（默认文件名）
    suspend fun saveImageToFile(context: Context, imageUrl: String): String? {
        return saveImageToFile(context, imageUrl, PROFILE_IMAGE_NAME)
    }

    // 保存图片到本地（自定义文件名）
    suspend fun saveImageToFile(context: Context, imageUrl: String, fileName: String): String? {
        try {
            // 创建存储目录
            val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), IMAGE_DIR)
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    Log.e(TAG, "创建目录失败: ${directory.absolutePath}")
                    return null
                }
            }

            // 创建文件
            val file = File(directory, fileName)

            // 使用Coil加载图片
            val imageLoader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .build()

            val result = (imageLoader.execute(request) as? SuccessResult)?.drawable ?: run {
                Log.e(TAG, "图片加载失败: $imageUrl")
                return null
            }

            val bitmap = (result as? android.graphics.drawable.BitmapDrawable)?.bitmap ?: run {
                Log.e(TAG, "无法转换为Bitmap: $imageUrl")
                return null
            }

            // 保存图片
            var outputStream: OutputStream? = null
            try {
                outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.flush()
                Log.d(TAG, "图片保存成功: ${file.absolutePath}")
                return file.absolutePath
            } catch (e: IOException) {
                Log.e(TAG, "保存图片失败: ${file.absolutePath}", e)
                return null
            } finally {
                outputStream?.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, "保存图片异常: $imageUrl", e)
            return null
        }
    }

    // 生成唯一文件名
    fun generateUniqueFileName(prefix: String = "image"): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "${prefix}_${timeStamp}.jpg"
    }

    // 获取本地图片文件
    fun getLocalImageFile(context: Context, fileName: String = PROFILE_IMAGE_NAME): File? {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), IMAGE_DIR)
        val file = File(directory, fileName)
        return if (file.exists()) file else null
    }

    // 获取图片URI（用于ContentProvider）
    fun getImageUri(context: Context, fileName: String = PROFILE_IMAGE_NAME): Uri? {
        val file = getLocalImageFile(context, fileName) ?: return null
        return try {
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )
        } catch (e: Exception) {
            Log.e(TAG, "获取图片URI失败", e)
            null
        }
    }

    // 检查本地是否有图片
    fun hasLocalImage(context: Context, fileName: String = PROFILE_IMAGE_NAME): Boolean {
        return getLocalImageFile(context, fileName)?.exists() == true
    }

    // 加载本地图片
    fun loadLocalImage(context: Context, fileName: String = PROFILE_IMAGE_NAME): Bitmap? {
        val file = getLocalImageFile(context, fileName) ?: return null
        return try {
            BitmapFactory.decodeFile(file.absolutePath)
        } catch (e: Exception) {
            Log.e(TAG, "加载本地图片失败: ${file.absolutePath}", e)
            null
        }
    }

    // 获取所有本地图片文件
    fun getAllLocalImages(context: Context): List<File> {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), IMAGE_DIR)
        return directory.listFiles()?.filter { it.isFile && it.name.endsWith(".jpg") } ?: emptyList()
    }

    // 删除本地图片
    fun deleteLocalImage(context: Context, fileName: String = PROFILE_IMAGE_NAME): Boolean {
        val file = getLocalImageFile(context, fileName)
        return if (file?.exists() == true) {
            val deleted = file.delete()
            Log.d(TAG, "删除图片: ${file.absolutePath}, 结果: $deleted")
            deleted
        } else {
            Log.d(TAG, "图片不存在，无需删除: $fileName")
            true
        }
    }
}