(in-ns 'data-obtainer.core)

(defn obtain [configuration]
	(let [{host :host port :port user :user database :database} configuration] 
		(let [subname (str "//" host ":" port "/information_schema")] 
			(let [mysql-conn {:subprotocol "mysql" :subname subname :user user}] 
				(let [time (java.sql.Timestamp. (.getTime (java.util.Date.)))] ; a(. System (nanoTime)) based timestamping implementation or no-system call service can be considered
					(try
						(sql/with-connection mysql-conn
							(sql/with-query-results rows
								["select table_name 'table-name',
								table_rows 'rows-num',
								data_length 'data-length-GB',
								index_length 'index-length-GB',
								data_length+index_length 'total-GB' 
								from information_schema.TABLES
								where table_schema=?" database] 
								(if rows 
									(do (doseq [row rows] (println "Obtained metadata:" "host" host "schema" database "table" (:table-name row)))
										(doseq [row rows] (writeline (str 
											time "," 
											(:table-name row) "," 
											(:rows-num row) "," 
											host 
											\newline)))) 
									(println (str \newline "---" \newline "In MySQL, on host " host ", no metadata found for schema " database \newline "---" \newline))
								)
							)
						)
						(catch Exception e (println (str \newline "---" \newline "Could not query one MySQL database for table sizes." \newline "Attempted connection map was: " \newline mysql-conn "." \newline "Source configuration was:" \newline configuration \newline "Detected exception:" \newline e \newline "---" \newline)))))))))	