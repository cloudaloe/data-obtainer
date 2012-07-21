# data-obtainer

Application for obtaining growth-over-time info for a MySQL cluster.
Prerequisites separately installing a MySQL JDBC driver suitable for the MySQL databases at hand.

## License

Copyright © 2012 Matan Safriel.
Distributed under the Eclipse Public License, the same as Clojure.

## Setup & Configuration

This program connects to MySQL using JDBC. Due to licensing limitations, the MySQL Connector/J JDBC driver should be separately obtained as we are not allowed to ship it.  This driver can be typically downloaded from http://dev.mysql.com/downloads/connector/j/. For straightforward invocation, the MySQL Connector/J jar file should be placed in the same directory as this project's jar file. (Otherwise, please modify the classpath to include that jar if placed elsewhere).

#### Configuration

Databases/databases.cfg should contain all configuration data, one clause per MySQL server.
For making the initial configuration, contact the author of this program for a sample. 
It's OK to change the configuration in between runs.

## Usage

Each run of this program captures and appends the current state for the databases defined to it at Databases/databases.cfg. 
To run it, CD to the directory containing this project's jar. Run: java -classpath "data-obtainer-0.1.0-SNAPSHOT-standalone.jar;mysql-connector-java-5.1.21-bin.jar" clojure.main -m data-obtainer.core > out.log. This captures the current status from all defined servers and appends it to the data store (currently a simple CSV file). Data is timestamped as UTC time.
View the accumulated data in data.csv. If using Excel, make sure to make a copy as opening it in Excel will block accumulation of more data. It's OK to delete this file for starting fresh. 
