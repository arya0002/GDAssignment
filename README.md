**Framework Overview**

This automation framework was built from scratch with extensibility, maintainability, and scalability in mind. It uses Appium for Android mobile automation and supports both local and cloud execution.

**Key Features:**

DriverManager with support for multiple environments (Local / Cloud)

Singleton Pattern to manage driver lifecycle

Page Factory pattern for page object abstraction

POJOs and YAML for configuration management

Enum-based control for test environments and platforms

Flexible for future enhancements and test coverage expansion

Clean project structure based on industry standards

**Test Scenarios Automated**

**1. Create Account**

End-to-End flow for new account registration

Validates account creation success

**2. Update Personal Details**

Login with provided credentials

Edit and update profile information

Validate successful update

**Configuration**

**APK Path:**

Ensure the .apk file exists under:

/src/test/resources/apkFiles/leaforg.apk

Or update the path in:

FrameworkConstants.java

**Appium Server:**

Default:

http://0.0.0.0:4723

To change: Update the server URL in:

LocalDriverImpl.java

**How to Run**

Start Appium Server

Execute tests using TestNG:

testng src/testng.xml

**Reporting**

A detailed Extent Report is generated:

/reports/report.html

All test steps and assertions are logged

**Extendable Test Coverage**

Due to time constraints, only two positive E2E scenarios are automated. However, the framework is built to support additional tests such as:

Valid / Invalid Login

Forgot Password

Lockout handling

Upload or update profile picture

Edit personal details (DOB, license info)

Validate mandatory fields

and many more..


**Ready to Scale**

The current foundation is robust and modular, ready to:

Support more features

Integrate with CI/CD tools

Run tests on cloud providers like BrowserStack / Sauce Labs

