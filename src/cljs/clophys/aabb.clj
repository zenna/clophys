(ns clophys.aabb
  (:import [clophys.vector/VectorN]))

;; Axis Aligned Bounding Box - aabb

;; Open question is how to
;; Have general n dim case
;; And special cases for 2D and 3D
;; Where we want type safety, or when some functions are only applicable in some dims
;; Proposal 1.
;; Just use n-dimensional case, store or calculate dimensionality to have preconditions
;; over number of dimensions.
;; Possibly have some convenience functions making 2 and 3d cases
;; Only problem with this is that predicate dispatch is slower than type dispatch
;; And we can't do static checking

;; TODO
;; Functios need to be updated to take new datastructure
;; intersect circle line.
;; consistent naming for instersect functions
;; consistent for aabb vs rect


(defrecord aabb
  [n-dims ^VectorN bounds])
;; Accessors ==================================================================
(defn [x] (num-dims) (:n-dims x))
(defn [x] (bounds) (:bounds x))
;; Intersections ==============================================================
(defn intersect-circle-line?
  [circle line])

(defn point-in-rect?
  "Is a point in a rectangle"
  [[x y] [[lower-x upper-x] [lower-y upper-y]]]
  (and (<= x upper-x) (>= x lower-x)
       (<= y upper-y) (>= y lower-y)))

(defn circle-aabb2-intersect
  [[centre r :as circle] rectangle]
  (let [lines (enum-lines rectangle)]
    (or (point-in-rect? centre rectangle)
        (intersect-circle-line? circle (lines 0))
        (intersect-circle-line? circle (lines 1))
        (intersect-circle-line? circle (lines 2))
        (intersect-circle-line? circle (lines 3)))))

(comment
  (def init-pos [0.0 0.0])
  (def init-vel [1.0 1.0])
  (def obstales []))