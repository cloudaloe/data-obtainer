(ns data-obtainer.core 
	(import (java.io File))
	(:gen-class main true))
	
(require '[clojure.java.io :as io])
(require '[clojure.java.jdbc :as sql])
	
(load "push" "obtain")	

(defn csv-init []
	 (doseq [field fields] (writeline (str (:human-name field) ",")))
	 (writeline (str \newline)))

(defn -main [& args]

	(println (str \newline "Clojure program starting " (java.sql.Timestamp. (.getTime (java.util.Date.))) "."))
	
	; detect configuration, from default location or command line argument
	(let [[first-arg] args]
		(if (nil? first-arg) (def databases "databases/databases.cfg") (def databases (str "databases/" first-arg))))
			(println (str "Using databases configuration file " databases "."))
	
	(def configurations (read-string (slurp databases))) ;TBA: confirming the configuration

	(let [f (File. out-file-path)]
		(if (.exists f) (println "Output file found.") (dorun (csv-init))))

	(println)
		
	(doall (map obtain configurations))
	
	nil
)

; TBA: announcing the amount of servers, databases, or likewise
; TBA: better-than-stdout interface for indicating progress
