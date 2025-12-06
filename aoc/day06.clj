(ns day06
  (:require [clojure.string :as str]))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map str/trim)
   (map #(str/split % #"\s+"))
   (apply (partial map vector)) ;; equivalent of python's zip() function, used as transposition
   (map #(apply (if (= (last %) "*") * +) (map Integer/parseInt (butlast %))))
   (reduce +)))

#_(do
    (def puzzle-sample-input (slurp "inputs/06_sample.txt"))
    (def puzzle-input (slurp "inputs/06.txt"))
    (println "Part 1:" (time (part-1 puzzle-input))))
