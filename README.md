# Data-obtainer

Application for obtaining growth-over-time info for a MySQL cluster.
This application requires a java runtime being available.

## License

Copyright © 2012 Matan Safriel.
Distributed under the Eclipse Public License 1.0, the same as Clojure.
This application redistributes the MySQL Connector/J JDBC driver under the Oracle FOSS License Exception.

## Setup & Configuration

### Configuration
Databases/databases.cfg should contain all configuration data, one clause per MySQL instance.
For making the initial configuration, [see here] (https://github.com/cloudaloe/data-obtainer/blob/master/sample%20configuration/databases/databases.cfg).
It's OK to change the configuration in between runs.

### Prerequisite Permissions (MySQL)
To pull metadata, information_schema access for the relevant schema is necessary. This can only be accomplished by providing read access to the database schema of interest. E.g. by using:
<code> GRANT SELECT ON db-name.* TO user-name; </code>  whereas if desired, a new MySQL user can first be defined for this sake, e.g. via <code> CREATE USER user-name IDENTIFIED BY 'password'; </code>. 

### Security Considerations (Operating System)
This program needs to authenticate to MySQL. Unless MySQL authentication plugins are employed, a user name and password need to be available to it. At present those are supplied via the configuration file described above. They may also appear in this program's log or error traces. It is therefore highly recommended to restrict access to this program's installation directory to trusted users, or to employ a stronger MySQL authentication mechanism via MySQL authentication plugins or otherwise.

## Usage

Each run of this program captures and appends the current state for all databases defined to it at databases/databases.cfg. 
The data accumulates in a simple CSV file, named data.csv. Information is time-stamped with the local time. It's OK to delete this file for cleaning up or starting a fresh accumulation. If using Excel to view its content, make sure to only view a copy, as opening the original in Excel would block accumulation of more data. 

Download [here] (https://github.com/downloads/cloudaloe/data-obtainer/data-obtainer.zip), unzip and open a command-line at the directory.
Execute: <code> java -jar data-obtainer.jar </code>



