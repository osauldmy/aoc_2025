(ns day05
  (:require [clojure.string :as str]))

(defn is-in-any-interval [intervals ingredient]
  (some true? (map (fn [[start end]] (<= start ingredient end)) intervals)))

(defn solve-part-1 [[raw-intervals raw-ingredients]]
  (let [ingredients (map Long/parseLong raw-ingredients)
        intervals (map #(map Long/parseLong (str/split % #"-")) raw-intervals)]
    (count (filter (partial is-in-any-interval intervals) ingredients))))

(defn part-1 [puzzle-input]
  (->>
   (str/split puzzle-input #"\n\n")
   (map str/split-lines)
   solve-part-1))

#_(do
    (def puzzle-sample-input (slurp "inputs/05_sample.txt"))
    (def puzzle-input (slurp "inputs/05.txt"))
    (println "Part 1:" (time (part-1 puzzle-input))))
