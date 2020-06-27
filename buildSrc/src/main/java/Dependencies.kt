
internal object Versions {
    const val kotlinVersion = "1.3.72"
    const val coreKtxVersion = "1.3.0"
    const val appCompatVersion = "1.1.0"
    const val jUnitVersion = "4.12"
    const val jUnitExtAndroidVersion = "1.1.1"
    const val koinVersion = "2.1.1"
    const val ktLintVersion = "0.33.0"
    const val retrofitVersion = "2.9.0"
    const val lifeCycleVersion = "2.2.0"
    const val recyclerViewVersion = "28.0.0"
    const val constrainLayoutVersion = "1.1.3"
    const val picassoVersion = "2.71828"
}

object Dependencies {
    const val kotlin                = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx               = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat             = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val koin                  = "org.koin:koin-core:${Versions.koinVersion}"
    const val koinAndroid           = "org.koin:koin-android:${Versions.koinVersion}"
    const val koinViewModel         = "org.koin:koin-androidx-viewmodel:${Versions.koinVersion}"
    const val ktLint                = "com.pinterest:ktlint:${Versions.ktLintVersion}"
    const val androidViewModel      = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleVersion}"
    const val liveData              = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleVersion}"
    const val recyclerView          = "com.android.support:recyclerview-v7:${Versions.recyclerViewVersion}"
    const val constrainLayout       = "androidx.constraintlayout:constraintlayout:${Versions.constrainLayoutVersion}"
    const val picasso               = "com.squareup.picasso:picasso:${Versions.picassoVersion}"

    const val retrofit              = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
}

object TestDependencies {
    const val jUnit             = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitExtAndroid   = "androidx.test.ext:junit:${Versions.jUnitExtAndroidVersion}"
}