pluginManagement {
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Recetario"
include(":app")
include(":core")
include(":feature")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":feature:splash")
include(":feature:home")
include(":feature:detail_recipe")
include(":core:database")
include(":core:design")
include(":core:constants")
include(":core:preferences")
