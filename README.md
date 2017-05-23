# Kea
## Requirements
* Java 8
* Tomcat 8
* Maven 3.2+
## Build
`mvn clean install`
## Configuration
```
\-- <tomcat>
  \-- conf
    \-- <engine>
      \-- <hostname>
        +-- configuration
        | +-- classes
        | | +-- database.properties
        | | +-- kea.properties
        | | \-- log4j2.xml
        | \-- lib
        \-- <appName>.xml
```
