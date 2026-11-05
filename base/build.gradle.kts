kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.serialization.kotlinx.json)
        }
    }
}