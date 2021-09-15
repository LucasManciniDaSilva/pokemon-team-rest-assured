# Overview

Automation Project for API pokemon-dream-team

## Stack

[Java 8](https://java.com/en/download/faq/java8.xml)

[Maven](https://maven.apache.org/)

[Cucumber](https://cucumber.io/docs/bdd/?query=java)

[Cucumber DI](https://github.com/cucumber/cucumber-jvm/tree/master/picocontainer)

[Cucumber Report](https://github.com/trivago/cluecumber-report-plugin)

[Rest Assured](http://rest-assured.io/)

[JUnit](https://junit.org/junit5/)


## Execute Tests

mvn -Dcucumber.options="src/test/java" clean test

### After executed this one project we need to execute this other command below to generate the report
```
allure serve allure-results
```
