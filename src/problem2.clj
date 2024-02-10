(ns problem2
  (:require [clojure.data.json :refer [read-str]]
            [clj-time.format :as f]))

(defn parse-from-json
  [jsonFile]
  (let [jsonData (read-str (slurp jsonFile))]
    (into {} [[:invoice/issue-date
               (f/parse
                (f/formatter "dd/MM/yyyy")
                (get-in jsonData ["invoice" "issue_date"]))]
              [:invoice/customer
               (into {} [[:customer/name (get-in jsonData ["invoice" "customer" "company_name"])]
                         [:customer/email (get-in jsonData ["invoice" "customer" "email"])]])]
              [:invoice/items (into [] (map
                                        #(into {} [[:invoice-item/price (get % "price")]
                                                   [:invoice-item/quantity (get % "quantity")]
                                                   [:invoice-item/sku (get % "sku")]
                                                   [:invoice-item/taxes (into [] (map
                                                                                  (fn [tax] (into {} [[:tax/category :iva]
                                                                                                      [:tax/rate (double (get tax "tax_rate"))]]))
                                                                                  (get % "taxes")))]])
                                        (get-in jsonData ["invoice" "items"])))]])))




