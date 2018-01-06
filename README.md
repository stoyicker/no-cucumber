# no-cucumber
## A toolset for making your manager happy and staying sane at the same time

Ever been forced to use Cucumber.io to describe some tests in a non-technical fashion, at a very painful maintenance cost?
Your suffering is over. If you're not interested in the cross-platform possibilities of Cucumber and are using it only 
for native Android, it's your lucky day:

0. Add JitPack as a repository for your dependencies:
```groovy
repositories {
  maven {
    url "https://jitpack.io"
  }
}
```
1. Add the annotations to your project:
```groovy
androidTestImplementation "com.github.stoyicker.no-cucumber:no-cucumber-annotations:+" // or androidTestImplementation if you use the apt from the Android plugin for Gradle
```
2. Add some scenarios in your ```androidTest``` source set:
```kotlin
@Scenario(
        name = "my scenario one",
        featureNames = arrayOf("feature one", "feature two"),
        steps = arrayOf("step one", "step two", "bananas"))
interface MyScenario
```
3. Add the annotation processor to your project: 
```groovy
kaptAndroidTest "com.github.stoyicker.no-cucumber:no-cucumber-processors:+" // or androidTestAnnotationProcessor if you use the apt from the Android plugin for Gradle
```
4. Add the plugin to your project:
```groovy
buildscript {
	repositories {
        maven { url "https://jitpack.io" }
    }
    dependencies {
        classpath "com.github.stoyicker.no-cucumber:no-cucumber-plugin:+"
    }
}
apply plugin: 'no-cucumber-android'
```
5. Make the magic happen:
```
./gradlew noCucumberGenerate (or nCG for short)
```
6. Look for the generated ```.feature``` files in ```build/generated/source/kapt/debugAndroidTest/nocucumber```
(or corresponding path if your project uses Java instead)!

Optionally, if you want both the features and their corresponding reports as of a new test run, you can just run:
```
./gradlew noCucumberRun (or nCR for short)
```
no-cucumber will run all Espresso tests, and then parse the XML reports from Espresso, the feature files and a generated 
mapping between @Steps and Espresso tests, and use the combined information to create reports corresponding to each of 
the feature files.

Or, if the feature files and XML reports are already generated, just run:
```
./gradlew noCucumberPrint (or nCP for short)
```
This will skip running your tests again, and instead just generate the per-feature reports from the XML report of the 
last run.
