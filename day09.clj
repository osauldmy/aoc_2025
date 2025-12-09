(ns day09
  (:require [clojure.string :as str]))

(defn compute-distances [positions]
  (for [[a_x a_y] positions [b_x b_y] positions]
    (* (inc (abs (- a_x b_x)))
       (inc (abs (- a_y b_y))))))

(defn part-1 [puzzle-input]
  (->> puzzle-input
       str/split-lines
       (map #(map Integer/parseInt (str/split % #",")))
       compute-distances
       (apply max)))

#_(do
   (def puzzle-sample-input (slurp "inputs/09_sample.txt"))
   (def puzzle-input (slurp "inputs/09.txt"))
   (println "Part 1:" (time (part-1 puzzle-input))))
