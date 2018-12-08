; Input
(def input (let 
    [
      filename "day_7/input.txt"
      ; filename "day_7/sample.txt"
    ]
    (->> 
      (slurp filename)
      clojure.string/split-lines
      (map #(let
        [ l (clojure.string/split % #" ")]
        (vector (l 1) (l 7))))
    )))

; Part one
(def graph
    (reduce 
        #(assoc %1 (first %2) (concat nil (%1 (first %2))))
        (reduce 
            #(assoc %1 (second %2) (concat (first %2) (%1 (second %2)))) 
            {} 
            input) 
        input))

(println graph)

(def solutionOne (loop 
    [
        s []
        g graph
    ] 
    (let 
    [
        c (->> (filter #(every? (set s) (second %)) g)
            (map first)
            sort
            first)
        s' (concat s c)
        g' (filter #(not (= c (first %))) g)
    ] 
    (if (empty? g') (apply str s') (recur s' g')))))

(println "Solution One:" solutionOne)

; Part Two

(defn create-workers [steps]
    (map #(vector % (- (int (first %)) 4)) steps))

(defn update-workers [workers steps] 
    (->> 
        (map #(vector (first %) (max 0 (dec (second %)))) workers)
        (filter #(= (second %) 0))
        (concat (create-workers steps))))

(defn get-steps [graph steps n]
    (->> 
        (filter #(every? (set steps) (second %)) graph)
        (map first)
        sort
        (take n)))

(def workerCount 5)

(defn solutionTwo [] (loop
    [
        workers [[\- 0] [\- 0] [\- 0] [\- 0] [\- 0]]
        s []
        g graph
    ]
    (let
        [
            idleWorkers (count (filter #(=(second %) 0) workers))
            workers' (update-workers workers s idleWorkers)
            graph' (drop idleWOrkers graph)
        ] [workers' graph'])))

(println (get-steps graph [] 5))
(println (create-workers (get-steps graph [] 5)))
(println (update-workers [] (get-steps graph [] 5)))