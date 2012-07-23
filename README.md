# Data-obtainer

Application for obtaining growth-over-time info for a MySQL cluster.
Prerequisites separately installing a MySQL JDBC driver suitable for the MySQL databases at hand.

## License

Copyright © 2012 Matan Safriel.
Distributed under the Eclipse Public License, the same as Clojure.

## Setup & Configuration

### Obtaining the MySQL Connector/J JDBC driver
This program connects to MySQL using JDBC. Due to licensing limitations, the MySQL Connector/J JDBC driver should be separately obtained, as we are not allowed to ship it. This driver can be typically downloaded from http://dev.mysql.com/downloads/connector/j/. After obtaining the MySQL Connector/J jar file, it should be placed in the same directory as this project's jar file. (Otherwise, if placed elsewhere, please make the classpath include that jar wherever you have placed it).

### Configuration
Databases/databases.cfg should contain all configuration data, one clause per MySQL instance.
For making the initial configuration, contact the author of this program for a sample. 
It's OK to change the configuration in between runs.

### Prerequisite Permissions (MySQL)
To pull metadata, information_schema access for the relevant schema is necessary. This can only be accomplished by providing read access to the database schema. E.g. by using:
<code> GRANT SELECT ON db-name.* TO user-name; </code>  whereas if desired, a new MySQL user can first be defined for this program, e.g. via <code> CREATE USER user-name IDENTIFIED BY 'password'; </code>. 

### Security Considerations (Operating System)
This program needs to authenticate to MySQL. Unless MySQL authentication plugins are employed, a user name and password need to be available to it. At present those are supplied via the configuration file described above. They may also appear in this program's log or error traces. It is therefore highly recommended to restrict access to this program's installation directory to trusted users, or to employ a stronger MySQL authentication mechanism via MySQL authentication plugins.

## Usage

Each run of this program captures and appends the current state for all databases defined to it at databases/databases.cfg. 
The data accumulates in a simple CSV file, named data.csv. Information is time-stamped as UTC time. It's OK to delete this file for cleaning up or starting a fresh accumulation. If using Excel to view its content, make sure to only view a copy, as opening the original in Excel would block accumulation of more data. 

To run this program, CD to the directory containing this project's jar, and run the following command (In case you've downloaded a differently named MySQL Connector/J driver, make sure to substitute its name in this command first)

<code> java -classpath "data-obtainer.jar;mysql-connector-java-5.1.21-bin.jar" clojure.main -m data-obtainer.core </code>



