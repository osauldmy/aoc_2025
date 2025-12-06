(ns day06
  (:require [clojure.string :as str]))

(defn part-1 [puzzle-input]
  (->>
   puzzle-input
   str/split-lines
   (map str/trim)
   (map #(str/split % #"\s+"))
   (apply (partial map vector)) ;; equivalent of python's zip() function, used as transposition
   (map #(apply (if (= (last %) "*") * +) (map Integer/parseInt (butlast %))))
   (reduce +)))

(defn part-2 [puzzle-input]
  (let [lines (str/split-lines puzzle-input)
        operations (map #(if (= "*" %) * +) (str/split (last lines) #"\s+"))
        columns (->> (map #(str/split % #"") (butlast lines))
                     (apply map vector)
                     (map #(str/join "" %)))
        partitioned (filter #(not (str/blank? (first %)))
                            (partition-by str/blank? columns))
        cleaned (map
                  (fn [lst]
                    (map #(Integer/parseInt (str/trim %)) lst)) partitioned)]
      (->> cleaned
           (map-indexed
             (fn [index items]
               (apply (nth operations index) items)))
           (reduce +))))

#_(do
    (def puzzle-sample-input (slurp "inputs/06_sample.txt"))
    (def puzzle-input (slurp "inputs/06.txt"))
    (println "Part 1:" (time (part-1 puzzle-input)))
    (println "Part 2:" (time (part-2 puzzle-input))))
