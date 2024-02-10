(ns problem3-test (:require [clojure.test :refer :all]
                            [invoice-item]))


(deftest successful-discount
  (testing "discount works 10*5*0.9 = 45"
    (is (= 45.0
           (invoice-item/subtotal {:invoice-item/precise-quantity 10
                                   :invoice-item/precise-price 5
                                   :invoice-item/discount-rate 10})))
    (is (= 0.0
           (invoice-item/subtotal {:invoice-item/precise-quantity 10
                                   :invoice-item/precise-price 5
                                   :invoice-item/discount-rate 100})))))

(deftest no-discount
  (testing "without discount works 10*5 = 50"
    (is (= 50.0
           (invoice-item/subtotal {:invoice-item/precise-quantity 10
                                   :invoice-item/precise-price 5})))))

(deftest zero-values
  (testing "zero-values"
    (is (= 0.0
           (invoice-item/subtotal {:invoice-item/precise-quantity 10
                                   :invoice-item/precise-price 0})))
    (is (= 0.0
           (invoice-item/subtotal {:invoice-item/precise-quantity 0
                                   :invoice-item/precise-price 5})))))

(deftest negative-values
  (testing "negative-values"
    (is (= true (try
                  (invoice-item/subtotal {:invoice-item/precise-quantity -10
                                          :invoice-item/precise-price 5})
                  false
                  (catch IllegalArgumentException _ true))))))

(deftest discount-over-100-percentage
  (testing "discount over 100"
    (is (= true (try
                  (invoice-item/subtotal {:invoice-item/precise-quantity -10
                                          :invoice-item/precise-price 5
                                          :invoice-item/discount-rate 200})
                  false
                  (catch IllegalArgumentException _ true))))))
