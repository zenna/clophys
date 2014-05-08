(ns ^{:doc "Ballistic Demo"
      :author "Zenna Tavares"}
  clophys.demo.ballistic
  (:require [clophys.particle :refer [position integrate make-particle]]
            [clophys.vector :refer [make-vector3]])
  (:import [clophys.vector/VectorN]
           [clophys.particle/Particle]))

(def shot-type '[UNUSED PISTOL ARTILLERY FIREBALL LASER])

(defrecord AmmoRound
  [^Particle particle start-time])

(defn render
  "Render the ammo-round"
  [ammo-round]
  )

(def pistol-particle (make-particle (make-vector3 0.0 1.5 0.0)
                                    (make-vector3 0.0 0.0 10.0)
                                    (make-vector3 0.0 0.6 0.0)
                                    (make-vector3 0.0 0.0 0.0)
                                    0.9 1.0))

(def pistol-ammo (->AmmoRound pistol-particle 0.0))

(defn update-ammo
  [ammo duration]
  (update-in ammo [:particle] #(integrate % duration)))

(defn invalid-particle?
  "Particles become invalid when they go out of bounds of they've been flying too long"
  [particle]
  (or (< ((position particle) 0) 0.0)
      (> ((position particle) 2) 0.0)))

(defn update [ammo time dt]
  (if (> time 10.0)
      (recur (update-ammo ammo dt) (+ time dt) dt)
      ammo))

(enable-console-print!)

;(update pistol-ammo 0.0 0.01)

;; (println "what")
;; (integrate (:particle pistol-ammo) 0.1)
