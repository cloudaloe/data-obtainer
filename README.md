# data-obtainer

Application for obtaining growth-over-time info for a MySQL cluster.
Prerequisites separately installing a MySQL JDBC driver suitable for the MySQL databases at hand.

## license

Copyright © 2012 Matan Safriel.
Distributed under the Eclipse Public License, the same as Clojure.

## setup & configuration

This program connects to MySQL using JDBC. Due to licensing limitations, it is not enough to install this program. The MySQL Connector/J JDBC driver should be separately obtained as we are not allowed to ship it.  This driver can be typically downloaded from http://dev.mysql.com/downloads/connector/j/. For straightforward invocation, the MySQL Connector/J jar file should be placed in the same directory as this project's jar file. (Otherwise, please modify the classpath to include that jar if placed elsewhere).

#### configuration

Databases/databases.cfg should contain all configuration data, one clause per MySQL server.
For making the initial configuration, contact the author of this program for a sample.

## usage

Each run of this program captures and appends the current state for the databases defined to it at Databases/databases.cfg. 
To run it, CD to the directory containing this project's jar. Run: java -classpath "data-obtainer-0.1.0-SNAPSHOT-standalone.jar;mysql-connector-java-5.1.21-bin.jar" clojure.main -m data-obtainer.core > out.log. This captures the current status from all defined servers and appends it to the data store (currently a CSV). Data is timestamped as UTC time.
