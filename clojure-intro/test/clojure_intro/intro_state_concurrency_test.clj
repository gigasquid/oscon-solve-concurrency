(ns clojure-intro.intro-state-concurrency-test
  (:require [clojure-intro.intro :refer :all]
            [clojure.test :refer :all]))


;; Uncomment each test by removing the #_ from in front of the deftest
;; to start running.


;; this is a helper function that is a stand-in for the right function
;; to use
(defn fill-in-the-blank [& args] "fill me in with the right function")

(defn happy-number [n]
  (str n ":)"))

;;; fill in the blank functions with swap! or reset!
(deftest all-about-atoms
  (def counter (atom 1))
  (testing "dereferencing an atom"
    (is (= 1 @counter)))

  (testing "setting an atom to a new value"
    (reset! counter 5)
    (is (= 5 @counter)))

  (testing "updating an atom to a new value with a update function"
    (reset! counter 7)
    (= "7:)" (swap! counter happy-number))))

(defn add-2 [n]
  (+ 2 n))

;fill in the blank with ref-set or alter
(deftest all-about-refs
  (def salad-servings (ref 10))
  (def cucumbers (ref 3))

  (testing "dereferecing a ref"
    (is (= 10 @salad-servings))
    (is (= 3 @cucumbers)))

  (testing"setting a ref to a new value in a transaction"
    (dosync (ref-set salad-servings 15)
            (ref-set cucumbers 4))
    (is (= 15 @salad-servings))
    (is (= 4 @cucumbers)))

  (testing "updating refs to new value with udpate functions"
    (dosync
     (ref-set salad-servings 15)
     (ref-set cucumbers 4)
     (alter salad-servings add-2)
     (alter cucumbers add-2))
    (is (= 17 @salad-servings))
    (is (= 6 @cucumbers))))

(defn eat-cookie [state cookie]
  (assoc state cookie))

(deftest all-about-agents
  (def cookie-agent (agent [:chocolate-chip]))

  (testing "dereferencing agents"
    (is (= [:chocolate-chip] @cookie-agent)))

  (testing "sending an action to an agent"
    (send cookie-agent eat-cookie :iced)
    (is (= [:chocolate-chip :iced]))))
