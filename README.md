# StoreApp

## Description
Search application to find best products on Mercadolibre Argentina

## Technologie:

### Libraries
- **Retrofit**: Requests
- **Gson**: Map responses from requests
- **Android ViewModels**
- **Android LiveData**: UI updates from ViewModel to Activity
- **Mckito:** Mocks in testing
- **Koin**: Dependency injection

### Background Strategy:
- **Kotlin Coroutines**

### Code Quiality
- **Code Style**: Added task using [ktlint](https://github.com/pinterest/ktlint) to keep a good code styel. The code style task is run always before the build task.

- **Unit Testing**: Created unit tests for the following modules using Mockito and Junit.
	- **data** module
	- **remotedatasource** module
	- **domain** module

###  Thrid Party Dependencies
- **BuildSrc Strategy**: [Blog](https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28)

### Recuperation strategy of views
I'm using **Kotlin Synthetic** and **View Binding** to show the both approaches.

## Architecture
- Using **Clean Architechture** for the whole application and using **MVVM** for the presentation layer.

![Architecture diagram](https://raw.githubusercontent.com/haurbano/StoreApp/master/Architecture%20Diagram.png)


