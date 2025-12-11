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

(def solve-2
  (memoize
   (fn [has-fft has-dac k m]
     (cond
       (and (= k "out") has-fft has-dac) 1
       (not= (contains? m k)) (throw (AssertionError. "Unreachable branch"))
       :else (->> (get m k)
                  (map #(solve-2 (or has-fft (= % "fft")) (or has-dac (= % "dac")) % m))
                  (reduce +))))))

(defn part-2 [puzzle-input]
  (->> puzzle-input
       str/split-lines
       (map #(str/split % #": "))
       (reduce
        (fn [m [k v]]
          (assoc m k (str/split v #" "))) {})
       (solve-2 false false "svr")))

#_(do
    (def puzzle-sample-input (slurp "inputs/11_sample.txt"))
    (def puzzle-sample-input2 (slurp "inputs/11_sample2.txt"))
    (def puzzle-input (slurp "inputs/11.txt"))
    (println "Part 1:" (time (part-1 puzzle-input)))
    (println "Part 2:" (time (part-2 puzzle-input))))
