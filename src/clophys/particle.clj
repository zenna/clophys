(ns ^{:doc "Particle"
      :author "Zenna Tavares"}
  clophys.particle
  (:require [clophys.vector :refer :all])
  (:import [clophys.vector VectorN]))

(defrecord Particle
  [^VectorN position ^VectorN velocity ^VectorN acceleration ^VectorN forceAccum damping inverseMass])

;; Accessors
(defn velocity [particle] (:velocity particle))
(defn acceleration [particle] (:acceleration particle))
(defn damping [particle] (:damping particle))
(defn position [particle] (:position particle))

;; Factory ====================================================================
(defn std-uniform-particle []
  (->Particle (std-uniform-vector 3) (std-uniform-vector 3) (std-uniform-vector 3) (std-uniform-vector 3) 1.0 1.0))

;; Updaters ===================================================================
(defn update-position
  ([particle ^VectorN position] (assoc particle :position position))
  ([particle x y z] (update-position particle (make-vectorN 3 [x y z]))))

(defn update-velocity
  ([particle ^VectorN velocity] (assoc particle :velocity velocity))
  ([particle x y z] (update-velocity particle (make-vectorN 3 x y z))))

(defn add-force
  "Add a force to the particle"
  [particle force]
  (update-in particle [:forceAccum] #(v+ force %)))

(defn integrate
  [particle duration]
  {:pre [(>= duration 0.0)]}
  (let [position-new (add-scaled-v (position particle) (velocity particle) duration)]
    ))


(add-force (std-uniform-particle) (std-uniform-vector 3))

