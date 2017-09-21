(ns advent-of-code-2016.day6
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #"\n")))

(->> "day6.txt"
     clojure.java.io/resource
     load-data
     (apply mapv vector)
     (map frequencies)
     (map (partial group-by val))
     (map sort)
     (map last)
     (map val))
