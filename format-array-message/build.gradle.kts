kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":client"))
        }
    }
}