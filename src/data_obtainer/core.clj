(ns data-obtainer.core 
	(:gen-class main true))
	
(require '[clojure.java.io :as io])
(require '[clojure.java.jdbc :as sql])
	
(load "push" "obtain")	

(defn -main [& args]

	(println "~ Clojure program starting ~" \newline)
	  
	(with-open [rdr (io/reader "hello.txt")]
		(doseq [line (line-seq rdr)]
			(println line)))

	(def configurations (read-string (slurp "databases/databases.cfg"))) ;TBA: confirming the configuration
	
	(doall (map obtain configurations))
)

; TBA: announcing the amount of servers, databases, or likewise
; TBA: interface for indicating progress
