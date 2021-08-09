(ns credit-card.logic
  (:require [credit-card.db]
            [schema.core :as s]))

(s/set-compile-fn-validation! true)

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(def random-cvv-generator (* (rand-int 10) 999))
(def random-card-number-generator (rand-int 100000))

(defn add-purchase
  "Add new-purchase to all-purchases"
  [purchase new-purchase]
  (conj new-purchase purchase))

(s/defn create-purchase
  "Create Purchase"
  [price :- (s/constrained Float pos?), establishment :- s/Str, category :- s/Str]
  {:date        (format "dd/MM/yyyy" (local-date))
   :price       price
   :category    category
   :establishment establishment })

(def new-purchase (create-purchase 1350.20 "Locker Room" "Nike"))
(def purchases new-purchase)

(def total-purchases (add-purchase purchases (credit-card.db/all-purchases)))

(s/defn create-client-data
  "Create Client"
  [name :- s/Str, cpf :- (s/constrained s/Int pos-int?), email :- s/Str]

  {:id cpf
   :data {:name name
          :cpf cpf
          :email email}

   :credit-card {:number        random-card-number-generator
                 :limit         2000.00
                 :validate      (format "dd/MM/yyyy" (zoned-date-time 2028 07 30))
                 :cvv           random-cvv-generator
                 :purchases     total-purchases}})

(defn sum-purchases
  "Return the sum of all Purchases"
  [purchases]
  (->> purchases
       (map :price)
       (reduce +)))

(defn sum-total-by-category
  "Return a HashMap with keywords containing Category and Total-Value"
  [[category purchases]]
  {:category category
  :total-value (sum-purchases purchases)}
  )

(defn total-price-by-category [purchases]
  "Return the total price by category"
  (->> purchases   
       (group-by :category)
       (map sum-total-by-category)
       ))

(defn search-by-price-or-establishment [value purchases]
  "Return the result of search by price or establishment"
  (->> purchases
       (filter #(or (= (:establishment %) value)
                    (= (:price %) value)))))

