(ns clojure-intro.intro2-test
  (:require [clojure-intro.intro :refer :all]
            [clojure.test :refer :all]))


;; To get remove the #_ in front of the next deftest to start
;; running those.  The #_ tells clojure to ignore the expression.
;; This is useful so we don't look at so many failures when running
;; the tests



(def x 1)

#_(deftest using-def
  (testing "using def to bind values globally"
    (is (= x 3))
    (is (= y "dog"))))


#_(deftest using-let
  (testing "let allows you to bind values within the context of the expression"
    (is (= "hi there" (let [x "hi"]
                        x))))
  (testing "let allows you to bind multiple things"
    (is (= [1 3] (let [x 1
                       y (+ x 1)]
                   [x y])))))

(defn cat []
  "woof")

(defn my-add [x y]
  (+ x y))

#_(deftest using-defn
  (testing "using defn to define functions"
    (is (= "meow" (cat)))
    (is (= "woof" (dog))))

  (testing "defn takes a vector of arguments"
    (is (= 4 (my-add 2 1)))))

#_(deftest anonymous-functions
  (testing "using fn for a function"
    (is (= "woof" (fn [] "meow"))))

  (testing "shorthand of #()"
    (is (= "2" #(+ 1 3)))))

#_(deftest flow-control-with-ifs
  (testing "if test is true, it returns the first expression"
    (is (= "scones" (if true "s" "cake"))))
  (testing "if test is false, it returns the last expression"
    (is (= "cake" (if false "scones" "c"))))
  (testing "nil is considered falsey"
    (is (= "cake" (if nil "scones" "c"))))
  (testing "a non nil or non false value is considered truthy"
    (is (= "scones" (if "tea" "s" "cake")))))


#_(deftest using-whens
  (testing "the body of a when only evaluates when it tests true"
    (is (= 2 (when true 3)))
    (is (= nil (when true 3)))))

#_(deftest flow-control-with-cond
  (testing "if the test is true then the value is returned"
    (is (= :a (cond
                false :a
                true :b))))
  (testing "defaults are at the end"
    (is (= :e (let [x 3]
                (cond
                  (= x 1) :a
                  (= x 3) :b
                  :else :e))))))

#_(deftest flow-control-with-case
  (testing "if the value matches then the expression is returned"
    (is (= :a (let [x 2]
                (case
                 1 :a
                 2 :b
                 3 :c)))))
  (testing "default cases are last"
    (is (= :e (let [x 2]
                (case
                 1 :a
                 2 :b
                 3 :c
                 :e))))))
