(ns clojure-intro.intro-test
  (:require [clojure.test :refer :all]
            [clojure-intro.intro :refer :all]))


;; this is a helper function that is a stand-in for the right function
;; to use
(defn fill-in-the-blank [& args] "fill me in with the right function")

;; Instructions: fix the numbers to make these test pass.  When you
;; are done, remove the #_ in front of the next deftest to start
;; running those.  The #_ tells clojure to ignore the expression.
;; This is useful so we don't look at so many failures when running
;; the tests

(deftest basic-expressions
  (testing "addition"
    (is (= 5 (+ 1 8)))
    (is (= 15 (+ 1 8 (+ 0 3)))))

  (testing "subtraction"
    (is (= 10 (- 15 8)))
    (is (= 10 (- 15 8 (- 2 1)))))

  (testing "multiplication"
    (is (= 72 (* 7 7)))
    (is (= 99 (* 11 (+ 1 7)))))

  (testing "division"
    (is (= 4 (/ 8 4)))
    (is (= 2 (/ 10 (+ 1 3))))))


;; Don't forget to remove the #_ to make the tests run

;;; Replace the fill-in-the-blank function with the right collection
;;; function - (first, rest,last, or conj)

#_(deftest all-about-lists
  (testing "getting the first element"
    (is (= :a (fill-in-the-blank '(:a :b :c :d)))))

  (testing "getting the last element"
    (is (= :d (fill-in-the-blank '(:a :b :c :d)))))

  (testing "getting the rest of the elements"
    (is (=  '(:b :c :d) (fill-in-the-blank '(:a :b :c :d)))))

  (testing "adding an element"
    (is (=  '(:e :a :b :c :d) (fill-in-the-blank '(:a :b :c :d) :e)))))



;;; Replace the fill-in-the-blank function with the right collection
;;; function - (first, rest,last, conj, or nth)

#_(deftest all-about-vectors
  (testing "getting the first element"
    (is (= :a (fill-in-the-blank [:a :b :c :d]))))

  (testing "getting the last element"
    (is (= :d (fill-in-the-blank '[:a :b :c :d]))))

  (testing "getting the rest of the elements"
    (is (= [:b :c :d] (fill-in-the-blank [:a :b :c :d]))))

  (testing "getting the element at an index"
    (is (= :b (fill-in-the-blank [:a :b :c :d] 1))))

  (testing "adding an element"
    (is (= [:a :b :c :d :e] (fill-in-the-blank [:a :b :c :d :e] :e)))))


;;; Replace the fill-in-the-blank function with the right collection
;;; function - it be: get, or a keyword, assoc, dissoc

#_(deftest all-about-maps
  (testing "getting the value from a map with get"
    (is (= "green" (fill-in-the-blank {:a "blue" :b "red" :c "green"} :c))))

  (testing "getting the value from a map with the keyword"
    (is (=  "blue" (fill-in-the-blank {:a "blue" :b "red" :c "green"}))))

  (testing "changing the value of a key"
    (is (=  {:a "yellow" :b "red" :c "green"}
            (fill-in-the-blank {:a "blue" :b "red" :c "green"} :a "pink"))))

  (testing "adding new key value pairs"
    (is (=  {:a "blue" :b "red" :c "green" :d "pink"}
            (fill-in-the-blank {:a "blue" :b "red" :c "green"} :e "mango"))))

  (testing "removing value pairs"
    (is (=  {:b "red" :c "green"}
            (fill-in-the-blank {:a "blue" :b "red" :c "green"} :a)))))


;;; Replace the fill-in-the-blank function with the right collection
;;; function - it be: get, or a keyword, clojure.set/intersection,
;;; conj or disj

#_(deftest all-about-sets
  (testing "getting the value from a set with get"
    (is (= :x (fill-in-the-blank #{:x :y :z}))))

  (testing "getting the value from a set with a keyword"
    (is (= :y (fill-in-the-blank #{:x :y :z}))))

  (testing "getting the intersection from two sets"
    (is (= #{:z} (fill-in-the-blank #{:x :y :z} #{:a :b :z}))))

  (testing "adding an element to a set"
    (is (= #{:x :y :z :a} (fill-in-the-blank #{:x :y :z} :a))))

  (testing "removiing an element from a set"
    (is (= #{:x :y} (fill-in-the-blank #{:x :y :z} :z))))

)
