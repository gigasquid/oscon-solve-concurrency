(ns async-playground.core
  (:require [clojure.core.async :as async]
            [clj-http.client :as client]))

;;; Here we are going to experiment with core.async
;; follow along the hints in the comments and make the code changes

;;; We are going to use core.async to get the fastest tea service of
;;; two services - Yahoo Tea service and Google Tea service


;; Create a buffered channel of 10 for a yahoo tea service
(def google-tea-service-chan (async/chan 10))

;; Now create another channel for the yahoo tea service
(def yahoo-tea-service-chan (async/chan 10))


;;; We can get the google web page and the status of the call
;; doing:
;; (:status (client/get "http://google.com"))
;; try it out in your repl


;; We have a function to calling out to google tea
;; service within a go loop.  It will request
;; the google page and then put a string onto
;; the channel
(defn request-google-tea []
  (async/go
    (:status (client/get "http://google.com"))
    (async/>! google-tea-service-chan "tea compliments of google")))


;; Now write one to call out to yahoo and do the same this as yahoo
(defn request-yahoo-tea []
  (async/go
    (:status (client/get "http://yahoo.com"))
    (async/>! yahoo-tea-service-chan "tea compliments of yahoo")))


;; Now we are going to write a function that calls both services and
;; returns the fastest one

(defn request-tea []
  (request-google-tea)
  (request-yahoo-tea)
  (async/go
    (let [[v] (async/alts! [google-tea-service-chan
                            yahoo-tea-service-chan
                            ])]
      (println v))))


;; Now we can try it!
(request-tea)
