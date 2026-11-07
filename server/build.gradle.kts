kotlin {
    sourceSets {
        commonMain.dependencies {
            api(project(":base"))
            api(project(":events"))
        }
    }
}
