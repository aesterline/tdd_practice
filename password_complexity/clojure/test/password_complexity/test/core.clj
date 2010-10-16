(ns password_complexity.test.core
  (:use [password_complexity.core]
        [lazytest.describe :only (describe it)]))


(defn- always-true [_] true)
(defn- always-false [_] false)

(describe "should be complex when the required number of rules pass"
  (it (complex? "ab34" [always-true] 1))
  (it (not (complex? "23ab!" [always-false] 2)))
  (it (complex? "23rt#" [always-false always-true] 1))
  (it (not (complex? "10wq!" [always-true always-false] 2))))

(describe "should not be complex when the password is a dictionary word"
  (it (not (complex? "password" [always-true] 1)))
  (it (not (complex? "appLe" [always-true] 1)))
  (it (not (complex? "TRASH" [always-true] 1))))

(describe "contains-number? should be true when input contains at least one number"
  (it (contains-number? "a2b"))
  (it (not (contains-number? "abc")))
  (it (not (contains-number? "")))
  (it (not (contains-number? nil))))

(describe "contains-upper? should be true when input contains at least one uppercase letter"
  (it (contains-upper? "Abc"))
  (it (not (contains-upper? "abc")))
  (it (not (contains-upper? "")))
  (it (not (contains-upper? nil))))

(describe "contains-lower? should be true when input contains at least one lowercase letter"
  (it (contains-lower? "AbC"))
  (it (not (contains-lower? "ABC")))
  (it (not (contains-lower? "")))
  (it (not (contains-lower? nil))))

(describe "contains-special? should be true when input contains at least one special character"
  (it (contains-special? "~abc"))
  (it (contains-special? "a!c"))
  (it (contains-special? "a@c"))
  (it (contains-special? "#bc"))
  (it (contains-special? "ab$"))
  (it (not (contains-special? "abc")))
  (it (not (contains-special? "")))
  (it (not (contains-special? nil))))

(describe "the default complex? should require two default-rules"
  (it (not (complex? "@#$")))
  (it (complex? "Pa$sword"))
  (it (complex? "p@ssword"))
  (it (complex? "123abc")))
