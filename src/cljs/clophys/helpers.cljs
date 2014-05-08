(ns ^{:doc "Helpers"
      :author "Zenna Tavares"}
  clophys.helpers)

(defn tolerant=
  [x y]
  (< (* (- x y) (- x y)) 0.00001))

(defn first-which
  "Return first element of coll where (f elem) is true, nil if none exist
   (first-which even? [1 9 3 2])
   => 2"
  [f coll]
  (if (seq coll)
      (if (f (first coll)) (first coll) (recur f (next coll)))
      nil))
