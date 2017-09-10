(ns advent-of-code-2016.day1
  (require [clojure.string :as str])
  (:require [clojure.set :as set]))

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

(defn negative-range-inc [end]
  (if (< end 0)
    (map - (range (- (dec end))))
    (range (inc end))))

(defn incremental-moving-directions [full-moving-direction]
  (rest (for [x (negative-range-inc (first full-moving-direction))
        y (negative-range-inc (second full-moving-direction))]
    [x y])))

(defn origin-distance
  ([moving-points] (origin-distance moving-points [0 1] [0 0] #{[0 0]} nil))
  ([moving-points orientation position visited-positions position-visited-twice]
   (let [[moving-point & rest-moving-points] moving-points
         new-orientation (next-orientation orientation (first moving-point))
         new-moving-direction (moving-direction moving-point new-orientation)
         newly-visited-positions (map #(map + position %) (incremental-moving-directions new-moving-direction))
         new-position (last newly-visited-positions)
         new-position-visited-twice (or position-visited-twice
                                        (first (filter #(contains? visited-positions %) newly-visited-positions)))
         new-visited-positions (set/union visited-positions (set newly-visited-positions))]
     (if-not (nil? new-position-visited-twice)
       (reduce + (map #(Math/abs %) new-position-visited-twice))
       (recur rest-moving-points new-orientation new-position new-visited-positions new-position-visited-twice)))))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #", ")))

(-> "day1.txt"
    clojure.java.io/resource
    load-data
    origin-distance)
