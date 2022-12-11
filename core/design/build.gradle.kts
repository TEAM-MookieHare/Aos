plugins {
    id("mookiehare.android.library")
    id("mookiehare.android.library.compose")
    id("mookiehare.android.hilt")
}

android {
    namespace = "com.mookiehare.hohoi.core.design"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)

    testImplementation(project(":core:testing"))
}