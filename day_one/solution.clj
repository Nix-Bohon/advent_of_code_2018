(defn now [] (java.util.Date.))

; Part One
(def input (->>
    (slurp "day_one/input.txt")
    clojure.string/split-lines
    (map read-string)))

(println "Solution One (" (now) "):" (reduce + input))

; Part Two
(defn solve [arr]
    (def seen [])
    (def i 0)
    (def freq 0)
    (while (not (some #(= freq %) seen)) (do
        (def seen (conj seen freq))
        (def freq (+ (last seen) (nth arr i)))
        (def i (mod (inc i) (count arr)))
    ))
    freq)

(println "Solution Two(" (now) "):"  (solve input))