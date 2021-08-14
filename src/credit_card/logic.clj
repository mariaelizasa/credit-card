(ns credit-card.logic
  (:require [credit-card.db]
            [datomic.api :as d]
            [schema.core :as s]
            [credit-card.models :as m]
            [credit-card.db-datomic :as db])

  (:use [java-time])
  (:use [clojure.pprint :exclude [formatter]]))

(defn id [] (java.util.UUID/randomUUID))

(def conn (db/create-and-connect-to-database!))
(db/create-schemaP! conn)

(s/set-compile-fn-validation! true)

(def random-cvv-generator (* (rand-int 10) 999))
(def random-card-number-generator (rand-int 100000))

(defn add-purchase
  "Add new-purchase to all-purchases"
  [purchase new-purchase]
  (conj new-purchase purchase))

(s/defn create-purchase :- m/Purchase
  "Create Purchase"
  [id :- s/Uuid, price :- (s/constrained Float pos?), establishment :- s/Str, category :- s/Str]
  :purchases {
              :purchase/id            id
              :purchase/date          (format "dd/MM/yyyy" (local-date))
              :purchase/price         price
              :purchase/establishment establishment
              :purchase/category      category
              })

(def new-purchase (create-purchase (id) 1350.20 "Spoleto" "Food"))
(d/transact conn [new-purchase])

(def purchases new-purchase)
(def total-purchases (add-purchase purchases (credit-card.db/all-purchases)))


(s/defn create-credit-card :- m/Credit-Card
  "Create Client"
  []
  :credit-card {:credit-card/number   random-card-number-generator
                :credit-card/limit    2000.00
                :credit-card/validate (format "dd/MM/yyyy" (zoned-date-time 2028 07 30))
                :credit-card/cvv      random-cvv-generator
                :credit-card/purchase total-purchases})


(s/defn create-customer-data :- m/Customer
  "Create Client"
  [id :- s/Uuid, name :- s/Str, cpf :- (s/constrained s/Int pos-int?), email :- s/Str]
  {:customer {:customer/id          id
              :customer/name        name
              :customer/cpf         cpf
              :customer/email       email
              :costumer/credit-card (create-credit-card)}
   })

(defn sum-purchases
  "Return the sum of all Purchases"
  [purchases]
  (->> purchases
       (map :price)
       (reduce +)))

(defn sum-total-by-category
  "Return a HashMap with keywords containing Category and Total-Value"
  [[category purchases]]
  {:category    category
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


(db/delete-database!)