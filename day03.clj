(ns day03
  (:require [clojure.string :as str]))

(defn remove-single-digit-keep-max [value]
  (let [as-list (str/split value #"")]
    (str (apply max
                (map #(BigInteger. (str/join %1))
                     (for [i (range 0 (count as-list))] (assoc as-list i "")))))))

(defn- keep-largest-n-digit-joltage [n value]
  (if (zero? n)
    value
    (keep-largest-n-digit-joltage
      (dec n)
      (remove-single-digit-keep-max value))))

(defn find-largest-joltage [n value]
  (keep-largest-n-digit-joltage (- (count value) n) value))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map (partial find-largest-joltage 2))
   (map Integer/parseInt)
   (reduce +)))

(defn part-2 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map (partial find-largest-joltage 12))
   (map Long/parseLong)
   (reduce +)))

#_(do
    (def puzzle-sample-input (slurp "inputs/03_sample.txt"))
    (def puzzle-input (slurp "inputs/03.txt"))
    (println "Part 1:" (time (part-1 puzzle-input)))
    (println "Part 2:" (time (part-2 puzzle-input))))
