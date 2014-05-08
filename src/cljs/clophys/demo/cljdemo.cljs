(ns ^{:doc "Balistic Demo"
      :author "Zenna Tavares"}
  clophys.demo.cljsdemo
    (:require
      [clophys.demo.ballistic :refer [update-ammo pistol-ammo]]
      [dommy.utils :as utils]
      [dommy.core :as dommy])
    (:use-macros
      [dommy.macros :only [node sel sel1]]))

(def scene (THREE.Scene. ))
(def camera (THREE.PerspectiveCamera. 75 (/ (.-innerWidth js/window)
                                            (.-innerHeight js/window)) 0.1 1000))

(def renderer (THREE.WebGLRenderer.))
(set! (.-z (.-position camera)) 50)
(set! (.-y (.-position camera)) 1)


(def geometry (THREE.SphereGeometry. 1 10 10))
(def material (THREE.MeshBasicMaterial. (js-obj "color" 0x00ff00)))
(def cube (THREE.Mesh. geometry material))
(.add scene cube)

(def pistol-ammo-js (js-obj "ammo" pistol-ammo))

(defn set-sphere-pos!
  [sphere [x y z]]
  (set! (-> sphere .-position .-x) x)
  (set! (-> sphere .-position .-y) y)
  (set! (-> sphere .-position .-z) z))

(update-ammo (.-ammo pistol-ammo-js) 0.01)

(defn render []
  (.requestAnimationFrame js/window render)
  (let [new-ammo (update-ammo (.-ammo pistol-ammo-js) 0.01)]
    (set! (-> pistol-ammo-js .-ammo) new-ammo))
  (set-sphere-pos! cube (-> pistol-ammo-js .-ammo :particle :position :coords))
  (.render renderer scene camera))

(defn start []
  (js/alert (-> pistol-ammo-js .-ammo :particle :position :coords))
  (.setSize renderer (.-innerWidth js/window) (.-innerHeight js/window))
  (.appendChild (.-body js/document) (.-domElement renderer))
  (render))


(update-ammo (update-ammo pistol-ammo 0.1) 0.1)
(set! (.-onload js/window) start)
;; (start)
;(render)
