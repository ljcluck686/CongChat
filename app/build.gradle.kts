plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //kotlin序列化
    kotlin("plugin.serialization") version "1.9.23"

    //依赖注入
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.software.mywechat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.software.mywechat"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation ("com.belerweb:pinyin4j:2.5.1")
    implementation(libs.androidx.compose.material.iconsExtended)
    //导航栏
    implementation (libs.androidx.navigation.compose)
    //kotlin序列化
    implementation(libs.kotlinx.serialization.json)
    //okhttp框架
    implementation(libs.okhttp)
    //网络框架日志框架
    implementation(libs.logging.interceptor)
    //类型安全网络框架
    implementation(libs.retrofit)
    //让Retrofit支持Kotlinx Serialization
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    //图片加载框架
    implementation(libs.coil.compose)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    //region 依赖注入
    //https://developer.android.google.cn/training/dependency-injection/hilt-android?hl=zh-cn
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kspAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    //endregion

    compileOnly(libs.ksp.gradlePlugin)
}