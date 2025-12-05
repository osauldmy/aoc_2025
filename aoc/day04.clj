(ns day04
  (:require [clojure.string :as str]))

(defn make-adjacent-compliant-positions [y x height width]
  (filter some?
          (for [h (range (dec y) (+ 2 y))
                w (range (dec x) (+ 2 x))]
            (when (every? true? [(<= 0 h (dec height))
                                 (<= 0 w (dec width))
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
    (filter some? (for [y (range height)
                        x (range width)]
                    (when (and (= (aget array y x) "@")
                               (can-be-accessed? array y x))
                      [y x])))))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map #(str/split % #""))
   find-accessible-positions
   count))

(defn remove-paper-roll [grid [y x]]
  (assoc grid y (assoc (nth grid y) x "x")))

(defn remove-rolls-while-possible [grid]
  (let [possible-to-remove (find-accessible-positions grid)]
    (if (empty? possible-to-remove)
      grid
      (remove-rolls-while-possible (reduce remove-paper-roll grid possible-to-remove)))))

(defn part-2 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map #(str/split % #""))
   (into [])
   remove-rolls-while-possible
   flatten
   (filter #(= % "x"))
   count))

#_(do
    (def puzzle-sample-input (slurp "inputs/04_sample.txt"))
    (def puzzle-input (slurp "inputs/04.txt"))
    (println "Part 1:" (time (part-1 puzzle-input)))
    (println "Part 2:" (time (part-2 puzzle-input))))
