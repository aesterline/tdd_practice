(ns center_string.core)

(defn- spaces [len]
  (apply str (repeat len " ")))

(defn center [s len]
  (let [slen (count s)
        lpad (int (/ (- len slen) 2))
        rpad (- len slen lpad)]
    (if (<= len slen) 
      s 
      (str (spaces lpad) s (spaces rpad)))))
