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

(defn merge-intervals [intervals]
  (reduce
   (fn [acc [x y]]
     (let [[a b] (last acc)]
       (cond
         (empty? acc) [[x y]]
         (>= b x) (conj (into [] (butlast acc)) [a (max b y)])
         :else (conj acc [x y]))))
   [] (sort-by first intervals)))

(defn part-2 [puzzle-input]
  (->>
   (str/split puzzle-input #"\n\n")
   (map str/split-lines)
   first
   (map #(map Long/parseLong (str/split % #"-")))
   merge-intervals
   (map (fn [[a b]] (- (inc b) a)))
   (reduce +)))

#_(do
    (def puzzle-sample-input (slurp "inputs/05_sample.txt"))
    (def puzzle-input (slurp "inputs/05.txt"))
    (println "Part 1:" (time (part-1 puzzle-input)))
    (println "Part 2:" (time (part-2 puzzle-input))))
