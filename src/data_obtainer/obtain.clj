(in-ns 'data-obtainer.core)

(def fields [{:var-name 'time :human-name "Time"}
			 {:var-name 'host :human-name "Host"}
			 {:var-name 'database :human-name "Schema"}
			 {:mysql-column "table_name" :human-name "Table Name"}
			 {:mysql-column "table_rows" :human-name "Number of Rows"}
			 {:mysql-column "data_length" :human-name "Data Size"}
			 {:mysql-column "index_length" :human-name "Index Size"}])

(defn obtain [configuration]
	(let [{:keys [host port schema]} configuration]
	(let [rest-of-configuration (dissoc configuration :host :port :schema)]
	(let [subname (str "//" host ":" port "/information_schema")] 
	(let [mysql-conn (conj {:subprotocol "mysql" :subname subname :schema schema}, rest-of-configuration)] 
	(let [time (java.sql.Timestamp. (.getTime (java.util.Date.)))] ; a(. System (nanoTime)) based timestamping implementation or no-system call service can be considered
		(try
			(sql/with-connection mysql-conn
				(sql/with-query-results rows
					["select table_name,
					table_rows,
					data_length,
					index_length
					from information_schema.TABLES
					where table_schema=?" schema] 
					(if rows 
						(do (doseq [row rows] (println (str "Obtained metadata for:" " host " host " schema " schema " table " (:table_name row) ".")))
							(doseq [row rows] (writeline (str 
								time "," host "," schema ","
								(:table_name row) "," 
								(:table_rows row) "," 
								(:data_length row) ","
								(:index_length row)
						\newline)))) 
						(println (str \newline "--- Warning:" \newline "In MySQL, on host " host ", no metadata obtained for schema " schema \newline "------------" \newline))
					)
				)
			)
			(catch Exception e (println (str \newline "--- Warning:" \newline "Could not query one MySQL database for table sizes." \newline "Source configuration was:" \newline configuration \newline "Attempted connection map was: " \newline mysql-conn "." \newline \newline "Exception encountered:" \newline e \newline "------------" \newline))))))))))	