(ns credit-card.core
  (:require [credit-card.db]))


(defn sum-purchases
  [purchases]
  (->> purchases
       (map :price)
       (reduce +)))

(println "\n\n Sum off all prices:" (sum-purchases (credit-card.db/all-purchases)))

(defn sum-total-by-category
  [[category purchases]]
  {:category category
  :total-value (sum-purchases purchases)}
  )

(defn total-price-by-category [purchases]
  (->> purchases   
       (group-by :category)
       (map sum-total-by-category)
       ))

(println "\n\n Total price by Category:" (total-price-by-category (credit-card.db/all-purchases)))

(defn search-by-price-or-establishment [value purchases]
  (->> purchases
       (filter #(or (= (:establishment %) value)
                    (= (:price %) value)))))

(println "\n\n\n Filter by Price or Establishment:" (search-by-price-or-establishment "Nike" (credit-card.db/all-purchases)))