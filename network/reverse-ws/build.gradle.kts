kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.server.core)
            api(libs.ktor.server.websockets)
            api(libs.ktor.serialization.kotlinx.json)

            implementation(rootProject)
        }
    }
}