# Visual testing

Test project contains two types of tests:</br>
* Traditional (TraditionalTests.java)</br>
* Visual (VisualTests.java); You can setup eyes in VisualTestsInit.java. </br>
Note: **Don't forget to setup your APPLITOOLS_API_KEY in environment variables**</br>

Using IntelliJIdea: you can run tests </br>
1. With right click on it and select "Run" </br>
2. With right click and "Run" on testng xml (see details below)</br>
3. Using maven "clean install" (or with additional parameteres, see details below)</br>

## Run with testng xml
Project contains 3 testng xml files: **traditional.xml, visual.xml, all.xml**, that allows you to run specific tests or all tests in project</br>
You can select file, right click on it and "Run" tests in this suite

## Run with maven
In order to **run tests against V1 environment*** set property "environment" in pom.xml to "V1"</br>
In order to **run tests against V2 environment** set property "environment" in pom.xml to "V2"</br>
Also in pom.xml property "testSuite" you can specify test suite you would like to run using values: **traditional, visual, all**</br>
And of course you can run tests using maven console using properties</br>
</br>
This order to run tests recommended to verify test project results:</br>
**clean install -DtestSuite=traditional -Denvironment=V1** - First of the run traditional tests against environment V1</br>
(optional) **clean install -DtestSuite=traditional -Denvironment=V1** - Second run against same environment to be sure that tests still passed and baselines passed</br>
**clean install -DtestSuite=traditional -Denvironment=V2** - Third run tests against environment V2 and identify issues</br>
You can observe test run results in console log or using allure report. In order to do it just place folder with "allure-results" in "target folder" and run Plugins>allure>allure:serve in maven panel or start local allure server using console</br>
See visual images comparison based on Selenium TakesScreenshot for canvas tests in \src\test\.logs\screens folder</br>
</br>
**clean install -DtestSuite=visual -Denvironment=V1** - First run of the visual tests with applitools eyes against environment V1</br>
(optional) **clean install -DtestSuite=visual -Denvironment=V1** - Second run against same environment to be sure that tests still passed and baselines passed</br>
**clean install -DtestSuite=visual -Denvironment=V2** - Third run tests against environment V2 and identify issues</br>
Visual testing results you can also see in Allure report or in Applitools web application using your APPLITOOLS_API_KEY.</br>

## Conclusions
Traditional tests are good to find that where is some issues in flow and functionality like "table is sorted" or "some values are the same as in DB or in Web API response".
Visual testing benefits:
* From other hands in most cases you will find only first issue and if there are more issues like on Login form it is hard to get them all without visual testing even if using Soft Asserts</br>
* Testing things like canvas and logos defenitely need visual testing</br>
Functional testing benefits:
* But in sort table case this is hard to validate that table is sorted especially if we take in account that data in table related to some storage and can change</br>
* And of course without functional testing you can't navigate through the application )

**As result in order to get best results it is necessary combinate both approaches that allow you to test application from functional and visual side**


# JDI Light links
About JDI Light fraemworks used for testing see more details at official [Github of the project](https://github.com/jdi-testing/jdi-light) and in our [documentation](https://jdi-docs.github.io/jdi-light/?java).
For fast start you can go through [tutorial](https://jdi-docs.github.io/jdi-light/?java#tutorial) </br>

More links: </br>
Documentation: https://jdi-docs.github.io/jdi-light/?java - different types of documentation from introduction and tutorial to technical methods documentation </br>
Introduction + fast start example: https://jdi-docs.github.io/jdi-light/?java#introduction </br>
Tutorial: https://jdi-docs.github.io/jdi-light/?java#tutorial - helps to get main ideas on practice </br>
Tutorial repo:  https://github.com/jdi-tutorials </br>
Project templates: https://github.com/jdi-templates - for fast projects start </br>
Examples: https://github.com/jdi-examples </br>
How to use [UI Elements examples](https://github.com/jdi-testing/jdi-light/tree/master/jdi-light-html-tests/src/test/java/io/github/epam/html/tests/elements) </br>
Increase test [performance examples](https://github.com/jdi-testing/jdi-light/tree/master/jdi-performance) </br>
