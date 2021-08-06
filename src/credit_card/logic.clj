(ns credit-card.logic
  (:require [credit-card.db]))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(def random-cvv-generator (* (rand-int 10) 999))
(def random-card-number-generator (rand-int 100000))
(def random-limit-generator (rand-int 5000))

(defn add-purchase
  [purchase all-purchases]
  (conj all-purchases purchase))

(defn create-purchase
  [price establishment category]
  {:date        (format "dd/MM/yyyy" (local-date))
   :price       price
   :category    category
   :establishment establishment })

(def purchase (create-purchase 1350.20 "Locker Room" "Nike"))
(def purchases purchase)

(def total-purchases (add-purchase purchases (credit-card.db/all-purchases)))

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
                 :purchases     total-purchases}})

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

