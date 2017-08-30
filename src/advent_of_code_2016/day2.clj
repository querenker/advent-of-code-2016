(ns advent-of-code-2016.day2
  (:require [clojure.string :as str]))

(def keypad [[1 2 3] [4 5 6] [7 8 9]])

(def current-position (atom [1 1]))

(def movements {\R (fn [[x y]] [x (inc y)])
                \U (fn [[x y]] [(dec x) y])
                \L (fn [[x y]] [x (dec y)])
                \D (fn [[x y]] [(inc x) y])})

(defn perform-movement [movement]
  (let [movement-fun (get movements movement)
        new-position (movement-fun @current-position)]
    (when (= (map <= [0 0] new-position [2 2]) [true true])
      (swap! current-position movement-fun))))

(defn walk-movement-path [movement-path]
  (doseq [movement movement-path] (perform-movement movement))
  (get-in keypad @current-position))

(defn solve-code [movement-paths]
  (map walk-movement-path movement-paths))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #"\n")))

(-> "day2.txt"
    clojure.java.io/resource
    load-data
    solve-code)
