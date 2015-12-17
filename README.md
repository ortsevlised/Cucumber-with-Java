# Example using Cucumber with Java

This is a simple example of the use of cucumber and Java to test the add to cart feature of an E-commerce site.

## Installation
Make sure you have GIT installed and Clone the repository to your local drive.:

    git clone https://github.com/ortsevlised/magenTys.git


## Use Maven

If you don't have maven installed please download it from:
   ```
   https://maven.apache.org/download.cgi
   ```
Open a command window inside the project folder and run:

    mvn test

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

If you are using an IDE you might just want to Run the `RunCukesTest` class.

## Properties file

There's a property file located at 
```
src/config.properties
```
There you can set what browser you want to use, please notice I have only added the chrome driver to this project,
so if you want to use another driver make sure the paths are properly set up in the DriverFactory class.


## Selenium Only
There's also package called `TestWithoutCucumber`
There you will find the same test using Selenium only with TestNG.

From the IDE select the @Test annotation within the Test class and Run with TestNG, if you don't have the plugin please
download it or just change the annotation import to JUnit, delete the description from the annotation and run it with JUnit. 
