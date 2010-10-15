(ns password_complexity.test.core
  (:use [password_complexity.core] :reload)
  (:use [clojure.test]))


(defn- always-true [_] true)
(defn- always-false [_] false)

(deftest should-be-complex-when-the-required-number-of-rules-pass
  (is (= true (is-complex? "password" [always-true] 1)))
  (is (= false (is-complex? "password" [always-false] 2)))
  (is (= true (is-complex? "password" [always-false always-true] 1)))
  (is (= false (is-complex? "password" [always-true always-false] 2))))

(deftest contains-num-should-be-true-when-input-contains-at-least-one-number
  (is (= true (contains-number? "a2b")))
  (is (= false (contains-number? "abc")))
  (is (= false (contains-number? "")))
  (is (= false (contains-number? nil))))

(deftest contains-upper-should-be-true-when-input-contains-at-least-one-uppercase-letter
  (is (= true (contains-upper? "Abc")))
  (is (= false (contains-upper? "abc")))
  (is (= false (contains-upper? "")))
  (is (= false (contains-upper? nil))))

(deftest contains-lower-should-be-true-when-input-contains-at-least-one-lowercase-letter
  (is (= true (contains-lower? "AbC")))
  (is (= false (contains-lower? "ABC")))
  (is (= false (contains-lower? "")))
  (is (= false (contains-lower? nil))))

(deftest contains-special-should-be-true-when-input-contains-at-least-one-special-character
  (is (= true (contains-special? "~abc")))
  (is (= true (contains-special? "a!c")))
  (is (= true (contains-special? "a@c")))
  (is (= true (contains-special? "#bc")))
  (is (= true (contains-special? "ab$")))
  (is (= false (contains-special? "abc")))
  (is (= false (contains-special? "")))
  (is (= false (contains-special? nil))))

(deftest default-is-complex-should-require-two-default-rules
  (is (= false (is-complex? "password")))
  (is (= true (is-complex? "Password")))
  (is (= true (is-complex? "p@ssword")))
  (is (= true (is-complex? "123abc"))))
