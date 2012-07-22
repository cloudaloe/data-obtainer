(in-ns 'data-obtainer.core)

;(def emitter (agent (io/writer out-file-path :append true)))
;(defn writeline [line]
;(send emitter write line))
	
(def out-file-path "data.csv")

(defn writeline [line]
	(try (with-open [out-file (clojure.java.io/writer out-file-path :append true)]
		(.write out-file line))
			(catch Exception e (println (str \newline "---" \newline "Could not append data to result file " out-file-path ". Detected exception:" \newline e \newline "---" \newline)))))