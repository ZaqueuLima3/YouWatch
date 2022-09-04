apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.ui))

    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
}
