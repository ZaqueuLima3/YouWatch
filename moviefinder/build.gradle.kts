apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.database))
    "implementation"(project(Modules.ui))

    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.okHttpLoggingInterceptor)

    "implementation"(Moshi.moshi)
    "implementation"(Moshi.kotlinMoshi)
    "kapt"(Moshi.moshiCodegen)

    "implementation"(Paging.pagingCompose)
}
