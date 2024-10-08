plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.example.colorpickerlibs"
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation ("androidx.databinding:databinding-runtime:4.1.3")
}

//publishing {
//    publications {
//        create<MavenPublication>("release") {
//            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
//
//            groupId = "com.example"
//            artifactId = "colorpickerlibs"
//            version = "1.2.1"
//        }
//    }
//}

publishing {
    publications {
        create<MavenPublication>("release") {
//            artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
            artifact("$buildDir/outputs/aar/colorpickerlibs-release.aar")

            groupId = "com.github.manoranga"
            artifactId = "colorpicker"
            version = "1.2.9"
        }
    }
        repositories {
        mavenLocal()
    }
}

//publishing {
//    publications {
//        maven(MavenPublication) {
//            groupId = 'com.github.geek-atif'
//            artifactId = 'com-atifqamar-customtoast'
//            version = "1.0"
//            pom {
//                description = 'First release'
//            }
//        }
//    }
//    repositories {
//        mavenLocal()
//    }
//}