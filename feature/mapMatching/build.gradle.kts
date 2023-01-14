plugins {
    id("mookiehare.android.library.compose")
    id("mookiehare.android.feature")
}

android {
    namespace = "com.mookiehare.hohoi.feature.mapmatching"
}

dependencies {

    implementation(libs.maps.play.service)
    implementation(libs.maps.compose)
}
