NF4Droid
========

NF4Droid (Network Forensics Tool for Android)

NF4Droid is a desktop web application for the analysis of network traffic capture files from Android apps. 


Build
========
Use maven to build project with the following goals:

* compile sources
~~~
mvn compile 
~~~

* clean build
~~~
mvn clean
~~~

* create apache tomcat webapp war file
~~~
mvn package
~~~

* run webapp in gwt development mode using jetty
~~~
mvn gwt:run	
~~~


Configure
========

- Database

  Please configure the database in the file /main/resources/META-INF/spring/database.properties.

- GeoIP Database

  Please configure the refeence to the Maxming GeoLite IP Database in the file /main/resources/META-INF/spring/nf4droid.properties.

- Logging

  Please configure the log4j logging level in the /main/resources/log4j.properties.


Update IP Database
========

We use the GeoLite IP database from maxmind for the geo location service in the webapp.
To have the most accurate results, we recommend to update the database every month.


Copyright & License
=======
Copyright 2012 - NF4Droid project

This project is licensed under Apache License 2.0 (see https://www.apache.org/licenses/LICENSE-2.0.html).


For all included libraries we reference to the according licenses.

This product includes GeoLite data created by MaxMind, available from http://www.maxmind.com
