# Example using Cucumber with Java

This is a simple example of the use of cucumber and Java to test the add to cart feature of an E-commerce site.

Git:

    git clone https://github.com/ortsevlised/magenTys.git


## Use Maven

Open a command window and run:

    mvn test

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

If you are using an IDE you might just want to Run the `RunCukesTest` class.

## Properties file

There's a property file located at /src/config.properties
There you can set what browser you want to use, please notice I have only added the chrome driver to this project,
so if you want to use another driver make sure the paths are properly set up in the DriverFactory class.


## There's also package called `TestWithoutCucumber`, there you will find the same test using Selenium with TestNG only.