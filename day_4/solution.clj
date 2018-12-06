; Get input

(def input (let 
  [
    filename "day_4/input.txt"
    rgx #"^.*:(\d+)] [^ ]+ ([^ ]+).*$"
  ]
  (->>
    (slurp filename)
    clojure.string/split-lines
    sort
    (map #(into [] (rest (re-matches rgx %)))))))

; Part One

(defn updateData [d g s e]
    (assoc d g (concat (d g) (range s e))))

(def f (loop 
  [
    guard nil
    start nil
    data {}
    in input
  ]
(if (= (count in) 0)
  data
  (let
    [
      action (last (first in))
      time (Integer/parseInt (first (first in)))
      in' (rest in)
    ]
    (cond
        (= (first action) \#)
          (recur (Integer/parseInt (subs action 1)) start data in')
        (= action "up")
          (recur guard nil (updateData data guard start time) in')
        (= action "asleep")
          (recur guard time data in'))))))

(def sleepiestElf (->>
    (map #(vector (first %) (count (last %))) f)
    (reduce #(if (> (get %1 1) (get %2 1)) %1 %2))
    first))

(defn getBestTimeForElf [sleepTimes] (->>
    (frequencies sleepTimes)
    (reduce #(if (> (get %1 1) (get %2 1)) %1 %2))))

(def bestTimeForSleepiestElf (->>
    (f sleepiestElf)
    getBestTimeForElf
    first))

(println "Solution One:" (* sleepiestElf bestTimeForSleepiestElf))

; Part Two

(def bestTimeOverall (->>
    (map #(into [(first %)] (getBestTimeForElf (last %))) f)
    (reduce #(if (> (get %1 2) (get %2 2)) %1 %2))
    (take 2)
    (apply *)))

(println "Solution Two:" bestTimeOverall)