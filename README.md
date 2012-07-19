# data-obtainer

Application for obtaining growth-over-time info for a MySQL cluster.
Requires separately installing a MySQL JDBC driver suitable for the MySQL database version at hand, into the Java classpath.

## Usage

Locate to the root directory.
databases/databases.cfg should contain all configuration data, one clause per MySQL server.
Developer edition only for now: invoke with Lein run

## License

Copyright © 2012 Matan Safriel

Distributed under the Eclipse Public License, the same as Clojure.
