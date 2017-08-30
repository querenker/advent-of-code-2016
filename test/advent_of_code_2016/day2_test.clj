(ns advent-of-code-2016.day2-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2016.day2 :refer :all]))

(deftest day2-test
  (testing "case 1"
    (let [instructions ["ULL" "RRDDD" "LURDL" "UUUUD"]]
      (is (= '(5 \D \B 3) (solve-code instructions))))))
