kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.client.core)
            api(libs.ktor.client.websockets)
            api(project(":server"))
        }
    }
}
