# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${ PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
project("ezezez83")
include_directories(
        ./
)
set(CMAKE_SYSTEM_NAME Android)
set(CMAKE_SYSTEM_VERSION 21)
set(CMAKE_ANDROID_ARCH_ABI arm64-v8a)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#
# In this top level CMakeLists.txt, ${CMAKE_PROJECT_NAME} is used to define
# the target library name; in the sub-module's CMakeLists.txt, ${PROJECT_NAME}
# is preferred for the same purpose.
#
# In order to load a library into your app from Java/Kotlin, you must call
# System.loadLibrary() and pass the name of the library defined here;
# for GameActivity/NativeActivity derived applications, the same library name must be
# used in the AndroidManifest.xml file.
add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        native-lib.cpp
        MyLoader.cpp
        dlfcn/local_dlfcn.c
        )
#add_library(otherso SHARED OtherSo.cpp)
# Specifies libraries CMake should link to your target library. You
# can link libraries from various origins, such as libraries defined in this
# build script, prebuilt third-party libraries, or Android system libraries.
target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log)


#[[
target_link_libraries(otherso
        # List libraries link to the target library
        android
        log)]]


add_library(ezezez01 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez01
        android
        log)

add_library(ezezez02 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez02
        android
        log)

add_library(ezezez03 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez03
        android
        log)

add_library(ezezez04 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez04
        android
        log)

add_library(ezezez5 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez5
        android
        log)

add_library(ezezez6 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez6
        android
        log)

add_library(ezezez7 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez7
        android
        log)

add_library(ezezez8 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez8
        android
        log)

add_library(ezezez9 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez9
        android
        log)

add_library(ezezez10 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez10
        android
        log)

add_library(ezezez11 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez11
        android
        log)

add_library(ezezez12 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez12
        android
        log)

add_library(ezezez13 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez13
        android
        log)

add_library(ezezez14 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez14
        android
        log)

add_library(ezezez15 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez15
        android
        log)

add_library(ezezez16 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez16
        android
        log)

add_library(ezezez17 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez17
        android
        log)

add_library(ezezez18 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez18
        android
        log)

add_library(ezezez19 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez19
        android
        log)

add_library(ezezez20 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez20
        android
        log)

add_library(ezezez21 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez21
        android
        log)

add_library(ezezez22 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez22
        android
        log)

add_library(ezezez23 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez23
        android
        log)

add_library(ezezez24 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez24
        android
        log)

add_library(ezezez25 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez25
        android
        log)

add_library(ezezez26 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez26
        android
        log)

add_library(ezezez27 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez27
        android
        log)

add_library(ezezez28 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez28
        android
        log)

add_library(ezezez29 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez29
        android
        log)

add_library(ezezez30 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez30
        android
        log)

add_library(ezezez31 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez31
        android
        log)

add_library(ezezez32 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez32
        android
        log)

add_library(ezezez33 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez33
        android
        log)

add_library(ezezez34 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez34
        android
        log)

add_library(ezezez35 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez35
        android
        log)

add_library(ezezez36 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez36
        android
        log)

add_library(ezezez37 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez37
        android
        log)

add_library(ezezez38 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez38
        android
        log)

add_library(ezezez39 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez39
        android
        log)

add_library(ezezez40 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez40
        android
        log)

add_library(ezezez41 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez41
        android
        log)

add_library(ezezez42 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez42
        android
        log)

add_library(ezezez43 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez43
        android
        log)

add_library(ezezez44 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez44
        android
        log)

add_library(ezezez45 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez45
        android
        log)

add_library(ezezez46 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez46
        android
        log)

add_library(ezezez47 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez47
        android
        log)

add_library(ezezez48 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez48
        android
        log)

add_library(ezezez49 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez49
        android
        log)

add_library(ezezez50 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez50
        android
        log)

add_library(ezezez51 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez51
        android
        log)

add_library(ezezez52 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez52
        android
        log)

add_library(ezezez53 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez53
        android
        log)

add_library(ezezez54 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez54
        android
        log)

add_library(ezezez55 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez55
        android
        log)

add_library(ezezez56 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez56
        android
        log)

add_library(ezezez57 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez57
        android
        log)

add_library(ezezez58 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez58
        android
        log)

add_library(ezezez59 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez59
        android
        log)

add_library(ezezez60 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez60
        android
        log)

add_library(ezezez61 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez61
        android
        log)

add_library(ezezez62 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez62
        android
        log)

add_library(ezezez63 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez63
        android
        log)

add_library(ezezez64 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez64
        android
        log)

add_library(ezezez65 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez65
        android
        log)

add_library(ezezez66 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez66
        android
        log)

add_library(ezezez67 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez67
        android
        log)

add_library(ezezez68 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez68
        android
        log)

add_library(ezezez69 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez69
        android
        log)

add_library(ezezez70 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez70
        android
        log)

add_library(ezezez71 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez71
        android
        log)

add_library(ezezez72 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez72
        android
        log)

add_library(ezezez73 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez73
        android
        log)

add_library(ezezez74 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez74
        android
        log)

add_library(ezezez75 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez75
        android
        log)

add_library(ezezez76 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez76
        android
        log)

add_library(ezezez77 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez77
        android
        log)

add_library(ezezez78 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez78
        android
        log)

add_library(ezezez79 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez79
        android
        log)

add_library(ezezez80 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez80
        android
        log)

add_library(ezezez81 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez81
        android
        log)

add_library(ezezez82 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez82
        android
        log)

add_library(ezezez84 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez84
        android
        log)

add_library(ezezez85 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez85
        android
        log)

add_library(ezezez86 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez86
        android
        log)

add_library(ezezez87 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez87
        android
        log)

add_library(ezezez88 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez88
        android
        log)

add_library(ezezez89 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez89
        android
        log)

add_library(ezezez90 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez90
        android
        log)

add_library(ezezez91 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez91
        android
        log)

add_library(ezezez92 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez92
        android
        log)

add_library(ezezez93 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez93
        android
        log)

add_library(ezezez94 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez94
        android
        log)

add_library(ezezez95 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez95
        android
        log)

add_library(ezezez96 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez96
        android
        log)

add_library(ezezez97 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez97
        android
        log)

add_library(ezezez98 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez98
        android
        log)

add_library(ezezez99 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez99
        android
        log)

add_library(ezezez100 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez100
        android
        log)

add_library(ezezez101 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez101
        android
        log)

add_library(ezezez102 SHARED
        fake.cpp
        dlfcn/local_dlfcn.c
        MyLoader.cpp
        )

target_link_libraries(ezezez102
        android
        log)

