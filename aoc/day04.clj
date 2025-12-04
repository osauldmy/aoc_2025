(ns day04
  (:require [clojure.string :as str]))

(defn make-adjacent-compliant-positions [y x height width]
  (filter some?
          (for [h (range (dec y) (+ 2 y))
                w (range (dec x) (+ 2 x))]
            (when (every? true? [(>= h 0)
                                 (< h height)
                                 (>= w 0)
                                 (< w width)
                                 (not= [y x] [h w])])
              [h w]))))

(defn can-be-accessed? [array y x]
  (->>
   (make-adjacent-compliant-positions y x (alength array) (alength (aget array 0)))
   (map #(apply aget array %))
   (filter #(= % "@"))
   count
   (> 4)))

(defn find-accessible-positions [grid]
  (let [array (to-array-2d grid)
        height (alength array)
        width (alength (aget array 0))]
    (filter true? (for [y (range height)
                        x (range width)]
                    (when (= (aget array y x) "@")
                      (can-be-accessed? array y x))))))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map #(str/split % #""))
   find-accessible-positions
   count))

#_(do
    (def puzzle-sample-input (slurp "inputs/04_sample.txt"))
    (def puzzle-input (slurp "inputs/04.txt"))
    (println "Part 1:" (time (part-1 puzzle-input))))
