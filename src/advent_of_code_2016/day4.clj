(ns advent-of-code-2016.day4
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn room-parts [character]
  (let [digit-chars (set (map char (range (int \0) (inc (int \9)))))]
  (cond
    (contains? digit-chars character) :sector-id
    (contains? #{\[ \]} character) :checksum-parenthesis)))

(defn parse-room-data [room-data]
  (let [cleaned-room-data (str/replace room-data #"-" "")
        [room-name sector-id _ checksum _] (partition-by room-parts cleaned-room-data)]
    [room-name (->> sector-id (apply str) Integer.) checksum]))

(defn valid-room? [[room-name _sector-id checksum]]
  (->> room-name
       frequencies
       (sort-by (fn [[char frequency]] [(- frequency) (int char)]))
       (take 5)
       (map first)
       (= checksum)))

(defn decrypt-shift-cipher [message rotations]
  (let [alphabet (apply str (map char (range (int \a) (inc (int \z)))))]
    (->> message
         (map (fn [char]
                (case char
                  \- \-
                  (nth (cycle alphabet) (+ rotations (.indexOf alphabet (str char))))))))))

(defn decrypt-room [room]
  (let [[room-name sector-id _] room]
    (assoc room 0 (decrypt-shift-cipher room-name sector-id))))

(defn load-data [filepath]
  (-> filepath
      slurp
      str/trim
      (str/split #"\n")))

(->> "day4.txt"
     clojure.java.io/resource
     load-data
     (map parse-room-data)
     (filter valid-room?)
     (map decrypt-room)
     (filter (fn [[room-name sector-id _]] (re-matches #".*north.*" (apply str room-name)))))

