(ns ^{:doc "Particle"
      :author "Zenna Tavares"}
  clophys.particle
  (:require [clophys.vector :refer
              [clear add-scaled-v v+ s* std-uniform-vector make-vectorN
               orig-vector3]])
  (:import [clophys.vector/VectorN]))

(defrecord Particle
  [^VectorN position ^VectorN velocity ^VectorN acceleration ^VectorN force-accum damping inverse-mass])

;; Accessors
(defn velocity [particle] (:velocity particle))
(defn acceleration [particle] (:acceleration particle))
(defn damping [particle] (:damping particle))
(defn position [particle] (:position particle))

;; Factory ====================================================================
(defn std-uniform-particle []
  (->Particle (std-uniform-vector 3) (std-uniform-vector 3) (std-uniform-vector 3) (std-uniform-vector 3) 1.0 1.0))

(def make-particle ->Particle)

;; Updaters ===================================================================
(defn update-position
  ([particle ^VectorN position] (assoc particle :position position))
  ([particle x y z] (update-position particle (make-vectorN 3 [x y z]))))

(defn update-velocity
  ([particle ^VectorN velocity] (assoc particle :velocity velocity))
  ([particle x y z] (update-velocity particle (make-vectorN 3 [x y z]))))

(defn add-force
  "Add a force to the particle"
  [particle force]
  (update-in particle [:force-accum] #(v+ force %)))

;;     // Update linear position.
;;     position.addScaledVector(velocity, duration);

;;     // Work out the acceleration from the force
;;     Vector3 resultingAcc = acceleration;
;;     resultingAcc.addScaledVector(force-accum, inverseMass);

;;     // Update linear velocity from the acceleration.
;;     velocity.addScaledVector(resultingAcc, duration);

;;     // Impose drag.
;;     velocity *= real_pow(damping, duration);

;;     // Clear the forces.
;;     clearAccumulator();

(defn clear-accumulator
  "Remvoe accumulated force"
  [particle]
  (assoc particle :force-accum orig-vector3))

(defn integrate
  [particle duration]
  {:pre [(>= duration 0.0)]}
  (let [pos-new (add-scaled-v (position particle) (velocity particle) duration)
        acc-new (add-scaled-v (acceleration particle) (:force-accum particle) (:inverse-mass particle))
;;         pvar (println "v" (velocity particle) (Math/pow (:damping particle) duration))
        vel-new (s* (Math/pow (:damping particle) duration) (velocity particle))]
    (clear-accumulator
      (make-particle pos-new vel-new acc-new orig-vector3
                     (:damping particle) (:inverse-mass particle)))))

;; Comments
(add-force (std-uniform-particle) (std-uniform-vector 3))

