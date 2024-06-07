pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CalorieBytez"
include(":app")
include(":domain")
include(":core:network")
include(":core:database")
include(":core:data")
include(":feature:search")
include(":feature:foodDetails")
include(":feature:savedItems")
include(":core:common-ui")
include(":core:testing")
