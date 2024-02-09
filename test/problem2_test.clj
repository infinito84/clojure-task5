(ns problem2-test (:require [clojure.test :refer :all]
                            [problem2 :as problem2]
                            [invoice-spec :as spec]
                            [clojure.spec.alpha :as s]))


(deftest core-generating-functions
  (testing "parsing JSON to spec"
    (let [invoice (problem2/parse-from-json "invoice.json")]
         (is (= true (s/valid? ::spec/invoice invoice))))))
