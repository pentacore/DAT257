# DAT257 - Gyro

This is a repository for group Gyro in the course DAT257 Agile Software Development @ Chalmers.
Most of the files in the root directory can be ignored, they are mostly configuration and misc files.

## Gradle
This project uses gradle for dependency and build management, to add a dependency to a module of the project, this should be added to that modules `build.gradle.kts`.

You can either use your IDE's integrated gradle toolkit or the gradle CLI for this.

## Modules
### app
This folder is for the app frontend of the project, code should originate from the `src/main/kotlin/dat257/gyro` folder

### backend
This folder is for the backend of the project, code should originate from the `src/main/kotlin/dat257/gyro` folder

### shared
This folder is for the code that can be shared by app and backend, to prevent having to reinvent the wheel so to speak, code should originate from the `src/main/kotlin/dat257/gyro` folder

## Importing the project (IntelliJ)
In the top left menu, pick `File > New > Project from Version Control` and copy the URL that you can find above by clicking the green "Code" button `https://github.com/pentacore/DAT257.git`, After this intelliJ will do some magic while it imports settings and whatnot while you sit back and wait.
