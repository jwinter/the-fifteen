
(defn mean [s]
  (/ (apply + s) (count s)))

(defn sqrt-list [s]
  (map #(Math/sqrt %1) s))

(def function-map {"sum" #((apply + %1))
                   "product" #((apply * %1))
                   "mean" mean
                   "sqrt" sqrt-list})

(defn strings-to-big-decimals [strings]
  (map #(Double. %1) strings))

(println ((function-map (first *command-line-args*)) 
          (strings-to-big-decimals (rest *command-line-args*))))

;(print ((function-map (first *command-line-args*))
;        (strings-to-big-decimals (rest *command-line-args*))))

;(try (BigDecimal. "joe") 
;     (catch NumberFormatException e (print "Invalid number"))) 
(defn help-text []
  "./s sum|mean 2 3 1 x ...")
