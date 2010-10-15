(ns password_complexity.core)

(defn- count-passing-rules [password rules]
  (reduce + (map #(if (% password) 1 0) rules)))

(defn- make-regex-rule [re]
  (fn [s] (not (nil? (if s (re-matches re s))))))

(def contains-number? (make-regex-rule #".*[0-9]+.*"))
(def contains-upper? (make-regex-rule #".*[A-Z]+.*"))
(def contains-lower? (make-regex-rule #".*[a-z]+.*"))
(def contains-special? (make-regex-rule #".*[~!@#$]+.*"))

(def default-rules [contains-number? contains-upper? contains-lower? contains-special?])

(defn is-complex?
  ([password] (is-complex? password default-rules 2))
  ([password rules required-num-rules]
    (>= (count-passing-rules password rules) required-num-rules)))