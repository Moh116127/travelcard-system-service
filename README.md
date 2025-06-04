# Al-Naqel Fare Card System

## Overview
The Al-Naqel Fare Card System is a travel card management solution developed by UAE SMART TRAVEL Limited. It enables users to register and load travel cards, perform trips across different zones, and view their balance. The system handles fare calculations based on zones traveled and transport types.

## Features
- Travel card registration and balance management
- Fare calculation based on zones and transport types
- Journey tracking with entry and exit points
- Smart fare calculation that favors customers
- Station information retrieval with zone details

## Recent Updates
The following enhancements have been implemented:

1. **API Documentation with Swagger**
   - Added Swagger UI for interactive API testing
   - Enhanced API endpoints with detailed documentation

2. **New Station API**
   - Created a new endpoint to retrieve all stations with their respective zones
   - Implemented at `/api/station`

3. **Maximum Fare Update**
   - Changed maximum fare from AED 3.20 to AED 7.00
   - Implemented configurable fare structure through application properties

4. **Code Refactoring**
   - Externalized fare configuration to properties file
   - Improved code maintainability and readability

## Technology Stack
- Java 8
- Spring Boot 2.6.2
- Maven
- Springfox Swagger 3.0.0
- Lombok

## Prerequisites
- JDK 1.8 or later
- Maven 3.2+

### Setting up JAVA_HOME
If you encounter the error `JAVA_HOME not found in your environment`, follow these steps:

1. **Locate your Java installation**:
   - Find where Java is installed on your system
   - Common locations are:
     - Windows: `C:\Program Files\Java\jdk1.8.0_xxx`
     - macOS: `/Library/Java/JavaVirtualMachines/jdk1.8.0_xxx.jdk/Contents/Home`
     - Linux: `/usr/lib/jvm/java-8-openjdk` or `/usr/lib/jvm/java-8-oracle`

2. **Set the JAVA_HOME Environment Variable**:
   - **Windows**:
     - Right-click on 'This PC' or 'My Computer' and select 'Properties'
     - Click on 'Advanced system settings'
     - Click the 'Environment Variables' button
     - Under 'System variables', click 'New'
     - Variable name: `JAVA_HOME`
     - Variable value: Path to your Java installation (e.g., `C:\Program Files\Java\jdk1.8.0_291`)
     - Click 'OK' to save
     - Find the 'Path' variable, select it and click 'Edit'
     - Click 'New' and add `%JAVA_HOME%\bin`
     - Click 'OK' on all dialogs

   - **macOS/Linux**:
     - Edit your shell profile file (`~/.bash_profile`, `~/.zshrc`, etc.)
     - Add: `export JAVA_HOME=/path/to/your/java/installation`
     - Add: `export PATH=$JAVA_HOME/bin:$PATH`
     - Save the file and run: `source ~/.bash_profile` (or your relevant profile file)

3. **Verify Installation**:
   - Open a new terminal/command prompt
   - Run: `echo %JAVA_HOME%` (Windows) or `echo $JAVA_HOME` (macOS/Linux)
   - Run: `java -version`

### Installing Maven
If you encounter the error `'mvn' is not recognized as an internal or external command`, follow these steps:

1. **Download Maven**:
   - Download the latest Maven binary from [Apache Maven website](https://maven.apache.org/download.cgi)
   - Extract the downloaded archive to a directory of your choice (e.g., `C:\Program Files\Apache\maven`)

2. **Set Environment Variables**:
   - Right-click on 'This PC' or 'My Computer' and select 'Properties'
   - Click on 'Advanced system settings'
   - Click the 'Environment Variables' button
   - Under 'System variables', click 'New' and add:
     - Variable name: `MAVEN_HOME`
     - Variable value: `C:\Program Files\Apache\maven` (or your Maven installation path)
   - Find the 'Path' variable under 'System variables', select it and click 'Edit'
   - Click 'New' and add `%MAVEN_HOME%\bin`
   - Click 'OK' on all dialogs to save the changes

3. **Verify Installation**:
   - Open a new Command Prompt window
   - Run: `mvn -version`
   - You should see Maven version information

Alternatively, you can use the Maven wrapper included in the project:
```bash
# On Windows
./mvnw clean install

# On Unix/Linux/MacOS
chmod +x mvnw
./mvnw clean install
```

## Setup and Installation

### Clone the Repository
```bash
git clone https://github.com/sahilkhan2013/UAE-Travel-Card-Management-System.git
cd UAE-Travel-Card-Management-System
```

### Create and Switch to a Development Branch
```bash
git checkout -b sprint-1
```

### Build the Project
```bash
cd travelcard-system-service/card-system-service
mvn clean install
```

Or using Maven Wrapper:
```bash
cd travelcard-system-service/card-system-service
# On Windows
./mvnw clean install
# On Unix/Linux/MacOS
chmod +x mvnw
./mvnw clean install
```

## Running the Application
```bash
mvn spring-boot:run
```

Or using Maven Wrapper:
```bash
# On Windows
./mvnw spring-boot:run
# On Unix/Linux/MacOS
./mvnw spring-boot:run
```

The application will start on port 8080 by default.

## API Documentation
Once the application is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui/
```

### Available APIs

#### Card Operations
- `GET /api/card/ping` - Check if the service is running
- `POST /api/card/register` - Register a new travel card
- `POST /api/card/recharge/{rechargeAmount}` - Recharge an existing card
- `POST /api/card/swipe` - Record card swipe at entry/exit points
- `GET /api/card/{cardNumber}` - Get card details
- `GET /api/card` - Get all registered cards

#### Station Operations
- `GET /api/station` - Get all stations with their zones

## Fare Structure
The system calculates fares based on the following rules:
- Anywhere in Zone 1: AED 2.50
- Any one zone outside Zone 1: AED 2.00
- Any two zones including Zone 1: AED 3.00
- Any two zones excluding Zone 1: AED 2.25
- Any three zones: AED 3.20
- Any bus journey: AED 1.80
- Maximum fare: AED 7.00 (updated from AED 3.20)

## Station and Zone Information
Stations are mapped to zones as follows:
- Algubaiba: Zone 1
- Jumeirah: Zones 1, 2
- Bur Dubai: Zone 3
- Deirah: Zone 2

## Operation Details
When a user passes through the inward barrier at a station, their travel card is charged the maximum fare. Upon exit at the destination station, the system calculates the actual fare, removes the maximum fare transaction, and replaces it with the real transaction. This ensures that if a user doesn't swipe out, they are charged the maximum fare.

## Project Structure
```
travelcard-system-service/
└── card-system-service/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/demo/travelcardsystem/
    │   │   │       ├── businessrule/    # Business rules for fare calculation
    │   │   │       ├── config/          # Application and Swagger configuration
    │   │   │       ├── constant/        # Enum constants
    │   │   │       ├── controller/      # REST API controllers
    │   │   │       ├── entity/          # Domain model classes
    │   │   │       ├── exception/       # Custom exceptions
    │   │   │       ├── model/           # Request and response models
    │   │   │       ├── repository/      # Data access layer
    │   │   │       └── service/         # Business logic
    │   │   └── resources/               # Configuration files
    │   └── test/                        # Test classes
    └── pom.xml                          # Maven dependencies
```

## Contributing
1. Create a feature branch from `sprint-1`
2. Implement your changes
3. Submit a pull request for peer review
4. After approval, merge changes to the `sprint-1` branch

## License
Proprietary - UAE SMART TRAVEL Limited "# travelcard-system-service" 
