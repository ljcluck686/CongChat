import com.google.protobuf.gradle.GenerateProtoTask

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //kotlin序列化
    kotlin("plugin.serialization") version "1.9.23"

    //依赖注入
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

    //protobuf插件
    alias(libs.plugins.protobuf)
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
    implementation (libs.pinyin4j)
    implementation(libs.kotlinx.datetime)
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

    //数据存储
    implementation("androidx.datastore:datastore:1.0.0")

    implementation(libs.protobuf.kotlin.lite)

    //二维码生成扫描框架
    //https://github.com/jenly1314/ZXingLite
    implementation("com.github.jenly1314:zxing-lite:3.1.0")

    //ML KIT,机器学习套件是一个移动 SDK，可将 Google 的设备端机器学习专业知识融入到 Android 和 iOS 应用中
    //https://developers.google.com/ml-kit/vision/barcode-scanning/android?hl=zh-cn
    implementation("com.google.mlkit:barcode-scanning:17.2.0")

    //region emo框架
    //提供很多功能,例如:图片选择,预览,网络状态监听等
    //https://github.com/cgspine/emo-public
    implementation(platform("cn.qhplus.emo:bom:2023.08.00"))

    // 默认使用 coil 作为图片加载器
    implementation("cn.qhplus.emo:photo-coil")
    // 可选：如果需要使用其它库，则引入 photo 库，自定义实现 PhotoProvider 即可
    implementation("cn.qhplus.emo:photo")
    //endregion
    //阿里云服务
    implementation ("com.aliyun.dpa:oss-android-sdk:2.9.21")
    //通用IO相关工具类
    //http://commons.apache.org/proper/commons-io/
    implementation("commons-io:commons-io:2.0")

}

//protobuf生成文件配置
protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

//https://github.com/google/dagger/issues/4097#issuecomment-1763781846
androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val protoTask =
                project.tasks.getByName("generate" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Proto") as GenerateProtoTask

            project.tasks.getByName("ksp" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Kotlin") {
                dependsOn(protoTask)
                (this as org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>).setSource(
                    protoTask.outputBaseDir
                )
            }
        }
    }
}

