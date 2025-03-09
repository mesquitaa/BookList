plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "com.rpm.navigation"
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  testOptions {
    unitTests.isReturnDefaultValues = true
    unitTests.all {
      it.useJUnitPlatform()
    }
  }
}

dependencies {
  api(project(":home"))
  api(project(":details"))
  api(platform(libs.androidx.compose.bom))
  api(libs.androidx.navigation.compose)
  api(libs.koin)
}
