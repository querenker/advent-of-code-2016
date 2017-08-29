(ns advent-of-code-2016.day1-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2016.day1 :refer :all]))

(deftest day1-test
  (testing "case 1"
    (is (= 5 (origin-distance ["R2" "L3"]))))
  (testing "case 2"
    (is (= 2 (origin-distance ["R2" "R2" "R2"]))))
  (testing "case 3"
    (is (= 12 (origin-distance ["R5" "L5" "R5" "R3"])))))
