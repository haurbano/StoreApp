# StoreApp

## Description
Search application to find best products on Mercadolibre Argentina

## Technologie:

### Libraries
- **Retrofit**
- **Gson**:
- **Android ViewModels**:
- **Android LiveData**:
- **Mckito:**
- **Koin**

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

## Architecture
- Using **Clean Architechture** for the whole application and using **MVVM** for the presentation layer.
