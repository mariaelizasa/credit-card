(ns credit-card.logic
  (:require [credit-card.db]))


(def random-cvv-generator (* (rand-int 10) 999))
(def random-card-number-generator (rand-int 100000))
(def random-limit-generator (rand-int 5000))

(defn create-client-data
  [name cpf email]

  {:id cpf
   :data {:name name
          :cpf cpf
          :email email}

   :credit-card {:number        random-card-number-generator
                 :limit         random-limit-generator   
                 :validate      (format "dd/MM/yyyy" (zoned-date-time 2028 07 30))
                 :cvv           random-cvv-generator
                 :purchases     (credit-card.db/all-purchases)}}) 

(defn sum-purchases
  [purchases]
  (->> purchases
       (map :price)
       (reduce +)))

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

(defn search-by-price-or-establishment [value purchases]
  (->> purchases
       (filter #(or (= (:establishment %) value)
                    (= (:price %) value)))))

