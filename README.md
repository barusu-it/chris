# chris

### Convention

package name

- autoconfig: spring boot auto configuration package


### How to view gradle build script dependencies
```
gradlew buildEnvironment
```


### How to view gradle dependencies
```
gradlew -q <module_name>:dependencyInsight --dependency <group_name>
```

### How to skip test task of gradle

use -x to skip task

```
gradlew clean build -x test
```