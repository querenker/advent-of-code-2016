(ns advent-of-code-2016.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2016.day1 :refer :all]))

(deftest day1-test
  (testing "case 1"
    (is (= 4 (origin-distance ["R8" "R4" "R4" "R8"])))))
