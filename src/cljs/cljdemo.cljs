(ns ^{:doc "Balistic Demo"
      :author "Zenna Tavares"}
  clophys.cljdemo
    (:require
      [dommy.utils :as utils]
      [dommy.core :as dommy]
      [cljs.core.match]
      [clophys.what :refer [douba]])
      ;[clophys.demo.balistic :refer [update-ammo first-which]])
    (:use-macros
      [dommy.macros :only [node sel sel1]]))

;    (:require-macros [cljs.core.match.macros :refer [match]])
;  (:require [cljs.core.match]))

(def scene (THREE.Scene. ))
(def camera (THREE.PerspectiveCamera. 75 (/ (.-innerWidth js/window)
                                            (.-innerHeight js/window)) 0.1 1000))

(def renderer (THREE.WebGLRenderer.))
(set! (.-z (.-position camera)) 5)

(def geometry (THREE.CubeGeometry. 1 1 1))
(def material (THREE.MeshBasicMaterial. (js-obj "color" 0x00ff00)))
(def cube (THREE.Mesh. geometry material))
(.add scene cube)

(-> cube .-rotation .-x)

(set! (-> cube .-rotation .-y) 0)
(set! (-> cube .-rotation .-z) 0)

(defn render []
  (.requestAnimationFrame js/window render)
  (set! (-> cube .-rotation .-y) (+ (-> cube .-rotation .-y) 0.05))
  (set! (-> cube .-rotation .-x) (+ (-> cube .-rotation .-x) 0.01))
  (.render renderer scene camera))

(defn start []
  (js/alert (sqr 3))
  (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
  (.appendChild (.-body js/document) (.-domElement renderer))
  (render))

(set! (.-onload js/window) start)

(render)
