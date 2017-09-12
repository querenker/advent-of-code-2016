
(ns advent-of-code-2016.day5
  (:require [clojure.string :as str]
            [clojure.set :as set])
  (import java.security.MessageDigest
          java.math.BigInteger))

(def door-id "ffykfhsq")

(defn positive-numbers
  ([] (positive-numbers 0))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        raw (.digest algorithm (.getBytes s))]
    (format "%032x" (BigInteger. 1 raw))))

(->> (positive-numbers)
     (map #(str door-id %))
     (map md5)
     (filter #(= (subs % 0 5) "00000"))
     (map #(nth % 5))
     (take 8))
