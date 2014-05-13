(ns clophys.body
  (:import [clophys.vector.vectorN])

(defrecord Body
  [shape force inertia inv-inertia mass inv-mass])

(defn apply-force [body f]
  (update body [:force] #(+ % f)))

(defn apply-impulse [impulse contact-vec]
  (update ))

(defn set-static [body])

(defn apply-impulse)

(defn set-static)

(defn )