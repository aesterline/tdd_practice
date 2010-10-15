(ns password_complexity.test.core
  (:use [password_complexity.core] :reload)
  (:use [clojure.test]))


(defn- always-true [_] true)
(defn- always-false [_] false)

(deftest should-be-complex-when-the-required-number-of-rules-pass
  (is (is-complex? "ab34" [always-true] 1))
  (is (not (is-complex? "23ab!" [always-false] 2)))
  (is (is-complex? "23rt#" [always-false always-true] 1))
  (is (not (is-complex? "10wq!" [always-true always-false] 2))))

(deftest should-not-be-complex-when-the-password-is-a-dictionary-word
  (is (not (is-complex? "password" [always-true] 1)))
  (is (not (is-complex? "appLe" [always-true] 1)))
  (is (not (is-complex? "TRASH" [always-true] 1))))

(deftest contains-num-should-be-true-when-input-contains-at-least-one-number
  (is (contains-number? "a2b"))
  (is (not (contains-number? "abc")))
  (is (not (contains-number? "")))
  (is (not (contains-number? nil))))

(deftest contains-upper-should-be-true-when-input-contains-at-least-one-uppercase-letter
  (is (contains-upper? "Abc"))
  (is (not (contains-upper? "abc")))
  (is (not (contains-upper? "")))
  (is (not (contains-upper? nil))))

(deftest contains-lower-should-be-true-when-input-contains-at-least-one-lowercase-letter
  (is (contains-lower? "AbC"))
  (is (not (contains-lower? "ABC")))
  (is (not (contains-lower? "")))
  (is (not (contains-lower? nil))))

(deftest contains-special-should-be-true-when-input-contains-at-least-one-special-character
  (is (contains-special? "~abc"))
  (is (contains-special? "a!c"))
  (is (contains-special? "a@c"))
  (is (contains-special? "#bc"))
  (is (contains-special? "ab$"))
  (is (not (contains-special? "abc")))
  (is (not (contains-special? "")))
  (is (not (contains-special? nil))))

(deftest default-is-complex-should-require-two-default-rules
  (is (not (is-complex? "@#$")))
  (is (is-complex? "Pa$sword"))
  (is (is-complex? "p@ssword"))
  (is (is-complex? "123abc")))
