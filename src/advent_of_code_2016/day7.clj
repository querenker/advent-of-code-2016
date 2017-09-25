(ns advent-of-code-2016.day7
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn is-abba? [[a b c d]]
  (and (not= a b) (= a d) (= b c)))

(defn contains-abba? [s]
  (let [groups-of-four (map vector s (drop 1 s) (drop 2 s) (drop 3 s))]
    (some is-abba? groups-of-four)))

(defn has-tls? [ip]
  (let [ip-parts (str/split ip #"\[|\]")
        abba-parts (take-nth 2 ip-parts)
        hypernet-parts (take-nth 2 (rest ip-parts))]
    (boolean (and (not-any? contains-abba? hypernet-parts) (some contains-abba? abba-parts)))))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #"\n")))

(->> "day7.txt"
     clojure.java.io/resource
     load-data
     (filter has-tls?)
     count)
