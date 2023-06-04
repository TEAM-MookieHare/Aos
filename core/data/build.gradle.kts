plugins {
    id("mookiehare.android.library")
    id("mookiehare.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.mookiehare.hohoi.core.data"
}

dependencies {
    testImplementation(project(":core:testing"))
    implementation(libs.kotlin.serialization.json)
}