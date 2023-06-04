plugins {
    id("mookiehare.android.library")
    id("mookiehare.android.hilt")
}

android {
    namespace = "com.mookiehare.hohoi.core.dataSourceRemote.rscoket"
    defaultConfig {
        buildConfigField("String", "RSOCKET_PATH", getLocalProperty("RSOCKET_PATH"))
        buildConfigField("String", "RSOCKET_PORT", getLocalProperty("RSOCKET_PORT"))
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes{
        debug {
            buildConfigField("String", "RSOCKET_ADDRESS", getLocalProperty("LOCAL_RSOCKET_ADDRESS"))
        }

        release {
            buildConfigField("String", "RSOCKET_ADDRESS", getLocalProperty("PROD_RSOCKET_ADDRESS"))
        }
    }
}

dependencies {
    implementation(project(":core:data"))
    testImplementation(project(":core:testing"))
    implementation(libs.rsocket.android.client)
    implementation(libs.rsocket.ktor.client)
    implementation(libs.rsocket.ktor.client.cio)
    implementation(libs.kotlin.serialization.json)
}