(def input (->>
  (slurp "day_2/input.txt")
  clojure.string/split-lines))

; Part One
(defn getCheck [str i]
  (let [freq (for [[x y] (frequencies (seq str))] y)]
    (if (some #{i} freq) 1 0)))

(defn getSum [in i]
  (->>
    (map #(getCheck % i) in)
    (reduce +)))

(println "Solution One:" (* (getSum input 2) (getSum input 3)))

; Part Two

(defn common [x y]
  (->>
    (map vector x y)
    (filter #(apply = %))
    (map first)
    (apply str)))

(defn offByOne [x y]
  (= (dec (count x)) (count (common x y))))

(println "Solution Two:" (first (for [a input b input :when (offByOne a b)] (common a b))))