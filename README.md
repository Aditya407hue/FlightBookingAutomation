# ✈️ FlightBookingAutomation

Welcome to **FlightBookingAutomation** – your one-stop solution for testing flight booking web applications with speed, precision, and ease!  
Automate your testing journey from searching flights to booking confirmations 🚀.  
Built with Java, Selenium, and Cucumber, this framework empowers testers and developers to deliver bug-free, high-quality flight booking experiences.  

---

## 🧩 Features

- 🤖 **End-to-End Automation:** Automate the entire flight booking workflow, from search to payment.
- 📝 **BDD with Cucumber:** Write human-readable test scenarios with Gherkin syntax.
- 🏎️ **Fast & Reliable:** Harness Selenium WebDriver for robust, cross-browser automation.
- 🧪 **Data-Driven Testing:** Easily test multiple scenarios using parameterized data.
- 🔍 **Detailed Reporting:** Get beautiful, interactive HTML reports after each run.
- 🏗️ **Modular & Extensible:** Quickly add or modify test scenarios and steps.
- 📈 **Continuous Integration Ready:** Designed to fit right into your CI/CD pipelines.

---

## 🛠️ Tech Stack

| Technology     | Usage                                  |
| -------------- | -------------------------------------- |
| **Java**       | Core automation scripts                |
| **Selenium**   | Web browser automation                 |
| **Cucumber**   | BDD scenario definitions (Gherkin)     |
| **HTML/CSS/JS**| Reporting UI & possible mockups        |
| **Maven**      | Build & dependency management          |

---

## 🗂️ Project Structure

```
.
├── src/
│   ├── main/java/           # Framework logic & helpers
│   └── test/java/           # Step definitions & runners
│   └── test/resources/      # Gherkin feature files
├── reports/                 # Test reports (HTML, etc.)
├── pom.xml                  # Maven config
├── README.md
```

---

## 🚦 Getting Started

### 🧰 Prerequisites

- Java 8 or above ☕
- Maven 📦
- ChromeDriver or GeckoDriver (in your PATH) 🌐
- (Optional) IDE like IntelliJ or Eclipse

### 🚀 Installation

1. **Clone the repo:**
   ```bash
   git clone https://github.com/Aditya407hue/FlightBookingAutomation.git
   cd FlightBookingAutomation
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Configure your test environment:**
   - Edit driver paths and environment data in your config files

---

## 🏃‍♂️ Running Your Tests

Run all automated scenarios with:

```bash
mvn test
```

- 📊 Test reports will be generated in the `reports/` directory.
- 🌈 Open the HTML reports in your browser to review results visually.

---

## ✍️ Writing & Adding Test Scenarios

- Write new scenarios in Gherkin (`.feature` files) under `src/test/resources/`.
- Follow the Given-When-Then format for clarity and collaboration.
- Example:

    ```gherkin
    Feature: Flight booking
      Scenario: Book a one-way flight
        Given I open the flight booking homepage
        When I search for flights from "NYC" to "LON"
        And I select the first available flight
        And I complete the booking process
        Then I should see the booking confirmation
    ```

---

👥 Contributions
This project wouldn’t have taken off without the dedication and collaboration of an exceptional team. Here’s a breakdown of who did what — and why this project soared:

🚀 Anshika Chaudhary
Role: Project Coordinator & QA Strategist
Anshika played a pivotal role in overseeing the overall progress of the automation framework. She was instrumental in ensuring that timelines were met, blockers were resolved swiftly, and quality was never compromised. Her hands-on involvement in crafting the FlightBooking step definitions and her consistent motivation helped keep the team focused and productive throughout the development cycle.

🔍 Aditya Srivastava
Role: Feature Developer – Flight Booking & Search
Aditya spearheaded the creation of the FlightBooking.feature file and took the lead in implementing the FlightSearch module. From scenario structuring to writing robust test steps, he ensured that user flows were automated accurately and efficiently. His detail-oriented approach helped cover complex edge cases and solidified the foundation of the test suite.

🧪 Neeti Pandey
Role: Module Owner – Enquiry & Bug Resolution Expert
Neeti contributed significantly to the Enquiry module by developing its Gherkin feature file, corresponding step definitions, and Page Factory elements. She also worked extensively on the FlightSearch module and actively participated in debugging and fixing runtime issues. Her consistent contributions helped enhance the stability and reliability of the test framework.

🛠️ Aditya Singh
Role: Technical Lead – Setup, Integration & Module Development
Aditya laid the groundwork for the project by setting up the core automation framework, managing the GitHub repository, and streamlining collaboration across the team. He developed the Login module and played a crucial role in implementing remaining critical modules. His strong focus on maintainability, modular design, and integration made sure the framework was CI/CD-ready and easy to scale.

---

## 🤝 Contributing

We 💙 contributions!  
Whether it's a new feature, bug fix, or improvement, help us make testing better for everyone:

1. Fork the repo
2. Create a new branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -am 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

Licensed under the MIT License.

---

🎫 **Book bugs out of your app – one click at a time!**  
Happy Testing & Safe Flights! 🧳🛫
