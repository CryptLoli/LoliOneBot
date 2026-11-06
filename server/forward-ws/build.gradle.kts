kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.server.core)
            api(libs.ktor.server.websockets)
            api(project(":server"))
        }
    }
}
