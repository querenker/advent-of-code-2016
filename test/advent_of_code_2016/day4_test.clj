(ns advent-of-code-2016.day4-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2016.day4 :refer :all]))

(deftest day4-test
  (testing "valid room 1"
    (let [room-code "aaaaa-bbb-z-y-x-123[abxyz]"]
      (is (= true (-> room-code parse-room-data valid-room?)))))
  (testing "valid room 2"
    (let [room-code "a-b-c-d-e-f-g-h-987[abcde]"]
      (is (= true (-> room-code parse-room-data valid-room?)))))
  (testing "valid room 3"
    (let [room-code "not-a-real-room-404[oarel]"]
      (is (= true (-> room-code parse-room-data valid-room?)))))
  (testing "unvalid room 1"
    (let [room-code "totally-real-room-200[decoy]"]
      (is (= false (-> room-code parse-room-data valid-room?))))))
