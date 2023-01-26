plugins {
    id("mookiehare.android.library.compose")
    id("mookiehare.android.feature")
}

android {
    namespace = "com.mookiehare.hohoi.feature.mapmatching"
}

dependencies {

    implementation(libs.maps.utils)
    implementation(libs.maps.play.service)
    implementation(libs.maps.compose)
    implementation(libs.maps.permissions)
    implementation(libs.location.play.service)
}
