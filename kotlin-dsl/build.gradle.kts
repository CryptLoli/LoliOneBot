kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":client"))
            api(project(":format-array-message"))
        }
    }
}