(ns day03
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combinatorics]))

;; NOTE: clojure.math.combinatorics/combinations actually skips duplicates in a way I wouldn't expect
;; itertools.combinations gives correct solution without "fixing it for me"
(defn combinations-fix [value n]
  (map #(str/join (map second %1)) (combinatorics/combinations (map-indexed vector (str/split value #"")) n)))

(defn find-largest-joltage-part-1 [line]
  (apply max
         (map #(Integer/parseInt (str/join %1))
              (combinations-fix line 2))))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map find-largest-joltage-part-1)
   (reduce +)))

(defn remove-single-digit-keep-max [value]
  (let [as-list (str/split value #"")]
    (str (apply max
                (map #(BigInteger. (str/join %1))
                     (for [i (range 0 (count as-list))] (assoc as-list i "")))))))

(defn- keep-largest-12-digit-joltage [n value]
  (if (zero? n)
    value
    (keep-largest-12-digit-joltage
      (dec n)
      (remove-single-digit-keep-max value))))

(defn find-largest-joltage-part-2 [value]
  (keep-largest-12-digit-joltage (- (count value) 12) value))

(defn part-2 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map find-largest-joltage-part-2)
   (map Long/parseLong)
   (reduce +)))

#_(do
    (def puzzle-sample-input (slurp "inputs/03_sample.txt"))
    (def puzzle-input (slurp "inputs/03.txt"))
    (println "Part 1:" (time (part-1 puzzle-input)))
    (println "Part 2:" (time (part-2 puzzle-input))))
