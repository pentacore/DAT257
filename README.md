# DAT257 - Gyro

This is a repository for group Gyro in the course DAT257 Agile Software Development @ Chalmers.
Most of the files in the root directory can be ignored, they are mostly configuration and misc files.

## Gradle
This project uses gradle for dependency and build management, to add a dependency to a module of the project, this should be added to that modules `%MODULE%/build.gradle.kts`.

You can either use your IDE's integrated gradle toolkit or the gradle CLI for this.

### Adding a dependency
Adding a dependency is as easy as adding one of the following into the designated `build.gradle.kts` file in the dependencies field. \
These dependencies can either be kotlin dependencies or even java dependencies (Kotlin can use both types). \
The rows are formatted like this: `%TYPE_OF_DEPENDENCY%("%DEPENDENCY%:%VERSION%")` examples follows below.

#### For the end product
`implementation("ch.qos.logback:logback-classic:1.2.3")`

#### Only for the product tests
`testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.5.0")`

## Modules
### app
This folder is for the app frontend of the project, code should originate from the `/app/src/main/kotlin/dat257/gyro` folder

### backend
This folder is for the backend of the project, code should originate from the `/backend/src/main/kotlin/dat257/gyro` folder

### shared
This folder is for the code that can be shared by app and backend, to prevent having to reinvent the wheel so to speak, code should originate from the `/shared/src/main/kotlin/dat257/gyro` folder

## Importing the project (IntelliJ)
In the top left menu, pick `File > New > Project from Version Control` and copy the URL that you can find above by clicking the green "Code" button `https://github.com/pentacore/DAT257.git`, After this intelliJ will do some magic while it imports settings and whatnot while you sit back and wait.
