# Convention Plugins

`buildLogic` 폴더는 single source of truth 를 유지하는 데 
사용되는 일반적인 모듈 구성에 대한 프로젝트별 컨벤션 플러그인을 정의합니다.

이 접근 방식은 크게 다음을 기반을 두고 만들었습니다.
1. [https://developer.squareup.com/blog/herding-elephants/](https://developer.squareup.com/blog/herding-elephants/)
2. [https://github.com/jjohannes/idiomatic-gradle](https://github.com/jjohannes/idiomatic-gradle).

`buildLogic`에 convetion plugins을 셋팅함으로써 
`buildSrc` 디렉토리의 단점 없이 중복적인 build script setup, 지저분한 `subproject` 구성을 피할 수 있습니다.

`buildLogic`은 [`settings.gradle.kts`](../settings.gradle.kts)에 구성된 대로 root에 포함된 build입니다

`buildLogic` 내부에는 일반 모듈이 자체 구성에 사용할 수 있는 플러그인 집합을 정의하는 `convention` 모듈이 있습니다.

또한 `buildLogic`은 플러그인 간에 logic을 공유하는 데 사용되는 `Kotlin` 파일을 포함하고 있는데, 
이는 공유(Shared) 코드로 안드로이드 구성 요소(라이브러리 vs 애플리케이션)를 구성하는 데 사용을 하였습니다.

이러한 플러그인은 *additive* 및 *composible*하며 단일 책임만 수행 합니다.

또한 모듈은 필요한 플러그인만을 이용하여 구성을 할 수 있습니다.

공유(shared) 코드가 없는 모듈에 대한 일회성 logic이 있다면 
모듈별 설정으로 컨벤션 플러그인을 만드는 것보다 모듈의 `build.gradle`에서 직접 정의하는 것이 좋습니다.

현재 convention plugins의 list는 다음과 같습니다.

common Android and Kotlin options을 구성하는 plugin:
- [`mookiehare.android.application`](convention/src/main/kotlin/AndroidApplicationConventionPlugin.kt)
- [`mookiehare.android.library`](convention/src/main/kotlin/AndroidLibraryConventionPlugin.kt)

Jetpack Compose options을 구성하는 plugin:
- [`mookiehare.android.application.compose`](convention/src/main/kotlin/AndroidApplicationComposeConventionPlugin.kt)
- [`mookiehare.android.library.compose`](convention/src/main/kotlin/AndroidLibraryComposeConventionPlugin.kt)
  
Hilt opsions를 구성하는 plugin:
- [`mookiehare.android.hilt`](convention/src/main/kotlin/AndroidHiltConventionPlugin.kt)

Feature options를 구성하는 plugin:
- [`mookiehare.android.feature`](convention/src/main/kotlin/AndroidFeatureConventionPlugin.kt)