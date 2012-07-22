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

	(println "~ Clojure program starting " (java.sql.Timestamp. (.getTime (java.util.Date.))) "~" \newline)
			
	(def configurations (read-string (slurp "databases/databases.cfg"))) ;TBA: confirming the configuration

	(let [f (File. out-file-path)]
		(if (.exists f) (println "Output file found") (dorun (csv-init))))
	
	(doall (map obtain configurations))
	
	nil
)

; TBA: announcing the amount of servers, databases, or likewise
; TBA: interface for indicating progress
