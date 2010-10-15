(ns password_complexity.core
  (use [clojure.contrib.duck-streams :only (read-lines)])
  (use [clojure.contrib.string :only (lower-case)]))

(defn- count-passing-rules [password rules]
  (reduce + (map #(if (% password) 1 0) rules)))

(defn- make-regex-rule [re]
  (fn [s] (if s (re-matches re s))))

(def contains-number? (make-regex-rule #".*[0-9]+.*"))
(def contains-upper? (make-regex-rule #".*[A-Z]+.*"))
(def contains-lower? (make-regex-rule #".*[a-z]+.*"))
(def contains-special? (make-regex-rule #".*[~!@#$]+.*"))

(def default-rules [contains-number? contains-upper? contains-lower? contains-special?])
(def dictionary (set (map lower-case (read-lines "/usr/share/dict/words"))))

;(not-any? #{ (lower-case password) } dictionary)

(defn complex?
  ([password] (complex? password default-rules 2))
  ([password rules required-num-rules]
    (and 
      (not (contains? dictionary (lower-case password)))
      (>= (count-passing-rules password rules) required-num-rules))))
