(ns credit-card.core
  (:require [credit-card.db]))

(def random-cvv-generator (* (rand-int 10) 100))
(def random-card-number-generator (* (rand-int 1000) 1000))

(defn create-client-data
  [name cpf email]

  {:id cpf
   :data {:name name
          :cpf cpf
          :email email}

   :credit-card {:number        random-card-number-generator
                 :limit         (rand-int 1000)
                 :validate      10
                 :cvv           random-cvv-generator
                 :orders        (credit-card.db/all-orders)}})

(def client (create-client-data "Maria" 920 "maria@gmail.com"))


(defn total-by-category [orders]
  (->> (credit-card.db/all-orders)
    (group-by :category)
    println
  ))

(defn total-price-by-category [orders]
  (->> (credit-card.db/all-orders)
       (group-by :category)
       vals
       (map :price)
       (+ reduce)
    println
  ))









