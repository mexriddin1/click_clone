plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinAndroidKsp)
}

android {
    namespace = "com.example.preseneter"
    compileSdk = 35

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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(project(":entity"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":usecase"))
    implementation(project(":common"))

    //dagger
    implementation(libs.voyager.hilt)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //view model
    implementation(libs.voyager.hilt)

}
