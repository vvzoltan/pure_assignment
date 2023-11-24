## General remarks

This sample project intends to highlight my technical experience and demonstrate my knowledge of the Android technology stack (Kotlin, Compose, Corutines, Room…). I did my best to follow an MVI architecture, however please keep in mind that I've never worked in MVI before so I am not entirely familiar with the best practices (I have experience with MVC and MVVM), if there are some not so good practices in there I look forward to discussing them during our next interview. I tried to respect Clean Architecture and SOLID principles and to keep everything simple and easily understandable. Tests were not mentioned in the specifications, however I added a couple just to showcase my ability in unit testing (repository implementation).


## Tech stack
Kotlin, Compose, Navigation, Coroutines, Retrofit, Room, Coil, Dagger (JUnit, Mockk, Faker for testing)


## Known issues
- in terms of error handling, I just update the text message accordingly, however I do map the error into a custom type (PureError)
- I noticed some lagging from time to time when opening the drinks list, I believe this to be because of the many shimmer effects playing at the same time
- When tapping the retry button in case of an error, the list of "shimmered placeholders" flash on the screen as a result of the app attempting to load the data again. I believe this to be a UX/design issue rather than a coding issue; keeping the placholders while showing the error seems overwhelming for the user, and coming up with a more complex solution seemed to be out of the scope of this exercise