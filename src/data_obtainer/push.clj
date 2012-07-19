(in-ns 'data-obtainer.core)

(def file-path "data.csv")
(def emitter (agent (io/writer file-path :append true)))
(defn writeline [line]
	(send emitter .write line))

#_(defn writeline [line]
	(try (with-open [out-file (clojure.java.io/writer file-path :append true)]
		(.write out-file (str line \newline))) 
			(catch Exception e (println (str \newline "---" \newline "Could not append data to result file " file-path ". Detected exception:" \newline e \newline "---" \newline)))))