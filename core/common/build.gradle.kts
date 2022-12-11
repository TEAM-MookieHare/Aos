plugins {
    id("mookiehare.android.library")
    id("mookiehare.android.hilt")
}

android {
    namespace = "com.mookiehare.hohoi.core.common"
}

dependencies {
    testImplementation(project(":core:testing"))
}