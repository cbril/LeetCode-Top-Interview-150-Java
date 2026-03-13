plugins {
    id("java")
    id("com.diffplug.spotless") version "8.3.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        googleJavaFormat().aosp()
        removeUnusedImports()
        forbidWildcardImports()
        importOrder()
        leadingTabsToSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
