# HarmonyOSWeatherApp

## What is Clean Architecture?
Clean architecture is a set of principles whose main purpose is to hide implementation details from the application's domain logic.
In this way we keep the logic isolated, achieving a much more maintainable and scalable logic over time.

![Clean Architecture](assets/cleanarchitecture.jpg?raw=true "Clean Architecture")

The inner layers should not know anything about the outer layers. Inner layers contain business logic, whereas the outer layers contain implementation and the middle layer contain Interface Adapters. Each ring represent one layer of abstraction.

Some key technical benefits achieved in this way are:

* Abstraction over Implementation
* Single Responsibility Principle
* Separation of Concern
* Decoupled Code

For more information on Clean Architecture please visit the following URL:[Uncle bob´s blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

## Model View Presenter

The MVP pattern separates the presentation layer from the business logic, draws a clear line between how the interface works and how we display it, and decouples the two.

**Model**: It is a data access layer for managing data. It can be an interface which is responsible for accessing APIs and local database or cache etc. It can be an interactor (if using Uncle Bob Clean Architecture) or repository (if using Repository Pattern) depends on which type of pattern are you.

**View**: It is the only layer for presenting data and reacting to user actions (a button click for example). In case of android, View can be implemented by activity, fragment, widgets or any custom view. This view will contain a reference to the presenter and call a method from presenter.

**Presenter**: This layer works as a middle-man between view and model. It fetches data from the model layer, format the data and return to the view. It also reacts to user interactions through view interface and update the model.

Our project will have the following architecture
![Project architecture](assets/project_architecture.png?raw=true "Project architecture")

The structure of the Project is modularized, we have the View layer in a Module of type Empty Feature Ability (Java), and both Domain and Data will be Modules of type Java Library

The Demo Code consist in a Weather Application; to get the weather information I consumed the REST APIs of: [OpenWeatherMap](https://openweathermap.org/) using Retrofit and RxJava 3.

We also use Rxohos: Reactive Extensions of openharmony specific bindings for RxJava 3.

Rxohos adds the minimum classes to RxJava that make writing reactive components in openharmony applications easy and hassle-free. More specifically, it provides a Scheduler that schedules on the main thread or any given EventRunner.

To have more code confidence and enforce layer separation we could create separate modules (Gradle subprojects) for each CA layer and define dependencies between them.

The structure of the project will be as follows:

![Project structure](assets/project_structure.png?raw=true "Project structure")

By separating the layers into different modules, we have a little more scalability of the project, the data and domain layer could be reused for an AOSP project. 

![Project IDE structure](assets/estructura.png?raw=true "Project IDE structure")

## Entry
Entry module is the Presentation Layer, presents data to a screen and handle user interactions.

In this implementation, we’re using a Contract interface, which in turn consists of two separate interfaces - View and Presenter. Our View class (MainAbility.java) directly implements the View interface, which performs UI-related work. In order to process all of the necessary View events (such as button clicking, etc.), the Presenter class (WeatherDetailPresenter.java) implements the Presenter interface.

## Domain
The Domain Layer contains the business logic. Use case will ask the Data layer to send back the result. The use cases orchestrate the flow of data to and from the entities, and direct those entities to use their enterprise wide business rules to achieve the goals of the use case.

## Data 
Data layer manages application data, i.e. retrieve data from the network, manage data cache or from a DB.

Once we have installed the application you will see the following:
![App](assets/Capture.PNG?raw=true "Weather App")
![App2](assets/Capture2.PNG?raw=true "Weather App 2")

References:

OpenWeatherMap:  [https://openweathermap.org/](https://openweathermap.org/)
Uncle Bob blog:  [https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
Retrofit:  [https://square.github.io/retrofit/](https://square.github.io/retrofit/)
RxJava:  [https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
Rxohos:  [https://gitee.com/openharmony-tpc/Rxohos/tree/1.x/](https://gitee.com/openharmony-tpc/Rxohos/tree/1.x/)
Theme Setting: [https://www.jianshu.com/p/1e81a782668a](https://www.jianshu.com/p/1e81a782668a)
Demo Code: [https://github.com/jordanrsas/HarmonyOSWeatherApp](https://github.com/jordanrsas/HarmonyOSWeatherApp)
