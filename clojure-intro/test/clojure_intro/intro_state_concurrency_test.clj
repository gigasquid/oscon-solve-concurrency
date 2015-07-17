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
#_(deftest all-about-atoms
  (def counter (atom 1))
  (testing "dereferencing an atom"
    (is (= 0 @counter)))
  (testing "setting an atom to a new value"
    (fill-in-the-blank counter 5)
    (is (= 5 @counter)))
  (testing "updating an atom to a new value with a update function"
    (reset! counter 7)
    (= "7:)" (fill-in-the-blank counter happy-number))))

(defn add-2 [n]
  (+ 2 n))

;fill in the blank with ref-set or alter
#_(deftest all-about-refs
  (def salad-servings (ref 10))
  (def cucumbers (ref 3))
  (testing "dereferecing a ref"
    (is (= 11 @salad-servings))
    (is (= 2 @cucumbers)))
  (testing "setting a ref to a new value in a transaction"
    (dosync (fill-in-the-blank salad-servings 15)
            (fill-in-the-blank cucumbers 4))
    (is (= 12 @salad-servings))
    (is (= 5 @cucumbers)))
  (testing "updating refs to new value with udpate functions"
    (dosync
     (ref-set salad-servings 15)
     (ref-set cucumbers 4)
     (fill-in-the-blank salad-servings add-2)
     (fill-in-the-blank cucumbers add-2))
    (is (= 12 @salad-servings))
    (is (= 5 @cucumbers))))


(defn eat-cookie [state cookie]
  (assoc state cookie))

#_(deftest all-about-agents
  (def cookie-agent (agent [:chocolate-chip]))
  (testing "dereferencing agents"
    (is (= [:butter] @cookie-agent)))
  (testing "sending an action to an agent"
    (send cookie-agent eat-cookie :iced)
    (is (= [:chocolate-chip :iced-cookie]))))

#_(deftest all-about-pmap
  (testing "pmap is just like map but parallel"
    (is (= [5 6 7] (pmap dec [6 7 8 9])))))

#_(deftest all-about-promises
  (testing "using deliver for promises"
    (def cake-promise (promise))
    (deliver cake-promise "cookies")
    (is (= "cake" @cake-promise))))

#_(deftest all-about-delays
  (testing "delays only eval when dereffed than cache after that"
    (def cake-delay (delay (println "I like cake.") "cake"))
    (is (= "cake!" @cake-delay))
    (is (= "cake!" @cake-delay))))
