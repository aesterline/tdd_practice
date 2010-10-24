(ns end_of_line_trim.core
  (:require [clojure.string :as str]))

(defn- trim-line [match]
  (let [[line end-of-line] (rest match)]
    (str (str/trimr line) end-of-line)))

(defn end-of-line-trim [s]
    (apply str (map trim-line (re-seq #"(.+)([\n\r]*)" s))))
