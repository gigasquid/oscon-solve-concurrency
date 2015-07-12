(ns ^:figwheel-always clojurescript-async-demo.core
    (:require
     [cljs.core.async :refer [timeout chan >! <!]])
    (:require-macros
     [cljs.core.async.macros :refer [go go-loop]]))


(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {1 {:id 1 :x 100 :y 200 :val 0}
                          2 {:id 2 :x 300 :y 200 :val 0}}))
(defonce running (atom true))

(def canvas (-> js/document (.getElementById "canvas")))
(def context (.getContext canvas "2d"))
(def width (.-width canvas))
(def height (.-height canvas))
(def background "white")
(def colors ["red" "pink" "lightgray" "lightblue" "green" "lightgreen" "orange" "yellow"])
(def d 50)

(defn setText [context color style]
  (set! (.-fillStyle context) color)
  (set! (.-font context) style))

(defn setColor [context color]
  (set! (.-fillStyle context) color)
  (set! (.-globalAlpha context) 1.0))


(defn draw-dot [{:keys [x y val]}]
  (doto context
       (setColor "lightgreen")
       .beginPath
       (.arc  x y d 0 (* 2 Math/PI) true)
       .closePath
       .fill )
  (doto context
    (setText "black" "bold 11px Courier")
    (.fillText (str val) (- x 7) (+ y 5))))

(defn draw-dots [state]
  (doall (map draw-dot state)))


(defn clear []
  (doto context
    (setColor background)
    (.fillRect  0 0 width height)))


(defn gen-calc-loop [dot timeout-num]
  (let [id (:id dot)]
    (go-loop [counter 10]
      (when (pos? counter)
        (<! (timeout timeout-num))
        (let [dot (get @app-state id)
              new-dot-val (inc (:val dot))
              new-dot (merge dot {:val new-dot-val})]
          (swap! app-state assoc id new-dot)
          (recur (dec counter)))))))

(defn gen-loops []
  (gen-calc-loop (first (vals @app-state)) 600)
  (gen-calc-loop (last (vals @app-state)) 1200))

(defn tick []
  (clear)
  (do (draw-dots (vals @app-state))))


(defn time-loop []
  (go
    (<! (timeout 30))
    (tick)
    (.requestAnimationFrame js/window time-loop)))

(defn run []
  (.requestAnimationFrame
   js/window
   (fn [_]
     (time-loop))))


(gen-loops)
(run)
(println "hi there")
