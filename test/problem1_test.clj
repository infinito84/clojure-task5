(ns problem1-test
  (:require [clojure.test :refer :all]
            [problem1 :as problem1]
            [clojure.edn :refer [read-string]]))



(deftest thread-last-operator
  (testing "checking the result of invoice.edn"
    (let [invoice (read-string (slurp "invoice.edn"))
          invoice-items (problem1/get-valid-invoice-items invoice)]
      ;; following tests are based on manual inspection on invoice.edn file
      (is (= 2 (count invoice-items)))
      (is (= 19
             (get-in invoice-items
                     [0 :taxable/taxes 0 :tax/rate])))
      (is (= "ii3" (get-in invoice-items [0 :invoice-item/id])))
      (is (= 1
             (get-in invoice-items
                     [1 :retentionable/retentions 0 :retention/rate])))
      (is (= "ii4" (get-in invoice-items [1 :invoice-item/id]))))))
