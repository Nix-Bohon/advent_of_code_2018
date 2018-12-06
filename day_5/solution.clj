; Input

(defn now [] (java.util.Date.))

(def input (let 
    [
      filename "day_5/input.txt"
      ;filename "day_five/sample.txt"
    ]
    (-> 
      (slurp filename)
      (clojure.string/replace #"\W" ""))))

; Part One
(def Aa #"(Aa|Bb|Cc|Dd|Ee|Ff|Gg|Hh|Ii|Jj|Kk|Ll|Mm|Nn|Oo|Pp|Qq|Rr|Ss|Tt|Uu|Vv|Ww|Xx|Yy|Zz)")
(def aA #"(aA|bB|cC|dD|eE|fF|gG|hH|iI|jJ|kK|lL|mM|nN|oO|pP|qQ|rR|sS|tT|uU|vV|wW|xX|yY|zZ)")

(defn removeReactions [sequence]
    (-> (clojure.string/replace sequence Aa "") (clojure.string/replace aA "")))

(defn performReactions [sequence] (loop [solution sequence]
    (let [solution' (removeReactions solution)]
        (if (= solution solution')
            solution'
            (recur solution')))))

(def solutionOne (performReactions input))

(println "Solution One:" (count solutionOne))

; Part Two

(defn removeUnitType 
    [x polymer]
    (->
        (clojure.string/replace polymer x "")
        (clojure.string/replace (clojure.string/upper-case x) "")))

(->>
    (map #(->>
        (removeUnitType (str (char %)) input)
        performReactions
        count) (range 97 123))
    (reduce min)
    (println "Solution Two:"))