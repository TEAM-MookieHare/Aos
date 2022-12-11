plugins {
    id("mookiehare.android.library")
    id("mookiehare.android.hilt")
}

android {
    namespace = "com.mookiehare.hohoi.core.dataSourceRemote.http"
}

dependencies {
    testImplementation(project(":core:testing"))
}