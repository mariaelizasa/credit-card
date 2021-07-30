(ns credit-card.core
  (:require [credit-card.db]))

(refer-clojure :exclude [range iterate format max min])
(use 'java-time)

(def random-cvv-generator (* (rand-int 10) 999))
(def random-card-number-generator (* (rand-int 999) 999))

(defn create-client-data
  [name cpf email]

  {:id cpf
   :data {:name name
          :cpf cpf
          :email email}

   :credit-card {:number        random-card-number-generator
                 :limit         (rand-int 1000)
                 :validate      (format "dd/MM/yyyy" (zoned-date-time 2028 07 30))
                 :cvv           random-cvv-generator
                 :purchases     (credit-card.db/all-purchases)}})

(def client (create-client-data "Maria" 920 "maria@gmail.com"))
(println "\n\n\n Creating Client" client)


(defn group-by-category [purchases]
  (->> purchases
       (group-by :category)
       ))

(println "\n\n\n Group by: "(group-by-category (credit-card.db/all-purchases)))

(defn search-by-price-or-establishment [value purchases]
  (->> purchases
       (filter #(or (= (:establishment %) value)
                    (= (:price %) value)))))

(println "\n\n\n Filter by Price or Establishment"(search-by-price-or-establishment "Nike" (credit-card.db/all-purchases)))
 
(defn sum-purchases
  [purchases]
  (->> purchases
       (map :price))
       (reduce +))
(println "\n\n Sum off all prices:" (sum-purchases (credit-card.db/all-purchases)))

(defn total-price-by-category [purchases]
  (->> purchases
       (vals)
       (group-by :category)
       (map sum-purchases)
       (reduce +))
  println)

(total-price-by-category (credit-card.db/all-purchases))




