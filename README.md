<p align="center">  
This Simple News Search App demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack libraries, Retrofit and Material Design based on MVVM architecture.

<p align="center">
</p>
<img src="https://github.com/user-attachments/assets/cf278b82-c91c-469a-b197-f2d269c50e7a" width="350" height="700"/>
<img src="https://github.com/user-attachments/assets/bb8e3ae6-a133-4401-9a3b-e4a1c284cc50" width="350" height="700"/>
<img src="https://github.com/user-attachments/assets/e7ecf1e4-6819-43c5-993c-e34bed346db8" width="350" height="700"/>
<img src="https://github.com/user-attachments/assets/249a8c04-a526-4414-92e2-7345a321efc5" width="350" height="700"/>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based, StateFlow, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Stateflow: An observable hot flow for emitting the latest data.
  - Kotlin Flow: A cold flow triggered by the collecting functions.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
  - Jetpack Compose for UI design layout.
- Architecture
  - MVVM Architecture (View - ViewModel - Model)
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Retrofit](https://github.com/square/retrofit): A networking library for fetching data over the internet
- [Turbine](https://github.com/cashapp/turbine): A small testing library for kotlinx.coroutines Flow.
- JUnit4 for Unit and Instrumentation Tests
- Material3 design guidelines
  
## Architecture
This app is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

The overall architecture here is composed of three layers; the presentation layer, the domain layer, the data layer according to the Clean Architecture paradigm, with another for navigation. Each layer has dedicated components and they have each different responsibilities, as defined below:

### Presentation Layer
The presentation layer consists of the ui components as well as the view models to control the lifecyle of the application.

### Data Layer
The data Layer consists of repositories, which include business logic, such as querying data from the remote data source. It follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>

### Domain
The domain layer consists mainly of repository interfaces, use cases and business logic as followed by the clean architecure principles.


## Prerequisites
To build this project, you require:

- Android Studio LadyBug
- Gradle or AGP 8.7.1
- Kotlin version 2.0.0,
- Clone the repository.
- Get your personalized News App [News App Api Key](https://newsapi.org) from the news api website and set the value as 'api_key' without quotes in your local.properties file and rebuild your project. The image image below shows how it should be done.

![Screenshot 2024-10-24 at 13 12 55](https://github.com/user-attachments/assets/001f41fa-634a-4235-8a3a-bc1ee1eecb89)
