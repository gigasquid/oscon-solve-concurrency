(ns clojure-intro.intro-test
  (:require [clojure.test :refer :all]
            [clojure-intro.intro :refer :all]
            [clojure.set :as set]))


;; this is a helper function that is a stand-in for the right function
;; to use
(defn fill-in-the-blank [& args] "fill me in with the right function")

(deftest basic-expressions
  (testing "addition"
    (is (= 5 (+ 1 4)))
    (is (= 15 (+ 1 9 (+ 2 3)))))

  (testing "subtraction"
    (is (= 10 (- 15 5)))
    (is (= 10 (- 15 3 (- 2 0)))))

  (testing "multiplication"
    (is (= 72 (* 9 8)))
    (is (= 99 (* 11 (+ 2 7)))))

  (testing "division"
    (is (= 4 (/ 8 2)))
    (is (= 2 (/ 10 (+ 1 4))))))


;;; Replace the fill-in-the-blank function with the right collection
;;; function - (first, rest,last, or conj)

(deftest all-about-lists
  (testing "getting the first element"
    (is (= :a (first '(:a :b :c :d)))))

  (testing "getting the last element"
    (is (= :d (last '(:a :b :c :d)))))

  (testing "getting the rest of the elements"
    (is (=  '(:b :c :d) (rest '(:a :b :c :d)))))

  (testing "adding an element"
    (is (=  '(:e :a :b :c :d) (conj '(:a :b :c :d) :e)))))



;;; Replace the fill-in-the-blank function with the right collection
;;; function - (first, rest, last, conj, or nth)

(deftest all-about-vectors
  (testing "getting the first element"
    (is (= :a (first [:a :b :c :d]))))

  (testing "getting the last element"
    (is (= :d (last '[:a :b :c :d]))))

  (testing "getting the rest of the elements"
    (is (= [:b :c :d] (rest [:a :b :c :d]))))

  (testing "getting the element at an index"
    (is (= :b (nth [:a :b :c :d] 1))))

  (testing "adding an element"
    (is (= [:a :b :c :d :e] (conj [:a :b :c :d] :e)))))


;;; Replace the fill-in-the-blank function with the right collection
;;; function - it be: get, or a keyword, assoc, dissoc

(deftest all-about-maps
  (testing "getting the value from a map with get"
    (is (= "green" (get {:a "blue" :b "red" :c "green"} :c))))

  (testing "getting the value from a map with the keyword"
    (is (=  "blue" (:a {:a "blue" :b "red" :c "green"}))))

  (testing "changing the value of a key"
    (is (=  {:a "yellow" :b "red" :c "green"}
            (assoc {:a "blue" :b "red" :c "green"} :a "yellow"))))

  (testing "adding new key value pairs"
    (is (=  {:a "blue" :b "red" :c "green" :d "pink"}
            (assoc {:a "blue" :b "red" :c "green"} :d "pink"))))

  (testing "removing value pairs"
    (is (=  {:b "red" :c "green"}
            (dissoc {:a "blue" :b "red" :c "green"} :a)))))


;;; Replace the fill-in-the-blank function with the right collection
;;; function - it be: get, or a keyword, clojure.set/intersection,
;;; conj or disj

(deftest all-about-sets
  (testing "getting the value from a set with get"
    (is (= :x (get #{:x :y :z} :x))))

  (testing "getting the value from a set with a keyword"
    (is (= :y (:y #{:x :y :z}))))

  (testing "getting the intersection from two sets"
    (is (= #{:z} (set/intersection #{:x :y :z} #{:a :b :z}))))

  (testing "adding an element to a set"
    (is (= #{:x :y :z :a} (conj #{:x :y :z} :a))))

  (testing "removiing an element from a set"
    (is (= #{:x :y} (disj #{:x :y :z} :z)))))
