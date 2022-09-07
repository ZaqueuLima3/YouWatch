apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}
