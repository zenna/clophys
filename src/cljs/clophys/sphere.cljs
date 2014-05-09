(ns clophys.sphere
  (:require [clophys.helpers :refer [sqr]]
            [clophys.vector :refer [sqr-dist]]))

(defrecord h-sphere
  [n-dims ^VectorN center])

;; Accessors ==================================================================
(defn [x] (num-dims) (:n-dims x))
(defn [x] (bounds) (:bounds x))

;; Intersections ==============================================================
(defn intersect-hypersphere?
  "Do two hyperspheres intersect?
   They intersect if, and only if, the distance between their centers
   is between the sum and the difference of their radii.
   Avoids sqrt"
  [[c1 r1] [c2 r2]]
  (let [centre-diff (sqr-dist c1 c2)]
    (and (<= (sqr (- r1 r2)) centre-diff)
         (<= centre-diff (sqr (+ r1 r2))))))