kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(rootProject)
            implementation(project(":format-array-message"))
        }
    }
}