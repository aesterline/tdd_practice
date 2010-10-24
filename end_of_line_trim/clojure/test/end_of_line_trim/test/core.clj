(ns end_of_line_trim.test.core
  (:use [end_of_line_trim.core])
  (:use [lazytest.describe :only (describe it)]))

(describe "should trim whitespace from the end of the line"
  (it (= "abc" (end-of-line-trim "abc")))
  (it (= "abc" (end-of-line-trim "abc  ")))
  (it (= "abc" (end-of-line-trim "abc\t")))
  (it (= "abc\n" (end-of-line-trim "abc \n")))
  (it (= "abc\r\n" (end-of-line-trim "abc\t\r\n"))))

(describe "should not trim whitespace from the beginning of the line"
  (it (= "  ab" (end-of-line-trim "  ab")))
  (it (= "  ab" (end-of-line-trim "  ab  ")))
  (it (= "  ab" (end-of-line-trim "  ab\t")))
  (it (= "  ab\n" (end-of-line-trim "  ab  \n")))
  (it (= "  ab\r\n" (end-of-line-trim "  ab\t  \r\n"))))

(describe "should keep the original end lines"
  (it (= "  ab\ncd" (end-of-line-trim "  ab \ncd")))
  (it (= "ab\r\nde\n" (end-of-line-trim "ab\r\nde\n"))))
