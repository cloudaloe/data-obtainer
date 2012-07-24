; REPL working code for time obtaining and formatting while preserving the timezone as UTC (as opposed to an integer of millies since the epoc).
; This is as opposed to using clojure str just invokes .ToString methods that allways convert to local time.
; The format string enables flexibility in choosing the format
; (applying the clojure str translation to the date-formatter object, will NOT change the string held in it, and is 'safe' to use)

;default formatting translates to the local timezone (presumably as known from the OS)
(let [date-formatter (java.text.SimpleDateFormat. "dd MM yyyy HH:mm:ss.S zzz")] 
     (let [now (java.util.Date.)] (println (.format date-formatter (.getTime now)))))

;setting the timezone to UTC avoids local time translation
(let [date-formatter (java.text.SimpleDateFormat. "dd MM yyyy HH:mm:ss.S zzz") ] 
     (.setTimeZone date-formatter (java.util.TimeZone/getTimeZone "UTC")) 
		(let [now (java.util.Date.)] (println (.format date-formatter (.getTime now)))))