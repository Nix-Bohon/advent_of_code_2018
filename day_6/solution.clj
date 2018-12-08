; Input
(def input (let 
  [
    filename "day_6/input.txt"
    ;filename "day_6/sample.txt"
  ]
  (->> 
    (slurp filename)
    clojure.string/split-lines
    (map #(clojure.string/split % #", "))
    (map #(into [] (map read-string %)))
  )))

; Part One
(def X (range (dec (apply min (map first input))) (inc (apply max (map first input)))))
(def Y (range (dec (apply min (map second input))) (inc (apply max (map second input)))))

(defn manhattan [Ax Ay Bx By]
    (+ (Math/abs (- Ax Bx)) (Math/abs (- Ay By))))

(def input' (map #(apply vector (concat %1 (vector %2))) input (range 0 (count input))))

(defn find-closest [x y points] 
    (let [
        distances (map #(vector (manhattan x y (% 0) (% 1) ) (% 2)) points)
        leastDistance (apply min (map first distances))
        closest (filter #(= leastDistance (% 0)) distances)
    ]
    (if (= 1 (count closest))
        (second (first closest))
        nil)))

(def grid (for [x X y Y] (find-closest x y input')))
(def infiniteRegions (set (concat 
    (map #(find-closest (first X) % input') Y)
    (map #(find-closest (last X) % input') Y)
    (map #(find-closest % (first Y) input') X)
    (map #(find-closest % (last Y) input') X))))

(->> 
    (frequencies grid)
    (filter #(not (infiniteRegions (first %))))
    (map second)
    sort
    reverse
    first
    (println "Solution One:"))