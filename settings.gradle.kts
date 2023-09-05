pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
	kotlin("jvm") version "1.9.0" apply false
}

rootProject.name = "kotlin-java-compat"
include(":kotlin-java-compat")