(ns advent-of-code-2016.day3
  (:require [clojure.string :as str]))

(defn valid-triangle? [triangle-sites]
  (let [[a b c] (sort triangle-sites)]
    (< c (+ a b))))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #"\n")))

(defn clear-triangle-data [triangle-data]
  (->> triangle-data
       str/trim
       (#(str/split % #"\s+"))
       (map #(Integer/parseInt %))))

(->> "day3.txt"
    clojure.java.io/resource
    load-data
    (map clear-triangle-data)
    (filter valid-triangle?)
    count)
