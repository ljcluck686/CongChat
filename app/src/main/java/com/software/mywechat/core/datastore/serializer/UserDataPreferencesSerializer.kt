package com.software.mywechat.core.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.software.app.core.datastore.UserDataPreferences
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserDataPreferencesSerializer  @Inject constructor(): Serializer<UserDataPreferences> {
    override val defaultValue: UserDataPreferences = UserDataPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserDataPreferences =
        try {
            // readFrom是在子线程中的
            // readFrom is already called on the data store background thread
            ///writeTo同理
            UserDataPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: UserDataPreferences, output: OutputStream) {
        // writeTo is already called on the data store background thread
        t.writeTo(output)
    }
}