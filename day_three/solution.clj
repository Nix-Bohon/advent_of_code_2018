; Load the input.txt
(def input (let 
  [
    filename "day_three/input.txt"
    rgx #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)"
  ]
  (->>
    (slurp filename)
    clojure.string/split-lines
    ; Get rid of the first result (it matches the whole string),
    ; and parse all of the values to be numbers!
    (map #(apply vector (map read-string (rest (re-matches rgx %)))))
  )))


; Part One

(defn getClaims [_ x y w h]
  (for [x' (range w) y' (range h)] 
    (into [] (map + [x y] [x' y']))))

(def claimFrequencies (->> 
  (map #(apply getClaims %) input)
  (reduce concat)
  frequencies))

(println claimFrequencies)
(->> 
  (filter #(> (last %) 1) claimFrequencies)
  count
  (println "Solution One:"))

; Part Two

(defn hasOverlaps? [in]
  (every? #(= (get claimFrequencies %) 1) (apply getClaims in)))

(println "Solution Two:" (first (filter hasOverlaps? input)))