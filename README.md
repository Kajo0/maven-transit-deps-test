# maven-transit-deps-test
Tests for maven transitive dependencies exclusion

According to:

https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html

 or with images:

http://shengwangi.blogspot.com/2016/04/when-to-use-true-in-maven-dependency.html

# Structure

```
~/view$ mvn dependency:tree
[INFO] ------------------------------------------------------------------------
[INFO] Building view 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ view ---
[INFO] app:view:jar:1.0-SNAPSHOT
[INFO] \- app:framework:jar:1.0-SNAPSHOT:compile
[INFO]    +- app:accessmanager:jar:1.0-SNAPSHOT:compile
[INFO]    |  \- com.google.guava:guava:jar:18.0:compile
[INFO]    \- com.googlecode.libphonenumber:libphonenumber:jar:8.9.5:compile
[INFO] ------------------------------------------------------------------------
```

# Build
```
~/accessmanager$ mvn clean -U install
~/framework$     mvn clean -U install
~/view$          mvn clean -U compile
```

# Make `accessmanager` unavailable in `view` classpath 
`~/framework/pom.xml`
```
        <dependency>
            <groupId>app</groupId>
            <artifactId>accessmanager</artifactId>
            <version>1.0-SNAPSHOT</version>
            <optional>true</optional>               <!-- Optioal = true -->
        </dependency>
```

## `view` Build fails
```
~/framework$     mvn clean -U install
~/view$          mvn clean -U compile
[INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] ~/view/src/main/java/view/View.java:[3,21] package accessmanager does not exist
[ERROR] ~/view/src/main/java/view/View.java:[4,33] package com.google.common.collect does not exist
[ERROR] ~/view/src/main/java/view/View.java:[12,34] cannot find symbol
  symbol:   variable Lists
  location: class view.View
[ERROR] ~/view/src/main/java/view/View.java:[12,13] cannot find symbol
  symbol:   class AccessManager
  location: class view.View
[INFO] 4 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
```

## Make `view` _compilable_ by removing `accessmanager`-package code

```
~/view$ mvn dependency:tree
[INFO] ------------------------------------------------------------------------
[INFO] Building view 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ view ---
[INFO] app:view:jar:1.0-SNAPSHOT
[INFO] \- app:framework:jar:1.0-SNAPSHOT:compile
[INFO]    \- com.googlecode.libphonenumber:libphonenumber:jar:8.9.5:compile
[INFO] ------------------------------------------------------------------------
```
