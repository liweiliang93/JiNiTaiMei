    plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("com.google.devtools.ksp")
    }
    
    android {
        namespace = "com.weiliang.jinitaimei"
        compileSdk = 34
    
        defaultConfig {
            applicationId = "com.weiliang.jinitaimei"
            minSdk = 21
            targetSdk = 34
            versionCode = 1
            versionName = "1.0"
    
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
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
        kotlin {
            jvmToolchain(8)
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        
    }
    
    
    dependencies {
        
        
        
        implementation("androidx.room:room-common:2.5.2")
        //ROOM
        val roomVersion = "2.5.2"
        
        implementation("androidx.room:room-runtime:$roomVersion")
        ksp("androidx.room:room-compiler:$roomVersion")
        // optional - Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$roomVersion")
        // optional - RxJava2 support for Room
        implementation("androidx.room:room-rxjava2:$roomVersion")
        // optional - RxJava3 support for Room
        implementation("androidx.room:room-rxjava3:$roomVersion")
        // optional - Guava support for Room, including Optional and ListenableFuture
        implementation("androidx.room:room-guava:$roomVersion")
        // optional - Test helpers
        testImplementation("androidx.room:room-testing:$roomVersion")
        // optional - Paging 3 Integration
        implementation("androidx.room:room-paging:$roomVersion")
        // Dagger Hilt for dependency injection
        implementation("com.google.dagger:hilt-android:2.48")
        
        // Retrofit and OkHttp
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:okhttp:4.11.0")
        
        //ViewModel
        implementation("androidx.lifecycle:lifecycle-livedata:2.6.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        
        //Navigation
        implementation("androidx.navigation:navigation-compose:2.6.0")
        implementation("com.google.accompanist:accompanist-navigation-animation:0.28.0")
        
        //activity
        implementation("androidx.core:core-ktx:1.10.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        implementation("androidx.activity:activity-compose:1.7.2")
        
        //GIF
        implementation("io.coil-kt:coil-compose:2.4.0")
        implementation("io.coil-kt:coil-svg:2.4.0")
        implementation("io.coil-kt:coil-gif:2.4.0")
        implementation("com.github.skydoves:landscapist-coil:2.2.8")
        implementation("com.github.skydoves:landscapist-glide:2.2.8")
        implementation("com.github.skydoves:cloudy:0.1.2")
        
        //Jetpack Compose
        implementation("androidx.compose.ui:ui:1.5.0")
        implementation("androidx.compose.material:material:1.5.0")
        implementation("androidx.compose.runtime:runtime-livedata:1.5.0")
        implementation("androidx.compose.ui:ui-tooling:1.5.0")
        
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
        implementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        
        implementation("com.android.volley:volley:1.2.1")
        implementation("com.google.firebase:firebase-database-ktx:20.2.2")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        
    }