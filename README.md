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
androidTestImplementation "com.github.stoyicker:no-cucumber-annotations:+"
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
kaptAndroidTest "com.github.stoyicker:no-cucumber-processors:+"
```
4. Make the magic happen:
```
./gradlew kaptDebugAndroidTestKotlin
```
5. Look for the generated ```.feature``` files in in ```build/generated/source/kapt/debugAndroidTest/nocucumber```
(or corresponding path if your project uses Java instead)!
