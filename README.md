# Data-obtainer

Application for obtaining growth-over-info for a MySQL cluster.
Prerequisites separately installing a MySQL JDBC driver suitable for the MySQL databases at hand.

## License

Copyright © 2012 Matan Safriel.
Distributed under the Eclipse Public License, the same as Clojure.

## Setup & Configuration

This program connects to MySQL using JDBC. Due to licensing limitations, the MySQL Connector/J JDBC driver should be separately obtained, as we are not allowed to ship it. This driver can be typically downloaded from http://dev.mysql.com/downloads/connector/j/. After obtaining the MySQL Connector/J jar file, it should be placed in the same directory as this project's jar file. (Otherwise, please modify the classpath to include that jar if placed elsewhere).

#### Configuration

Databases/databases.cfg should contain all configuration data, one clause per MySQL server.
For making the initial configuration, contact the author of this program for a sample. 
It's OK to change the configuration in between runs.

## Usage

Each run of this program captures and __appends the current__ state for the databases defined to it at databases/databases.cfg. Information is time-stamped as UTC time. 
To run this program, CD to the directory containing this project's jar, and run: 

_java -classpath "data-obtainer-0.1.0-SNAPSHOT-standalone.jar;mysql-connector-java-5.1.21-bin.jar" clojure.main -m data-obtainer.core > out.log_

It captures the current state from all defined servers and appends it to the data store (currently a simple CSV file). 
View the accumulated data in data.csv. If using Excel, make sure to make a copy as opening it in Excel will block accumulation of more data. It's OK to delete this file for starting fresh. 
