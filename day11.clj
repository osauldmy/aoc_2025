(ns day11
  (:require [clojure.string :as str]))

(defn solve [k m]
  (if (contains? m k)
    (for [x (get m k)]
      (solve x m))
    (when (= k "out")
      true)))

(defn part-1 [puzzle-input]
  (->> puzzle-input
       str/split-lines
       (map #(str/split % #": "))
       (reduce
         (fn [m [k v]]
           (assoc m k (str/split v #" "))) {})
       (solve "you")
       flatten
       count))

#_(do
    (def puzzle-sample-input (slurp "inputs/11_sample.txt"))
    (def puzzle-input (slurp "inputs/11.txt"))
    (println "Part 1:" (time (part-1 puzzle-input))))
