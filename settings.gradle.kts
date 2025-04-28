rootProject.name = "DevOps"

val remoteCacheUrl = System.getenv("GRADLE_REMOTE_CACHE_URL") ?: (extra.properties["GRADLE_REMOTE_CACHE_URL"] as? String)
val remoteCachePush = System.getenv("GRADLE_REMOTE_CACHE_PUSH") ?: (extra.properties["GRADLE_REMOTE_CACHE_PUSH"] as? String)
val remoteCacheUsername = System.getenv("GRADLE_REMOTE_CACHE_USERNAME") ?: (extra.properties["GRADLE_REMOTE_CACHE_USERNAME"] as? String)
val remoteCachePassword = System.getenv("GRADLE_REMOTE_CACHE_PASSWORD") ?: (extra.properties["GRADLE_REMOTE_CACHE_PASSWORD"] as? String)

if (remoteCacheUrl != null) {
    buildCache {
        remote<HttpBuildCache> {
            isAllowInsecureProtocol = true
            isAllowUntrustedServer = true

            url = uri(remoteCacheUrl)
            isPush = remoteCachePush.toBoolean()
            credentials {
                username = remoteCacheUsername
                password = remoteCachePassword
            }
        }
    }
}

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}