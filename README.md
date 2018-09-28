# Authu Spring Jwt
Authu Jwt For Spring Cloud    
Provide unified handling auth through jwt   

## How to use ?
Resolving artifacts using Maven   
```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-authu-spring-starter-jwt</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/authu/spring-starter-jwt</url>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-authu-spring-starter-jwt</id>
        <name>bintray-plugins</name>
        <url>https://dl.bintray.com/authu/spring-starter-jwt</url>
    </pluginRepository>
</pluginRepositories>
```

Import dependencyManagement
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.authu</groupId>
            <artifactId>spring-starter-jwt</artifactId>
            <version>0.1.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Add dependency
```xml
<dependency>
    <groupId>io.authu</groupId>
    <artifactId>spring-starter-jwt-core</artifactId>
</dependency>
```

Example    
<https://github.com/authu/spring-cloud-examples/tree/authu-jwt>

