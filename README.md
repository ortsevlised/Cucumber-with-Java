# Example of Cucumber with Java

This is a simple example of the use of cucumber and Java to test the add to cart feature of an E-commerce site.

Git:

    git clone https://github.com/cucumber/cucumber-java-CartFeatures.git
    cd cucumber-java-CartFeatures

Subversion:

## Use Maven

Open a command window and run:

    mvn test

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the `RunCukesTest`
class tells JUnit to kick off Cucumber.

