(ns ^{:doc "Helpers"
      :author "Zenna Tavares"}
  clophys.helpers)

(defn sqr [x] (* x x))

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

(defn clamp-f [thres f x]
  "Apply f to a x, but clamp it to a maximum value of thres"
  (let [y (f x)]
    (if (> y thres)
        thres
        y)))

(defn clamp [x thres]
  "Clamp x to a threshold"
  (if (> x thres)
      thres
      x))
