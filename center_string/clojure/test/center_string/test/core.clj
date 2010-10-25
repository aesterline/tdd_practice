(ns center_string.test.core
  (:use [center_string.core])
  (:use [lazytest.describe :only (describe it)]))

(describe "should not change the string when length is not greater than the string"
  (it (= "abc" (center "abc" 3)))
  (it (= "abc" (center "abc" 2)))) 

(describe "should distribute spaces evenly"
  (it (= " abc " (center "abc" 5)))
  (it (= " abcd " (center "abcd" 6)))
  (it (= "  abc  " (center "abc" 7))))

(describe "should distribute uneven spaces on the right side"
  (it (= " abc  " (center "abc" 6)))
  (it (= " abcd  " (center "abcd" 7))))
