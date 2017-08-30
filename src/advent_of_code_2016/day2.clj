(ns advent-of-code-2016.day2
  (:require [clojure.string :as str]))

(def keypad [[nil nil 1   nil nil]
             [nil 2   3   4   nil]
             [5   6   7   8   9]
             [nil \A  \B  \C  nil]
             [nil nil \D  nil nil]])

(def current-position (atom [2 0]))

(def movements {\R (fn [[x y]] [x (inc y)])
                \U (fn [[x y]] [(dec x) y])
                \L (fn [[x y]] [x (dec y)])
                \D (fn [[x y]] [(inc x) y])})

(defn perform-movement [movement]
  (let [movement-fun (get movements movement)
        new-position (movement-fun @current-position)]
    (when-not (nil? (get-in keypad new-position))
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
