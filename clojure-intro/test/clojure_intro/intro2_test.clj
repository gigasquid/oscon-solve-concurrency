(ns clojure-intro.intro2-test
  (:require [clojure-intro.intro :refer :all]
            [clojure.test :refer :all]))


;; To get remove the #_ in front of the next deftest to start
;; running those.  The #_ tells clojure to ignore the expression.
;; This is useful so we don't look at so many failures when running
;; the tests



(def x 1)

(def y "dog")

(deftest using-def
  (testing "using def to bind values globally"
    (is (= x 1))
    (is (= y "dog"))))


(deftest using-let
  (testing "let allows you to bind values within the context of the expression"
    (is (= "hi there" (let [x "hi there"]
                        x))))
  (testing "let allows you to bind multiple things"
    (is (= [1 2] (let [x 1
                       y (+ x 1)]
                   [x y])))))

(defn cat []
  "meow")

(defn dog []
  "woof")

(defn my-add [x y]
  (+ x y))

(deftest using-defn
  (testing "using defn to define functions"
    (is (= "meow" (cat)))
    (is (= "woof" (dog))))

  (testing "defn takes a vector of arguments"
    (is (= 3 (my-add 2 1)))))

(deftest anonymous-functions
  (testing "using fn for a function"
    (is (= "woof" ((fn [] "woof")))))

  (testing "shorthand of #()"
    (is (= 2 (#(+ 1 1))))))

(deftest flow-control-with-ifs
  (testing "if test is true, it returns the first expression"
    (is (= "scones" (if true "scones" "cake"))))
  (testing "if test is false, it returns the last expression"
    (is (= "cake" (if false "scones" "cake"))))
  (testing "nil is considered falsey"
    (is (= "cake" (if nil "scones" "cake"))))
  (testing "a non nil or non false value is considered truthy"
    (is (= "scones" (if "tea" "scones" "cake")))))


(deftest using-whens
  (testing "the body of a when only evaluates when it tests true"
    (is (= 3 (when true 3)))
    (is (= nil (when false 3)))))

(deftest flow-control-with-cond
  (testing "if the test is true then the value is returned"
    (is (= :a (cond
                true :a
                true :b))))
  (testing "defaults are at the end"
    (is (= :e (let [x 7]
                (cond
                  (= x 1) :a
                  (= x 3) :b
                  :else :e))))))

(deftest flow-control-with-case
  (testing "if the value matches then the expression is returned"
    (is (= :a (let [x 1]
                (case x
                    1 :a
                    2 :b
                    3 :c)))))
  (testing "default cases are last"
    (is (= :e (let [x 7]
                (case x
                    1 :a
                    2 :b
                    3 :c
                    :e))))))


(defn happy-number [x]
  (str x ":)"))

(deftest map-the-ultimate
   (testing "map takes a function and applies it to every element of the collection"
     (is (= ["1" "2" "3"] (map str [1 2 3]))))
   (testing "it can use anonymous functions"
     (is (= ["1!" "2!" "3!"] (map (fn [x] (str x "!")) [1 2 3]))))
   (testing "it can use functions defined somewhere else"
     (is (= ["1:)" "2:)" "3:)"] (map happy-number [1 2 3])))))

(defn happy-reduce [r x]
  (str r ":)" x))

(deftest reduce-the-ultimate
  (testing "reduce takes a function of two arguments and applies it to each element.  The first arg of the function in the ongoning result"
    (is (= 6 (reduce + [1 2 3]))))
  (testing "it can us anonymous functions"
    (is (= "123" (reduce (fn [r x] (str r x)) [1 2 3]))))
  (testing "it can use functions defined somewhere else"
    (is (= "1:)2:)3" (reduce happy-reduce [1 2 3])))))
