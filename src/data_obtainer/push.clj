(in-ns 'data-obtainer.core)

;(def emitter (agent (io/writer out-file-path :append true)))
;(defn writeline [line]
;(send emitter write line))
	
(def out-file-path "data.csv")

(defn writeline [line]
	(try (with-open [out-file (clojure.java.io/writer out-file-path :append true)]
		(.write out-file line))
			(catch Exception e (println (str \newline "--- error ---" \newline "Could not append data to result file " out-file-path ". Possible causes may be:" \newline "1) The file is excusively open in another application" \newline "2) Disk space" \newline "3) Write permissions are missing" \newline "Exception encountered:" \newline e \newline\newline "------------" \newline)))))