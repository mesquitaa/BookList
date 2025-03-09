### 📖 BookApp - Clean Architecture & Multi-Module Android App
This project follows Clean Architecture with MVVM, MVI, Jetpack Compose, Retrofit, Koin, and Enum-Based Navigation to create a scalable, maintainable, and testable Android application.

### 🚀 Why This Approach?
1. **Multi-Module Architecture**
- ✅ Faster Builds – Reduces build times by separating concerns.
- ✅ Scalability – Modules can be extended or replaced independently.
- ✅ Reusability – Features can be reused in other projects.
2. **Clean Architecture & SOLID Principles**
- ✅ Layered Separation – UI, business logic, and data layers are independent.
- ✅ Easier Testing – Isolated modules make unit and UI testing more effective.
- ✅ Maintainability – Organized code structure simplifies debugging.
3. **Jetpack Compose + MVI (Model-View-Intent)**
- ✅ Declarative UI – Compose makes UI updates smoother.
- ✅ Unidirectional Data Flow – Prevents UI inconsistencies and state corruption.
4. **Koin for Dependency Injection**
- ✅ Lightweight & Readable – No need for complex Dagger setup.
- ✅ Improves Testability – Easily inject mock dependencies.
5. **Enum-Based Navigation**
- ✅ Type-Safe Navigation – Avoids hardcoded strings.
- ✅ More Readable & Maintainable – Enums make navigation structured.

### 📂 Project Structure
```
book-app/
├── app/                  # Main App Module
├── home/                 # Feature: Book List
├── details/              # Feature: Book Details
├── navigation/           # Navigation Module
├── ui-tests/             # UI Testing Module
```

### 📌 Features & Functionality ###
✅ **Book List & Details Navigation**
- Fetches books from a REST API using Retrofit.
- Displays a list of books using Jetpack Compose.

### 🛠️Technologies Used ###
- Multi-Module Android App
- Kotlin + Jetpack Compose
- MVVM + MVI + Clean Architecture
- Retrofit (Networking)
- Koin (Dependency Injection)
- Jetpack Navigation
- Unit & UI Testing


### 📌 How to Run the Project
1. Clone the Repository
```sh
git clone https://github.com/mesquitaa/BookList.git
cd BookList
```
Open in Android Studio
Run the App on an emulator or device.

### 📉 Potential Downsides of This Architecture
While this approach is great, there are some trade-offs:

1. **Increased Complexity**
   - Multiple modules require a well-defined dependency structure.
   - More boilerplate compared to a single-module app.
2. **Longer Initial Setup**
   - Setting up Koin, Navigation, Use Cases, and Multi-Module takes time.
   - New team members may require onboarding for Clean Architecture.
3. **Higher Code Overhead for Small Projects**
   - If the app has only a few screens, a monolithic architecture might be simpler.
