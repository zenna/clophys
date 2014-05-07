(ns ^{:doc "Balistic Demo"
      :author "Zenna Tavares"}
  clophys.demo.balistic
  (:require [clophys.vector :refer :all]
            [clophys.particle :refer [position integrate]])
  (:import [clophys.vector VectorN]
           [clophys.particle Particle]))

(defn update
  [])

(def shot-type '[UNUSED PISTOL ARTILLERY FIREBALL LASER])

(defrecord AmmoRound
  [^Particle particle shot-type start-time])

(defn render
  "Render the ammo-round"
  [ammo-round]
  )

(def MAX_AMMO_ROUNDS 16)

(def ammo-rounds (vec (repeat 16 'UNUSED)))

(def current-shot-type 'LASER)

(defn first-which
  "Return first element of coll where (f elem) is true, nil if none exist
   (first-which even? [1 9 3 2])
   => 2"
  [f coll]
  (if (seq coll)
      (if (f (first coll)) (first coll) (recur f (next coll)))
      nil))

(defn available-round
  "Find the first available round."
  [ammo-rounds]
  (first-which #(not= (:shot-type %) 'UNUSUED) ammo-rounds))

(defn fire
  "Fires a round into the sky.
   Fire involves geting the right particle properties, putting the particle in the right place
   and applying a force"
  [ammo-rounds]
  (let [round (available-round ammo-rounds)]))

;; void BallisticDemo::fire()
;; {
;;     // Find the first available round.
;;     AmmoRound *shot;
;;     for (shot = ammo; shot < ammo+ammoRounds; shot++)
;;     {
;;         if (shot->type == UNUSED) break;
;;     }

;;     // If we didn't find a round, then exit - we can't fire.
;;     if (shot >= ammo+ammoRounds) return;

;;     // Set the properties of the particle
;;     switch(currentShotType)
;;     {
;;     case PISTOL:
;;         shot->particle.setMass(2.0f); // 2.0kg
;;         shot->particle.setVelocity(0.0f, 0.0f, 35.0f); // 35m/s
;;         shot->particle.setAcceleration(0.0f, -1.0f, 0.0f);
;;         shot->particle.setDamping(0.99f);
;;         break;

;;     case ARTILLERY:
;;         shot->particle.setMass(200.0f); // 200.0kg
;;         shot->particle.setVelocity(0.0f, 30.0f, 40.0f); // 50m/s
;;         shot->particle.setAcceleration(0.0f, -20.0f, 0.0f);
;;         shot->particle.setDamping(0.99f);
;;         break;

;;     case FIREBALL:
;;         shot->particle.setMass(1.0f); // 1.0kg - mostly blast damage
;;         shot->particle.setVelocity(0.0f, 0.0f, 10.0f); // 5m/s
;;         shot->particle.setAcceleration(0.0f, 0.6f, 0.0f); // Floats up
;;         shot->particle.setDamping(0.9f);
;;         break;

;;     case LASER:
;;         // Note that this is the kind of laser bolt seen in films,
;;         // not a realistic laser beam!
;;         shot->particle.setMass(0.1f); // 0.1kg - almost no weight
;;         shot->particle.setVelocity(0.0f, 0.0f, 100.0f); // 100m/s
;;         shot->particle.setAcceleration(0.0f, 0.0f, 0.0f); // No gravity
;;         shot->particle.setDamping(0.99f);
;;         break;
;;     }

;;     // Set the data common to all particle types
;;     shot->particle.setPosition(0.0f, 1.5f, 0.0f);
;;     shot->startTime = TimingData::get().lastFrameTimestamp;
;;     shot->type = currentShotType;

;;     // Clear the force accumulators
;;     shot->particle.clearAccumulator();
;; }

(defn invalid-particle?
  "Particles become invalid when they go out of bounds of they've been flying too long"
  [particle]
  (or (< ((position particle) 0) 0.0)
      (> ((position particle) 2) 0.0)))

(defn deactive-invalid-ammo
  [ammo]
  (if (invalid-particle? (:particle ammo))
      (assoc ammo :shot-type 'UNUSHED)
      ammo))

(defn update-ammo
  [ammo-rounds duration]
  (map (comp (fn [ammo] (update-in ammo [:ammo] #(integrate % duration))))
             deactive-invalid-ammo ammo-rounds))
