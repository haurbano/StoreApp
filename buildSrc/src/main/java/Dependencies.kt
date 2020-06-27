
internal object Versions {
    const val kotlinVersion = "1.3.72"
    const val coreKtxVersion = "1.3.0"
    const val appCompatVersion = "1.1.0"
    const val jUnitVersion = "4.12"
    const val jUnitExtAndroidVersion = "1.1.1"
}

object Dependencies {
    const val kotlin        = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx       = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat     = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
}

object TestDependencies {
    const val jUnit             = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitExtAndroid   = "androidx.test.ext:junit:${Versions.jUnitExtAndroidVersion}"
}