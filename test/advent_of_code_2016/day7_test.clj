(ns advent-of-code-2016.day7-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2016.day7 :refer :all]))

(deftest day4-test
  (testing "valid tls"
    (let [ip "abba[mnop]qrst"]
      (is (= true (has-tls? ip)))))
  (testing "abba in square brackets"
    (let [ip "bcd[bddb]xyyx"]
      (is (= false (has-tls? ip)))))
  (testing "interior character not different"
    (let [ip "aaaa[qwer]tyui"]
      (is (= false (has-tls? ip)))))
  (testing "abba is within larger string"
    (let [ip "ioxxoj[asdfgh]zxcvbn"]
      (is (= true (has-tls? ip))))))
