(ns day02
  (:require [clojure.string :as str]))

(defn pre-process [puzzle-input]
  (->
   puzzle-input
   str/trim
   (str/split #",")))

(defn generate-range [pair]
  (let [[a b] (map Long/parseLong pair)]
    (range a (inc b))))

(defn generate-ranges [unsplitted-strings]
  (->>
   unsplitted-strings
   (map #(str/split %1 #"-"))
   (map generate-range)))

(defn is-half-copy? [value]
  (let [str-value (str value)
        half (/ (count str-value) 2)]
    (apply = (split-at half str-value))))

(defn is-even-length-string? [value]
  (-> value
      str
      count
      even?))

(defn part-1 [puzzle-input]
  (->> puzzle-input
       pre-process
       generate-ranges
       flatten
       (filter is-even-length-string?)
       (filter is-half-copy?)
       (reduce +)))

#_(do
    (def puzzle-sample-input (slurp "inputs/02_sample.txt"))
    (def puzzle-input (slurp "inputs/02.txt"))
    (println "Part 1:" (part-1 puzzle-input)))
