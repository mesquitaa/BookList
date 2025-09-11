plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "com.rpm.integration"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  testOptions {
    animationsDisabled = true
    unitTests.isReturnDefaultValues = true
    unitTests.all {
      it.useJUnitPlatform()
    }
  }
}

dependencies {
  implementation(libs.androidx.foundation)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.activity.compose)
  androidTestImplementation(libs.androidx.navigation.compose)
  androidTestImplementation(libs.ui.test.junit4.android)
  androidTestImplementation(libs.ui.test.junit4)
  androidTestImplementation(libs.ui.test.koin)
  androidTestImplementation(libs.ui.test.koin.junit4)
  androidTestImplementation(libs.ui.test.espresso)
  androidTestImplementation(libs.ui.test.espresso.intents)
  androidTestImplementation(libs.ui.test.espresso.contrib)
  androidTestImplementation(libs.ui.test.mockwebserver)
  androidTestImplementation(libs.retrofit)
  androidTestImplementation(libs.retrofit.converter.gson)
  androidTestImplementation(libs.ui.test.runner)
  debugImplementation(libs.ui.test.manifest)

  // Module dependencies
  implementation(project(":navigation"))
}
