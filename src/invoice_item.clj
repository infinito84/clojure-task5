(ns invoice-item)

(defn- discount-factor [{:invoice-item/keys [discount-rate]
                         :or                {discount-rate 0}}]
  (- 1 (/ discount-rate 100.0)))

(defn subtotal
  [{:invoice-item/keys [precise-quantity precise-price discount-rate]
    :as                item
    :or                {discount-rate 0}}]
  (cond 
    (< precise-quantity 0) (throw (IllegalArgumentException. "quantity negative"))
    (< precise-price 0) (throw (IllegalArgumentException. "price negative"))
    (< discount-rate 0) (throw (IllegalArgumentException. "discount negative"))
    (> discount-rate 100) (throw (IllegalArgumentException. "discount over 100%"))
    :else (* precise-price precise-quantity (discount-factor item))))

