(ns advent-of-code-2016.day1
  (require [clojure.string :as str]))

(defn rotate-clockwise [[x, y]]
  [y, (- x)])

(defn rotate-counterclockwise [[x, y]]
  [(- y), x])

(defn next-orientation [current-orientation, rotation]
  (case rotation
    \L (rotate-counterclockwise current-orientation)
    \R (rotate-clockwise current-orientation)))

(defn moving-direction [moving-point orientation]
  (let [[rotation & moving-length] moving-point
        moving-length (Integer/parseInt (apply str moving-length))]
    (map #(* moving-length %) orientation)))

(defn origin-distance
  ([moving-points] (origin-distance moving-points [0 1] [0 0]))
  ([moving-points orientation position]
   (let [[moving-point & rest-moving-points] moving-points
         new-orientation (next-orientation orientation (first moving-point))
         new-moving-direction (moving-direction moving-point new-orientation)
         new-position (map + position new-moving-direction)]
     (case rest-moving-points
       nil (reduce + (map #(Math/abs %) new-position))
       (recur rest-moving-points new-orientation new-position)))))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #", ")))

(-> "day1.txt"
    clojure.java.io/resource
    load-data
    origin-distance)
