(ns credit-card.db-datomic
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/credit-card")

(defn create-and-connect-to-database []
  (d/create-database db-uri)
  (d/connect db-uri))


(defn delete-database []
  (d/delete-database db-uri))

(def schema [
             {:db/ident        :purchase/id
              :db/valueType    :db.type/uuid
              :db/cardinality  :db.cardinality/one
              :db/unique       :db.unique/identity}

             {:db/ident       :purchase/date
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "The date of purchase made"}

             {:db/ident       :purchase/price
              :db/valueType   :db.type/float
              :db/cardinality :db.cardinality/one
              :db/doc         "The price of purchase"}

             {:db/ident       :purchase/establishment
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "The establishment of purchase"}

             {:db/ident       :purchase/category
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "The category of purchase"}
             ])

(defn create-schema [conn]
  (d/transact conn schema))