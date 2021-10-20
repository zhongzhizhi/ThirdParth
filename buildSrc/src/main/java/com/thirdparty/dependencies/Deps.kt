package com.thirdparty.dependencies


/**
 *  @项目名：  HappyChat
 *  @包名：    com.thirdparty.dependencies
 *  @文件名:   Deps
 *  @创建者:   Administrator
 *  @创建时间:  2021/10/20 10:10
 *  @描述：    TODO
 */


object Versions {
    const val compileSdk = 31
    const val buildTools = "29.0.3"
    const val minSdk = 16
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.5.10"
    const val coroutines = "1.3.9"
    const val coroutines_android = "1.3.9"
    const val androidxArch = "2.0.0"
    const val mockito = "2.23.0"
    const val anko = "0.10.8"

    const val room = "2.3.0-alpha02"

    const val appcompat = "1.2.0"
    const val constraintLayout = "2.0.1"
    const val retrofit = "2.8.1"
    const val retrofit_converter_gson = "2.8.1"
    const val retrofit_adapter_rxjava = "2.8.1"
    const val okhttp_logging_interceptor = "4.0.0"
    const val rxandroid_version = "2.1.1"
    const val swipeRefreshLayout = "1.1.0-rc01"
    const val material = "1.2.1"
    const val circleImageview = "2.2.0"
    const val leakcanary = "2.0-alpha-3"
    const val baseRecyclerViewAdapterHelper = "2.9.50"
    const val banner = "1.4.10"
    const val glide = "4.11.0"
    const val glide_compiler = "4.11.0"
    const val cardView = "1.0.0"
    const val verticalTabLayout = "1.2.5"
    const val flowLayout = "1.1.2"
    const val persistentCookieJar = "v1.0.1"
    const val licensesDialog = "2.1.0"
    const val material_dialogs = "3.3.0"
    const val livedata_ktx = "2.2.0"
    const val viewPager2 = "1.0.0"
    const val koin = "2.0.1"
    const val core_ktx = "1.3.1"
    const val navigation = "2.2.2"
    const val recyclerView = "1.1.0"

    const val rxpermisson = "0.12"
    const val smart_refresh = "2.0.1"
    const val rxjava_version = "3.1.2"
    const val fastjson_version = "1.2.60"

    //jetpack
    const val lifecycle_runtime_version = "2.2.0"
    const val lifecycle_runtime_ktx_version = "2.3.0-alpha05"
    const val lifecycle_common_version = "2.2.0"
    const val lifecycle_extensions_version = "2.2.0"
    const val lifecycle_viewmodel_version = "2.2.0"
    const val lifecycle_viewmodel_ktx_version = "2.3.0-alpha05"
    const val lifecycle_livedata_version = "2.2.0"
    const val lifecycle_livedata_ktx_version = "2.3.0-alpha05"
}

object Deps {
    // androidx
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    // kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"

    //coroutines
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_android}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"

    //room
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

    //jetpack
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_runtime_version}"
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_runtime_ktx_version}"
    const val lifecycle_common_java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_common_version}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extensions_version}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycle_viewmodel_version}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_viewmodel_ktx_version}"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle_livedata_version}"
    const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_livedata_ktx_version}"


    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val retrofit_adapter_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_adapter_rxjava}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid_version}"
    const val persistentCookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.persistentCookieJar}"

    // third
    const val circleimageview = "de.hdodenhof:circleimageview:${Versions.circleImageview}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val baseRecyclerViewAdapterHelper =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelper}"
    const val banner = "com.youth.banner:banner:${Versions.banner}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val verticalTabLayout = "q.rorbin:VerticalTabLayout:${Versions.verticalTabLayout}"
    const val flowLayout = "com.hyman:flowlayout-lib:${Versions.flowLayout}"
    const val licensesDialog = "de.psdev.licensesdialog:licensesdialog:${Versions.licensesDialog}"
    const val material_dialogs_core = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
    const val material_dialogs_input = "com.afollestad.material-dialogs:input:${Versions.material_dialogs}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_androidx_scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val rxpermission = "com.github.tbruyelle:rxpermissions:${Versions.rxpermisson}"
    const val smart_refresh_kernel = "com.scwang.smart:refresh-layout-kernel:${Versions.smart_refresh}"
    const val smart_refresh_footer = "com.scwang.smart:refresh-footer-classics:${Versions.smart_refresh}"
    const val smart_refresh_header = "com.scwang.smart:refresh-header-classics:${Versions.smart_refresh}"
    const val rxjava = "io.reactivex.rxjava3:rxjava:${Versions.rxjava_version}"
    const val fastjson = "com.alibaba:fastjson:${Versions.fastjson_version}"

    //my
    const val logger = "com.dosmono.library:logger:1.1.6"
}