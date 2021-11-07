# JDALib
Helper library for JDA

# Usage
TODO
## Activity
```java
// JDALib.setActivity(status, pattern);
// pattern embeds:
//   {serverCount}, {memberCount}

// Ex
jdaLib.setActivity(true, "Working in {serverCount} servers.");

// You can customize embed string
jdaLib.property.setActivityEmbedServerCount("{serverCount}");
jdaLib.property.setActivityEmbedMemberCount("{memberCount}")
```

# Install
## Gradle Groovy
```
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/CronBotLibrary/JDALib")
    }
}
dependencies {
    implementation 'jp.cron:jdalib:0.1'
}
```
## Kotlin DSL
```
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/CronBotLibrary/JDALib")
    }
}
dependencies {
    implementation("jp.cron:jdalib:0.1")
}
```
## Maven
```
<dependency>
  <groupId>jp.cron</groupId>
  <artifactId>jdalib</artifactId>
  <version>0.1</version>
</dependency>
```

## Task
[Issues](https://github.com/CronBotLibrary/JDALib/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc)