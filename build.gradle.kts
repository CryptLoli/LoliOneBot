plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.vanniktech.maven.publish)
    alias(libs.plugins.signing)
}

group = "io.github.cryptloli"
version = project.property("version") as String

allprojects {
    group = rootProject.group
    version = rootProject.version

    apply(plugin = "org.jetbrains.kotlin.multiplatform")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    apply(plugin = "com.vanniktech.maven.publish")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    kotlin {
        jvm()
        linuxX64()
        linuxArm64()
        mingwX64()
        macosArm64()
        js {
            browser()
            nodejs()
        }
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.serialization.kotlinx.json)
        }
    }
}

val isRelease = System.getenv("GITHUB_REF_TYPE") == "tag"
val privateKey = let {
    val key = System.getenv("GPG_PRIVATE_KEY")
        ?.replace("\\n", "\n")
        ?.takeIf { it.isNotBlank() }
        ?: return@let null
    val password = System.getenv("GPG_PASSWORD")
        ?.takeIf { it.isNotBlank() }
        ?: return@let null

    Pair(key, password)
}

allprojects {
    mavenPublishing {
        if (isRelease) publishToMavenCentral()
        publishing {
            repositories {
                mavenLocal()
            }
        }
        if (privateKey != null) signAllPublications()
        coordinates("io.github.cryptloli", rootProject.name + if (project != rootProject) "-${project.name}" else "", project.version.toString())
        pom {
            name.set("LoliOneBot")
            description.set("A Kotlin multiplatform library for WPLauncher api.")
            url.set("https://github.com/CryptLoli/LoliOneBot")
            licenses {
                license {
                    name.set("Zero-Clause BSD License")
                    url.set("https://opensource.org/license/0bsd")
                }
            }
            developers {
                developer {
                    id.set("nekocurit")
                    name.set("nekocurit")
                }
            }
            scm {
                connection.set("scm:git:https://github.com/CryptLoli/LoliOneBot.git")
                developerConnection.set("scm:git:ssh://git@github.com/CryptLoli/LoliOneBot.git")
                url.set("https://github.com/CryptLoli/LoliOneBot")
            }
        }
    }

    privateKey?.also { (key, password) ->
        signing {
            useInMemoryPgpKeys(key, password)
            sign(publishing.publications)
        }
    }
}