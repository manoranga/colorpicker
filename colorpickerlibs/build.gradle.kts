plugins {
    id("com.android.library")
    id("maven-publish")
    id("signing")

}

android {
    namespace = "com.pmr.colorpickerlibs"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }


}

afterEvaluate {
    println("Available components: ${components.names.joinToString()}")
    publishing {
        publications {
            create<MavenPublication>("release") {
                val releaseComponent = components.findByName("release")
                if (releaseComponent != null) {
                    from(releaseComponent)
                } else {
                    logger.warn("Release component not found")
                }

                groupId = project.findProperty("POM_GROUP_ID") as String
                artifactId = project.findProperty("POM_ARTIFACT_ID") as String
                version = project.findProperty("POM_VERSION") as String

                pom {
                    name.set("Android Color Picker")
                    description.set("A brief description of your library")
                    url.set("https://github.com/manoranga/colorpicker")

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("manoranga")
                            name.set("Prabodha Ranasinghe")
                            email.set("prabodhamanoranga@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com//manoranga/colorpicker.git")
                        developerConnection.set("scm:git:ssh://github.com:/manoranga/colorpicker.git")
                        url.set("https://github.com//manoranga/colorpicker")
                    }
                }
            }
        }
    }
    signing {
        sign(publishing.publications["release"])
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("androidx.databinding:databinding-runtime:4.1.3")
}