(ns problem1)


(defn get-valid-invoice-items [invoice]
  (into [] (->>
            (get invoice :invoice/items)
            (filter #(or
                      (and
                       (= 19 (get-in % [:taxable/taxes 0 :tax/rate]))
                       (= 1 (count (get % :taxable/taxes)))
                       (= nil (get % :retentionable/retentions)))
                      (and
                       (= 1 (get-in % [:retentionable/retentions 0 :retention/rate]))
                       (= 1 (count (get % :retentionable/retentions)))
                       (= nil (get % :taxable/taxes))))))))

