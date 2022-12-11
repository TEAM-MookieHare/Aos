plugins {
    id("mookiehare.android.library")
    id("mookiehare.android.hilt")
}

android {
    namespace = "com.mookiehare.hohoi.core.domain"
}

dependencies {
    testImplementation(project(":core:testing"))
}