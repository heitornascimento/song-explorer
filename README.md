# Song Explorer - Android App

## Tech stack
- 100% Kotlin code
- Clean Multimodular Architecture
- Google recommended app design - View - Domain - Data
- Coroutines
- State Flow for reactive programming
- Retrofit - Kotlin Serialization
- Dagger Hilt
- Test - Mockk and Turbine for StateFlow
- TOML dependency formatting

## Demo

[v3.webm](https://user-images.githubusercontent.com/121907/209259717-aba8ed29-fd3a-4912-9867-063124333f7d.webm)


## Architecture

![App Arch - Clean Code (4)](https://user-images.githubusercontent.com/121907/209257935-a2f1da63-16a7-42aa-b477-ba0b9c97679a.jpg)


## Considerations:

I have completed the minimum requirements requested on the assignment.

1. Api call restricts to Denmark country. I also limited the API to media-music typed only.

2. Fields properties. For some song title, the short description is nullable, that is why the properties is optional

3. Screen size - It is always a challenge to fit different screen sizes. I have applied small-normal-large-extralarge folder resource to encapsulate the dimensions for the view, such as Height and TextFont values

4. Due to lack of time I have covered with test the presentation view, I cover ViewModel with one scenario. Domain and Data can be easily covered as well.
In particular for date layer, I would mockwebserver to mock the http request.

5. I have added the navigation graph as a bonus. I would replace this with another feature module called SongDetails and the app follows feature-level modularization.


#### Hacking on the artwork url:

According to the API, the endpoint only provides  the size of the image. But all of them are are very small. So I have changed the path to bigger image size. In a real world scenario, the app should only retrieve images proper for your screen dimension and size.

#### Improvements

That is always room for improvement. I would cover more test cases in all layers, View - Domain - Data. Using the popular libraries such mockk, junit, mockwebser and espresso.

I would create more libraries on the UI level for the extensions.

The UI can be improved as well. Add more Material Design patterns and components.

Jetpack compose is the hottest topic in the Android world. It is on my study path.
