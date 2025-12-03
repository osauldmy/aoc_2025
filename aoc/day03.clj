(ns day03
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combinatorics]))

;; NOTE: clojure.math.combinatorics/combinations actually skips duplicates in a way I wouldn't expect
;; itertools.combinations gives correct solution without "fixing it for me"
(defn combinations-fix [value n]
  (map #(str/join (map second %1)) (combinatorics/combinations (map-indexed vector (str/split value #"")) n)))

(defn find-largest-joltage [line]
  (apply max
         (map #(Integer/parseInt (str/join %1))
              (combinations-fix line 2))))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map find-largest-joltage)
   (reduce +)))

#_(do
    (def puzzle-sample-input (slurp "inputs/03_sample.txt"))
    (def puzzle-input (slurp "inputs/03.txt"))
    (println "Part 1:" (time (part-1 puzzle-input))))
