(ns day01
  (:require [clojure.string :as str]))

(defn parse-line [line]
  (let [[operation value] (str/split line #"(?=[0-9])" 2)]
    (* (if (= operation "L") -1 1) (Integer/parseInt value))))

(defn sum-mod100 [pair]
  (mod (reduce + pair) 100))

(defn rotate [coll]
  (if (<= (count coll) 1)
    coll
    (let [rotated (sum-mod100 (take 2 coll))
          the-rest (drop 2 coll)
          merged (cons rotated the-rest)]
      (if (>= (count the-rest) 2)
        (lazy-seq (cons rotated (rotate merged)))
        [rotated  (sum-mod100 merged)]))))

(defn part-1 [puzzle-input]
  (->> puzzle-input
       str/split-lines
       (map parse-line)
       (cons 50)
       rotate
       (filter zero?)
       count))

#_(do
   (def puzzle-sample-input (slurp "inputs/01_sample.txt"))
   (def puzzle-input (slurp "inputs/01.txt"))
   (println "Part 1:" (part-1 puzzle-input)))
