pluginManagement {
    includeBuild("buildLogic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "hohoi"
include(":app-hohoi")
include(":core:domain")
include(":core:design")
include(":core:common")
include(":core:data")
include(":core:di")
include(":core:dataSourceLocal:room")
include(":core:dataSourceRemote:http")
include(":core:testing")
include(":core:model")
include(":feature:chat")
include(":feature:myPage")
include(":feature:randomMatching")
include(":feature:mapMatching")
include(":core:dataSourceRemote:rsocket")
