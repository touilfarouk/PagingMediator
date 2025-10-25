package gaur.himanshu.imagesearchapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the deliverer search app
 * Annotated with @HiltAndroidApp to enable Hilt dependency injection
 * This triggers Hilt's code generation and serves as the application-level dependency container
 */
@HiltAndroidApp
class BaseApplication : Application() {
}