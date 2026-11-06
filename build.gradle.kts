plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.vanniktech.maven.publish)
    alias(libs.plugins.signing)
}

group = project.property("group") as String
version = project.property("version") as String
val repo = project.property("repo") as String

allprojects {
    group = rootProject.group
    version = rootProject.version

    apply {
        plugin("org.jetbrains.kotlin.multiplatform")
        plugin("org.jetbrains.kotlin.plugin.serialization")
        plugin("com.vanniktech.maven.publish")
        plugin("maven-publish")
    }

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
        coordinates(rootProject.group.toString(), rootProject.name + if (project != rootProject) "-${project.name}" else "", project.version.toString())
        pom {
            name.set("LoliOneBot")
            description.set("A Kotlin multiplatform library.")
            url.set("https://github.com/$repo")
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
                connection.set("scm:git:https://github.com/$repo.git")
                developerConnection.set("scm:git:ssh://git@github.com/$repo.git")
                url.set("https://github.com/$repo")
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