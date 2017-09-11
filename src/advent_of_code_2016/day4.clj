(ns advent-of-code-2016.day4
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def digit-chars (set (map char (range (int \0) (inc (int \9))))))

(defn room-parts [char]
  (cond
    (contains? digit-chars char) :sector-id
    (contains? #{\[ \]} char) :checksum-parenthesis))

(def room "aaaaa-bbb-z-y-x-123[abxyz]")
(partition-by room-parts room)

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
     (map second)
     (reduce +))
