(defn mean [s]
  (/ (apply + s) (count s)))

(defn sqrt-list [s]
  (map #(Math/sqrt %1) s))

(def function-map {"sum" #((apply + %1))
                   "product" #((apply * %1))
                   "mean" mean
                   "sqrt" sqrt-list})

(defn strings-to-nums [strings]
  (map #(Double. %1) strings))

; Haar([8, 5, 7, 3) -> [5.75, 1.75, 0.75, -0.25]
;    Step 1 -> [6.5, 5, 1.5, 2]
; 1st and 3rd values as prior example, 2nd and 4th transform applied to 7 and 3
;   Step 2 -> Recurse, using [6.5, 5] and [1.5, 2] as bases. (N.B.: Average([8,5,7,3]) == 5.75)

(defn haar-coll 
  "concat of averages, then distance from averages of EACH PAIR"
  [coll]
  (let [coll-pairs (partition 2 coll)]
    (concat 
     (map mean coll-pairs)
     (map #(let [[one two] %1]  (- one (mean %1))) coll-pairs))))

;This works, YAY (time to test/refactor)
(defn haar [coll]
  (let [coll_mean (mean coll)]
    (loop [coll coll]
      (if (= coll_mean (first coll))
        coll
        (recur (haar-coll coll))))))
               


(println "First one (sum/mean/product/sqrt of cmd line args):")
(println ((function-map (first *command-line-args*)) 
          (strings-to-nums (rest *command-line-args*))))
(println "Second one (Harr transform):")

;(print ((function-map (first *command-line-args*))
;        (strings-to-big-decimals (rest *command-line-args*))))

;(try (BigDecimal. "joe") 
;     (catch NumberFormatException e (print "Invalid number"))) 
(defn help-text []
  "./s sum|mean 2 3 1 x ...")
