# Al-Naqel Fare Card System
## Software Maintenance and Enhancement Project

---

## Slide 1: Title Slide
- **Project**: Al-Naqel Fare Card System
- **Subject**: Production Support and Maintenance
- **Client**: UAE SMART TRAVEL Limited
- **Team Members**: [Add Team Member Names]
- **Date**: [Current Date]

---

## Slide 2: Project Overview
- **System Purpose**: 
  - Travel card management for public transportation
  - Card registration and balance management
  - Trip tracking and fare calculation

- **Key Features**:
  - User registration and card loading
  - Trip management across multiple zones
  - Automatic fare calculation
  - Balance tracking

---

## Slide 3: Assessment Requirements
1. Git repository management with sprint branches
2. Swagger configuration for API documentation
3. Code quality assessment and refactoring with SonarQube
4. Architecture and design pattern analysis
5. New API implementation for station and zone information
6. Maximum fare update from AED 3.20 to AED 7.00
7. Project presentation

---

## Slide 4: Version Control Management
- Created sprint-1 branch from main repository
- Individual feature branches for development
- Pull requests and peer reviews
- Merged approved changes to sprint-1 branch

![Git Workflow Diagram]
[Insert screenshot/diagram of Git branches]

---

## Slide 5: Swagger Documentation
### Implementation:
- Added Springfox dependencies to pom.xml
- Created SwaggerConfig class
- Added API annotations to controllers
- Configured proper API documentation

### Benefits:
- Interactive API testing
- Comprehensive endpoint documentation
- Improved developer experience

![Swagger UI Screenshot]
[Insert screenshot of Swagger UI]

---

## Slide 6: Swagger Documentation Demo
- API endpoints for card operations
- API endpoints for station operations
- Request/response documentation
- Testing capabilities

![Swagger API Documentation]
[Insert screenshot of documented APIs]

---

## Slide 7: Code Quality Assessment
### SonarQube Analysis:
- Initial code quality metrics
- Identified issues:
  - Code smells
  - Potential bugs
  - Maintainability concerns

[Insert SonarQube initial report screenshot]

---

## Slide 8: Code Refactoring
### Improvements Made:
- Removed code duplications
- Enhanced error handling
- Improved code organization
- Added comprehensive comments
- Enhanced test coverage

[Insert before/after SonarQube metrics]

---

## Slide 9: Architecture Analysis
### System Architecture:
- Spring Boot application architecture
- MVC pattern implementation
- Repository layer for data access
- Service layer for business logic

[Insert architecture diagram]

---

## Slide 10: Design Pattern Identification
### Patterns Used in Project:
- **Observer Pattern**: TravelCard and TravelCardObserver
- **Strategy Pattern**: Business rules for fare calculation
- **Repository Pattern**: Data access abstraction
- **Builder Pattern**: Journey creation

### Maintainability Benefits:
- Separation of concerns
- Modularity and extensibility
- Reduced coupling

[Insert UML diagrams showing patterns]

---

## Slide 11: New API Implementation
### User Story:
- As a user, I want to view all stations and their zones

### Implementation:
- Created StationResponse model
- Extended repository with getAllStations method
- Added service method for retrieving stations with zones
- Created StationController with API endpoint

[Insert API endpoint screenshot]

---

## Slide 12: Jira User Story Management
### Complete Lifecycle:
- Requirements definition
- Development planning
- Implementation tracking
- Testing and verification
- Deployment

[Insert Jira board screenshot]

---

## Slide 13: Maximum Fare Update
### Requirement:
- Change maximum fare from AED 3.20 to AED 7.00

### Implementation:
- Externalized fare configuration to application.properties
- Added @Value annotations for configuration injection
- Updated TravelStrategy class
- Enhanced fare calculation logic

[Insert code change screenshots]

---

## Slide 14: System Structure & Environment Setup
### Project Structure:
```
travelcard-system-service/
└── card-system-service/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/demo/travelcardsystem/
    │   │   │       ├── businessrule/
    │   │   │       ├── config/
    │   │   │       ├── constant/
    │   │   │       ├── controller/
    │   │   │       ├── entity/
    │   │   │       ├── exception/
    │   │   │       ├── model/
    │   │   │       ├── repository/
    │   │   │       └── service/
    │   │   └── resources/
    │   └── test/
    └── pom.xml
```

### Environment Setup:
- Java 8
- Maven 3.2+
- Spring Boot 2.6.2

---

## Slide 15: Technical Challenges & Solutions
### Challenges:
- Maven and Java environment setup
- Configuring Swagger with Spring Boot 2.6
- Maintaining backward compatibility

### Solutions:
- Enhanced README with environment setup instructions
- Configured appropriate Spring MVC path matcher
- Thorough testing after changes

---

## Slide 16: Live Demo
### Demonstration of:
- Swagger UI navigation
- Station API functionality
- Card management operations
- Fare calculation with updated maximum fare

---

## Slide 17: Project Outcomes
### Achievements:
- Improved API documentation with Swagger
- Enhanced codebase quality
- New station API implementation
- Configurable fare structure
- Better maintainability

---

## Slide 18: References
- GitHub Repository: [Repository URL]
- Spring Boot Documentation
- Swagger Documentation
- IEEE Format References for External Resources

---

## Slide 19: Q&A
Thank you for your attention!

Questions? 